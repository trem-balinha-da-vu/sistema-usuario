package sistemausuario.repository.userbase;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sistemausuario.model.userbase.UserBase;

@Repository
public interface UserBaseRepository extends JpaRepository<UserBase, Long> {

	UserBase findByUuid(String uuid);
	
	List<UserBase> findAll();
	
	boolean existsByEmail(String email);

}
