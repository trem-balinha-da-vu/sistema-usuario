package sistemausuario.service.impl.typeuserbase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sistemausuario.mapper.typeuserbase.TypeUserBaseMapper;
import sistemausuario.model.typeuserbase.TypeUserBase;
import sistemausuario.repository.typeuserbase.TypeUserBaseRepository;
import sistemausuario.request.TypeUserBaseRequest;
import sistemausuario.response.typeuserbase.TypeUserBaseResponse;
import sistemausuario.service.typeuserbase.TypeUserBaseService;

@Service
public class TypeUserBaseServiceImpl implements TypeUserBaseService{
	
	@Autowired
	private TypeUserBaseMapper mapper;
	
	@Autowired
	private TypeUserBaseRepository repository;

	@Override
	public TypeUserBaseResponse create(TypeUserBaseRequest request) {
		
		TypeUserBase model = mapper.create(request);
		repository.save(model);
		TypeUserBaseResponse response = mapper.response(model);
		return response;
	}

	@Override
	public List<TypeUserBaseResponse> getList() {
		List<TypeUserBase> list = repository.findAll();
		List<TypeUserBaseResponse> response = mapper.response(list);
		return response;
	}

	@Override
	public TypeUserBaseResponse getByUuid(String uuid) {
		TypeUserBase model = repository.findByUuid(uuid);
		TypeUserBaseResponse response = mapper.response(model);
		
		return response;
	}

}
