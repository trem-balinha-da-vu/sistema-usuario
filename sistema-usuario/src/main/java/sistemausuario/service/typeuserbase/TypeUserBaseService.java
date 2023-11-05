package sistemausuario.service.typeuserbase;

import java.util.List;

import sistemausuario.request.TypeUserBaseRequest;
import sistemausuario.response.typeuserbase.TypeUserBaseResponse;

public interface TypeUserBaseService {

	TypeUserBaseResponse create (TypeUserBaseRequest request);
	
	List<TypeUserBaseResponse> getList();
	
	TypeUserBaseResponse getByUuid (String uuid);
	
}
