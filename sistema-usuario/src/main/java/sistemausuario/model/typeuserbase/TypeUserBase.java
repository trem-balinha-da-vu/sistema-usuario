package sistemausuario.model.typeuserbase;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sistemausuario.model.commom.BaseModel;

@Entity
@Table(name = "tipo_usuario_base")
@NoArgsConstructor
@Getter
@Setter
public class TypeUserBase extends BaseModel<TypeUserBase> {
	
	private static final long serialVersionUID = 7594688515031196402L;
	
	@Column(name = "nome", nullable = false)
	public String name;
	
}
