package sistemausuario.controller.typeuserbase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sistemausuario.controller.common.BaseController;
import sistemausuario.request.TypeUserBaseRequest;
import sistemausuario.response.typeuserbase.TypeUserBaseResponse;
import sistemausuario.service.typeuserbase.TypeUserBaseService;

@RestController
@RequestMapping("/tipo_usuario_base")
public class TypeUserBaseController extends BaseController {
	
	@Autowired
	private TypeUserBaseService service;
	
	@GetMapping(produces = PRODUCES_JSON)
	public List<TypeUserBaseResponse> getList (){
		return service.getList();
	}
	
	@GetMapping(path = "/{uuid}", produces = PRODUCES_JSON)
	public TypeUserBaseResponse getByUuid (@PathVariable("uuid") String uuid){
		return service.getByUuid(uuid);
	}
	
	@PostMapping(consumes = CONSUMES_JSON, produces = PRODUCES_JSON)
	public TypeUserBaseResponse create (@Validated @RequestBody TypeUserBaseRequest request) {
		return service.create(request);
	}

}
