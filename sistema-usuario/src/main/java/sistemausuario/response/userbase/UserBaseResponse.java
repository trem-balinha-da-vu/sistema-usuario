package sistemausuario.response.userbase;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import sistemausuario.response.typeuserbase.TypeUserBaseResponse;

@Getter
@Setter
public class UserBaseResponse {

	@JsonProperty("Nome completo")
	public String name;
	
	@JsonProperty("idade")
	public Integer age;
	
	@JsonProperty("tipoUsuario")
	public TypeUserBaseResponse typeUserBase;
	
	@JsonProperty("senha")
	public String password;
	
	@JsonProperty("email")
	public String email;
	
	public String uuid;
}
