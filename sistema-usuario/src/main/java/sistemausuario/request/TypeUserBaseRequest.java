package sistemausuario.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypeUserBaseRequest {

	@JsonProperty("nome")
	public String name;
	
}
