package sistemausuario.response.common;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseReturn<T> implements Serializable  {

	private static final long serialVersionUID = -6391803126640930742L;

	public BaseReturn() {
		this.success = true;
		this.description = null;
		this.error = null;
		this.data = null;
	}

	public BaseReturn(T data) {
		this.success = true;
		this.description = null;
		this.error = null;
		this.data = data;
	}

	@JsonProperty("sucesso")
	public Boolean success;

	@JsonProperty("erro")
	public String error;

	@JsonProperty("descricao")
	public String description;

	@JsonProperty("dados")
	public T data;

}
