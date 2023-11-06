package sistemausuario.mapper.userbase;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sistemausuario.mapper.typeuserbase.TypeUserBaseMapper;
import sistemausuario.model.typeuserbase.TypeUserBase;
import sistemausuario.model.userbase.UserBase;
import sistemausuario.request.userbase.UserBaseRequest;
import sistemausuario.response.userbase.UserBaseResponse;

import static java.util.Objects.nonNull;

@Component
public class UserBaseMapper {
	
	@Autowired
	private TypeUserBaseMapper typeUserBaseMapper;

	public UserBase create (UserBaseRequest request, TypeUserBase typeUserBase) {
		UserBase model = new UserBase();
		
		model.setAge(request.getAge());
		model.setCreatedIn(LocalDateTime.now());
		model.setEmail(request.getEmail());
		model.setFirstName(request.getFirstName());
		model.setLastName(request.getLastName());
		model.setPassword(request.getPassword());
		model.setTypeUserBase(typeUserBase);
		
		return model;
	}
	
	public UserBaseResponse response (UserBase model) {
		UserBaseResponse response = new UserBaseResponse();
		
		response.setAge(model.getAge());
		response.setEmail(model.getEmail());
		response.setName(model.getFirstName() + " " +  model.getLastName());
		response.setPassword(model.getPassword());
		response.setUuid(model.getUuid());
		response.setTypeUserBase(
			typeUserBaseMapper.response(model.getTypeUserBase())
		);
		
		return response;
	}
	
	public List<UserBaseResponse> response (List<UserBase> modelList){
		return modelList.stream().map(m -> response(m)).collect(Collectors.toList());
	}
	
	public UserBase update (String firstName, String lastName, UserBase model) {
		
		model.setFirstName(nonNull(firstName) ? firstName : model.firstName);
		model.setLastName(nonNull(lastName) ?lastName : model.lastName);
		
		return model;
	}
	
}
