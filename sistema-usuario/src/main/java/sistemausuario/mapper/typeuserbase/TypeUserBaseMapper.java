package sistemausuario.mapper.typeuserbase;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import sistemausuario.model.typeuserbase.TypeUserBase;
import sistemausuario.request.TypeUserBaseRequest;
import sistemausuario.response.typeuserbase.TypeUserBaseResponse;

@Component
public class TypeUserBaseMapper {

	public TypeUserBaseResponse response (TypeUserBase model) {
		TypeUserBaseResponse response = new TypeUserBaseResponse();
		
		response.setName(model.getName());
		response.setUuid(model.getUuid());
		
		return response;
	}
	
	public List<TypeUserBaseResponse> response (List<TypeUserBase> modelList){
		return modelList.stream().map(m -> response(m)).collect(Collectors.toList());
	}
	
	public TypeUserBase create (TypeUserBaseRequest request) {
		TypeUserBase model = new TypeUserBase();
		
		model.setName(request.getName());
		
		return model;
	}
	
}
