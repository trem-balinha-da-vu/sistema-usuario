package sistemausuario.controller.common;

import org.springframework.http.MediaType;

public class BaseController {

	protected static final String PRODUCES_JSON = MediaType.APPLICATION_JSON_VALUE;
	protected static final String CONSUMES_JSON = MediaType.APPLICATION_JSON_VALUE;
	protected static final String CONSUMES_ALL = MediaType.ALL_VALUE;

}
