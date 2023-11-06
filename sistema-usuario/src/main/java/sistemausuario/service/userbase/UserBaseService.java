package sistemausuario.service.userbase;

import java.util.List;

import sistemausuario.request.userbase.UserBaseRequest;
import sistemausuario.response.userbase.UserBaseResponse;

public interface UserBaseService {

	UserBaseResponse create (UserBaseRequest request);
	
	UserBaseResponse getByUuid (String uuid);
	
	List<UserBaseResponse> getList ();
	
	UserBaseResponse update(UserBaseRequest request, String uuid);
	
	void delete(String uuid);
	
}
