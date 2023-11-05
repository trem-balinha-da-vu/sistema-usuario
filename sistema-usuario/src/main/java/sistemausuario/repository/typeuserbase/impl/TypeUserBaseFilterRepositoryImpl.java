package sistemausuario.repository.typeuserbase.impl;


import org.springframework.stereotype.Repository;

import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import sistemausuario.model.typeuserbase.TypeUserBase;
import sistemausuario.repository.commom.GenericFilterRepositoryImpl;
import sistemausuario.repository.typeuserbase.TypeUserBaseFilterRepository;

@Repository
public class TypeUserBaseFilterRepositoryImpl extends GenericFilterRepositoryImpl<TypeUserBase> 
	implements TypeUserBaseFilterRepository{

	public TypeUserBaseFilterRepositoryImpl() {
		super(TypeUserBase.class);
	}
	
	@Override
	public Path<Object> getSortColumn(String value, Root<TypeUserBase> root) {
		switch (value) {
		case "nome":
			return root.get("name");
		}
		return null;
	}

	@Override
	public Path<Object> getSearchColumn(String value, Root<TypeUserBase> root) {
		switch (value) {
		case "uuid":
			return root.get("uuid");
		case "nome":
			return root.get("name");
		}
		return null;
	}

}
