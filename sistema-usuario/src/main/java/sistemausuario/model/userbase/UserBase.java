package sistemausuario.model.userbase;

import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sistemausuario.model.commom.BaseModel;
import sistemausuario.model.typeuserbase.TypeUserBase;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "usuario_base")
public class UserBase extends BaseModel<UserBase>{

	private static final long serialVersionUID = 8279141167808009830L;
	
	@Column(name = "idade", nullable = false)
	public Integer age;
	
	@Column(name = "primeiro_nome", nullable = false)
	public String firstName;
	
	@Column(name = "sobrenome", nullable = false)
	public String lastName;
	
	@Column(name = "email", nullable = false)
	public String email;
	
	@Column(name = "senha", nullable = false)
	public String password;
	
	@Column(name = "criado_em", nullable = false)
	public LocalDateTime createdIn;
	
	@ManyToOne
	@JoinColumn(name = "tipo_usuario_base_id", referencedColumnName = "id")
	public TypeUserBase typeUserBase;
	
}
