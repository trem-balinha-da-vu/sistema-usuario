package sistemausuario.repository.typeuserbase;

import java.util.List;

import sistemausuario.model.typeuserbase.TypeUserBase;
import sistemausuario.repository.commom.Page;

public interface TypeUserBaseFilterRepository {

	Page<List<TypeUserBase>> findAll(Integer skip, Integer take, String filter, String sort);

}
