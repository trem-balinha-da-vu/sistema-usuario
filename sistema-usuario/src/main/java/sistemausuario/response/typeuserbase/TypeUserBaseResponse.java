package sistemausuario.response.typeuserbase;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypeUserBaseResponse {

	@JsonProperty("nome")
	public String name;
	
	public String uuid;
	
}
