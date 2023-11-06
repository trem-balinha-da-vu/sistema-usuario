package sistemausuario.service.impl.userbase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

import sistemausuario.controller.exception.BaseException;
import sistemausuario.mapper.userbase.UserBaseMapper;
import sistemausuario.model.typeuserbase.TypeUserBase;
import sistemausuario.model.userbase.UserBase;
import sistemausuario.repository.typeuserbase.TypeUserBaseRepository;
import sistemausuario.repository.userbase.UserBaseRepository;
import sistemausuario.request.userbase.UserBaseRequest;
import sistemausuario.response.userbase.UserBaseResponse;
import sistemausuario.service.userbase.UserBaseService;

@Service
public class UserBaseServiceImpl implements UserBaseService {
	
	@Autowired
	private UserBaseMapper mapper;
	
	@Autowired
	private TypeUserBaseRepository typeUserBaseRepository;
	
	@Autowired
	private UserBaseRepository repository;

	@Override
	public UserBaseResponse create(UserBaseRequest request) {
		
		TypeUserBase typeUserBase = getsTypeUserBase(request.typeUserBase.uuid);
		
		validateRequest(request);
		
		UserBase model = mapper.create(request, typeUserBase);
		
		repository.save(model);
		
		UserBaseResponse response = mapper.response(model);
		
		return response;
	}
	
	private void validateRequest (UserBaseRequest request) {
		if (repository.existsByEmail(request.email)) {
			throw new BaseException(HttpStatus.BAD_REQUEST, "Já existe usuário com esse email.");
		}
		if (request.age.compareTo(18) < 0 ) {
			throw new BaseException(HttpStatus.BAD_REQUEST, "Não é permitido usuário menor de idade.");
		}
	}

	private TypeUserBase getsTypeUserBase(String uuid) {
		TypeUserBase model = typeUserBaseRepository.findByUuid(uuid);
		
		if (isNull(model)) {
			throw new BaseException(HttpStatus.BAD_REQUEST, "tipo de usuário inválido.");
		}
		
		return model;
	}
	
	private UserBase getsUserBase(String uuid) {
		UserBase model = repository.findByUuid(uuid);
		
		if (isNull(model)) {
			throw new BaseException(HttpStatus.BAD_REQUEST, "usuário não encontrado.");
		}
		
		return model;
	}

	@Override
	public UserBaseResponse getByUuid(String uuid) {
		
		UserBase model = getsUserBase(uuid);
		
		UserBaseResponse response = mapper.response(model);
		
		return response;
	}

	@Override
	public List<UserBaseResponse> getList() {

		List<UserBase> list = repository.findAll();
		
		List<UserBaseResponse> response = mapper.response(list);
		
		return response;
	}

	@Override
	public UserBaseResponse update(UserBaseRequest request, String uuid) {

		UserBase model = getsUserBase(uuid);
		
		model = mapper.update(request.firstName, request.lastName, model);
		
		repository.save(model);
		
		UserBaseResponse response = mapper.response(model);
		
		return response;
	}

	@Override
	public void delete(String uuid) {
		
		UserBase model = getsUserBase(uuid);
		
		try{
            repository.delete(model);
        }
        catch (DataIntegrityViolationException e){
            throw new BaseException(HttpStatus.CONFLICT, "Não foi possivel deletar.");
        }
	}

}
