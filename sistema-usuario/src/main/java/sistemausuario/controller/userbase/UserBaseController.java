package sistemausuario.controller.userbase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sistemausuario.controller.common.BaseController;
import sistemausuario.request.userbase.UserBaseRequest;
import sistemausuario.response.common.BaseReturn;
import sistemausuario.response.userbase.UserBaseResponse;
import sistemausuario.service.userbase.UserBaseService;

@RestController
@RequestMapping("/usuario_base")
public class UserBaseController extends BaseController{
	
	@Autowired
	private UserBaseService service;
	
	@GetMapping(produces = PRODUCES_JSON)
	public BaseReturn<List<UserBaseResponse>> getList (){
		
		List<UserBaseResponse> response  = service.getList();
		return new BaseReturn<>(response);
	}
	
	@GetMapping(path = "/{uuid}", produces = PRODUCES_JSON)
	public BaseReturn<UserBaseResponse> getByUuid (@PathVariable("uuid") String uuid){
		
		UserBaseResponse response = service.getByUuid(uuid);
		
		return new BaseReturn<>(response);
	}
	
	@PostMapping(consumes = CONSUMES_JSON, produces = PRODUCES_JSON)
	public BaseReturn<UserBaseResponse> create (@Validated @RequestBody UserBaseRequest request) {
		
		UserBaseResponse response = service.create(request);
		
		return new BaseReturn<>(response);
	}
	
	@PutMapping(path = "/{uuid}", consumes = CONSUMES_JSON, produces = PRODUCES_JSON)
	public BaseReturn<UserBaseResponse> update (@Validated @RequestBody UserBaseRequest request, @PathVariable("uuid") String uuid) {
		
		UserBaseResponse response = service.update(request, uuid);
		
		return new BaseReturn<>(response);
	}
	
	@DeleteMapping(path = "/{uuid}")
	public void delete (@PathVariable("uuid") String uuid) {
		service.delete(uuid);
	}
}
