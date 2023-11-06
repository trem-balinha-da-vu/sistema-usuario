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
import sistemausuario.request.typeuserbase.TypeUserBaseRequest;
import sistemausuario.response.common.BaseReturn;
import sistemausuario.response.typeuserbase.TypeUserBaseResponse;
import sistemausuario.service.typeuserbase.TypeUserBaseService;

@RestController
@RequestMapping("/tipo_usuario_base")
public class TypeUserBaseController extends BaseController {
	
	@Autowired
	private TypeUserBaseService service;
	
	@GetMapping(produces = PRODUCES_JSON)
	public BaseReturn<List<TypeUserBaseResponse>> getList (){
		
		List<TypeUserBaseResponse> response = service.getList();
		
		return new BaseReturn<>(response);
	}
	
	@GetMapping(path = "/{uuid}", produces = PRODUCES_JSON)
	public BaseReturn<TypeUserBaseResponse> getByUuid (@PathVariable("uuid") String uuid){
		
		TypeUserBaseResponse response = service.getByUuid(uuid);
		
		return new BaseReturn<>(response);
	}
	
	@PostMapping(consumes = CONSUMES_JSON, produces = PRODUCES_JSON)
	public BaseReturn<TypeUserBaseResponse> create (@Validated @RequestBody TypeUserBaseRequest request) {
		
		TypeUserBaseResponse response = service.create(request);
		
		return new BaseReturn<>(response);
	}

}
