package sistemausuario.repository.typeuserbase;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sistemausuario.model.typeuserbase.TypeUserBase;

@Repository
public interface TypeUserBaseRepository extends JpaRepository<TypeUserBase, Long>{
	
	TypeUserBase findByUuid(String uuid);
	
	List<TypeUserBase> findAll();

}
