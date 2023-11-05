package sistemausuario.repository.commom;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import static java.util.Objects.nonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class GenericFilterRepositoryImpl<T> {

    @PersistenceContext
    protected EntityManager manager;

    private Class<T> genericType;

    public GenericFilterRepositoryImpl(Class<T> genericType) {
        this.genericType = genericType;
    }

    public Page<List<T>> findAll(Integer skip, Integer take, String filter, String sort) {

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaBuilder builderCount = manager.getCriteriaBuilder();

        CriteriaQuery<T> criteria = builder.createQuery(genericType);
        CriteriaQuery<Long> criteriaCount = builderCount.createQuery(Long.class);
        Root<T> root = criteria.from(genericType);
        Root<T> rootCount = criteriaCount.from(genericType);

        List<Predicate> predicates = new ArrayList<>();
        List<Predicate> predicatesCount = new ArrayList<>();

        if (filter != null && !filter.isBlank()) {
            String[] filters = filter.split(",\"and\",");
            if (filters != null && filters.length > 0) {
                for (int i = 0; i < filters.length; i++) {
                    String filtro = filters[i];
                    if (filtro.contains(",\"or\",")) {
                        predicates.add(filterOrColumn(predicates, filtro.split(",\"or\","), builder, root));
                        predicatesCount.add(
                                filterOrColumn(predicatesCount, filtro.split(",\"or\","), builderCount, rootCount));
                    } else {
                        filtro = filtro.replaceAll("]", "");
                        filtro = filtro.replaceAll("\\[", "");
                        String[] itens = filtro.split("\",");
                        for (int j = 0; j < itens.length; j++) {
                            if (itens[j].startsWith("\""))
                                itens[j] = itens[j].replace("\"", "");
                            if (itens[j].endsWith("\""))
                                itens[j] = itens[j].substring(0, itens[j].length() - 2);
                        }
                        Predicate predicate = condicao(itens, builder, root);
                        if (nonNull(predicate))
                            predicates.add(predicate);
                        Predicate predicateCount = condicao(itens, builderCount, rootCount);
                        if (nonNull(predicateCount))
                            predicatesCount.add(predicateCount);
                    }
                }
            }
        }

        criteria.where(predicates.toArray(new Predicate[0]));
        criteriaCount.select(builderCount.count(getCountPath(rootCount)));
        criteriaCount.where(predicatesCount.toArray(new Predicate[0]));

        List<Order> order = new ArrayList<>();
        if (sort != null && !sort.isBlank()) {
            String[] sorts = sort.split("},\\{");
            for (int i = 0; i < sorts.length; i++) {
                String filtro = sorts[i];
                filtro = filtro.replaceAll("]", "");
                filtro = filtro.replaceAll("\\[", "");
                filtro = filtro.replaceAll("\\{", "");
                filtro = filtro.replaceAll("}", "");
                String[] itens = filtro.split("\",");
                for (int j = 0; j < itens.length; j++) {
                    if (itens[j].startsWith("\"selector\":\"")) {
                        itens[j] = itens[j].replace("\"selector\":\"", "");
                    }
                    if (itens[j].startsWith("\"desc\":"))
                        itens[j] = itens[j].replace("\"desc\":", "");
                }

                if (itens[1].equals("true")) {
                    order.add(builder.desc(getSortColumn(itens[0], root)));
                } else {
                    order.add(builder.asc(getSortColumn(itens[0], root)));
                }
            }
        } else {
            order.add(builder.asc(root.get("id")));

        }

        criteria.orderBy(order);

        TypedQuery<T> query = manager.createQuery(criteria);
        Long total = null;
        List<T> result;

        total = manager.createQuery(criteriaCount).getSingleResult();

        if (nonNull(take) && take == 0) {
            result = query.getResultList();
        } else {
            result = query.setFirstResult(nonNull(skip) ? skip : 0).setMaxResults(nonNull(take) ? take : 10)
                    .getResultList();
        }

        return new Page<>(result, total);

    }


    private LocalDate formatDate(String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(value, formatter);
        } catch (Exception e) {
        }
        return null;
    }

    public Path<Object> getCountPath(Root<T> rootCount) {
        return rootCount.get("id");
    }

    public abstract Path<Object> getSortColumn(String value, Root<T> root);

    public abstract Path<Object> getSearchColumn(String value, Root<T> root);

    public Object getSearchColumnType(String value, Class<?> type) {
        return type.cast(value);
    }

    private Predicate filterOrColumn(List<Predicate> predicates, String[] filters, CriteriaBuilder builder,
            Root<T> root) {
        List<Predicate> predicatesOr = new ArrayList<>();
        for (int i = 0; i < filters.length; i++) {
            String filtro = filters[i];
            filtro = filtro.replaceAll("]", "");
            filtro = filtro.replaceAll("\\[", "");
            String[] itens = filtro.split("\",");
            for (int j = 0; j < itens.length; j++) {
                if (itens[j].startsWith("\""))
                    itens[j] = itens[j].replace("\"", "");
                if (itens[j].endsWith("\""))
                    itens[j] = itens[j].substring(0, itens[j].length() - 2);
            }

            predicatesOr.add(condicao(itens, builder, root));
        }

        Predicate orClause = criaClausulaOR(predicatesOr, builder);

        return orClause;
    }

    private Predicate criaClausulaOR(List<Predicate> predicatesOr, CriteriaBuilder builder) {
        Predicate predicate = predicatesOr.get(predicatesOr.size() - 1);
        predicatesOr.remove(predicatesOr.size() - 1);
        if (predicatesOr.size() > 0)
            return builder.or(criaClausulaOR(predicatesOr, builder), predicate);
        return predicate;
    }

    private Object checkType(String value) {
        if (value.equals("null")) {
            return null;
        }
        return checkBoolean(value);
    }

    private Object checkBoolean(String value) {
        if (nonNull(value)) {
            if ("true".equals(value.toLowerCase())) {
                return Boolean.TRUE;
            } else if ("false".equals(value.toLowerCase())) {
                return Boolean.FALSE;
            }
        }
        return value;
    }

    private Predicate condicao(String[] itens, CriteriaBuilder builder, Root<T> root) {
        Predicate predicate = null;
        if (itens.length == 3 && nonNull(itens[2]) && !itens[2].isBlank()) {
            if (itens[1].equals("=")) {
                predicate = builder.equal(this.getSearchColumn(itens[0], root),
                        nonNull(formatDate(itens[2])) ? formatDate(itens[2]) : getValueComparation(itens[0], itens[2]));
            } else if (itens[1].equals("<>") || itens[1].equals("&lt;>")) {
                predicate = builder.notEqual(this.getSearchColumn(itens[0], root),
                        nonNull(formatDate(itens[2])) ? formatDate(itens[2]) : getValueComparation(itens[0], itens[2]));

            } else if (itens[1].equals("<") || itens[1].equals("&lt;")) {
                if (nonNull(formatDate(itens[2])))
                    predicate = builder.lessThan((Expression) this.getSearchColumn(itens[0], root),
                            formatDate(itens[2]));
                else
                    predicate = builder.lessThan((Expression) this.getSearchColumn(itens[0], root),
                            itens[2]);
            } else if (itens[1].equals(">")) {
                if (nonNull(formatDate(itens[2])))
                    predicate = builder.greaterThan((Expression) this.getSearchColumn(itens[0], root),
                            formatDate(itens[2]));
                else
                    predicate = builder.greaterThan((Expression) this.getSearchColumn(itens[0], root),
                            itens[2]);
            } else if (itens[1].equals("<=") || itens[1].equals("&lt;=")) {
                if (nonNull(formatDate(itens[2])))
                    predicate = builder.lessThanOrEqualTo((Expression) this.getSearchColumn(itens[0], root),
                            formatDate(itens[2]));
                else
                    predicate = builder.lessThanOrEqualTo((Expression) this.getSearchColumn(itens[0], root),
                            itens[2]);
            } else if (itens[1].equals(">=")) {
                if (nonNull(formatDate(itens[2])))
                    predicate = builder.greaterThanOrEqualTo((Expression) this.getSearchColumn(itens[0], root),
                            formatDate(itens[2]));
                else
                    predicate = builder.greaterThanOrEqualTo((Expression) this.getSearchColumn(itens[0], root),
                            itens[2]);
            } else if (itens[1].equals("contains")) {
                predicate = builder.like(builder.lower((Expression) this.getSearchColumn(itens[0], root)),
                        "%" + itens[2].toLowerCase() + "%");
            } else if (itens[1].equals("notcontains")) {
                predicate = builder.notLike(builder.lower((Expression) this.getSearchColumn(itens[0], root)),
                        "%" + itens[2].toLowerCase() + "%");
            } else if (itens[1].equals("startswith")) {
                predicate = builder.like(builder.lower((Expression) this.getSearchColumn(itens[0], root)),
                        itens[2].toLowerCase() + "%");
            } else if (itens[1].equals("endswith")) {
                predicate = builder.like(builder.lower((Expression) this.getSearchColumn(itens[0], root)),
                        "%" + itens[2].toLowerCase());
            }
        } else if (itens.length == 2 && nonNull(itens[1]) && !itens[1].isBlank()) {
            predicate = builder.equal(this.getSearchColumn(itens[0], root),
                    nonNull(formatDate(itens[1])) ? formatDate(itens[1]) : getValueComparation(itens[0], itens[1]));
        }
        return predicate;
    }

    public Object getValueComparation(String campo, String valor) {
        return valor;
    }
}
