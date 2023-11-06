package sistemausuario.controller.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 5704439789483924605L;
	
	private HttpStatus status;

	private String message;

	public BaseException(HttpStatus status, String message) {
		super(message);
		this.message = message;
		this.status = status;
	}

}
