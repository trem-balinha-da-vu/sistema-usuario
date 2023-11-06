package sistemausuario.request.userbase;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NonNull;
import sistemausuario.request.generic.GenericUuidRequest;

@Getter
public class UserBaseRequest {
	
	@JsonProperty("primeiro nome")
	public String firstName;
	
	@JsonProperty("sobrenome")
	public String lastName;
	
	@JsonProperty("idade")
	public Integer age;
	
	@JsonProperty("tipoUsuario")
	public GenericUuidRequest typeUserBase;
	
	@JsonProperty("senha")
	public String password;
	
	@JsonProperty("email")
	public String email;
}
