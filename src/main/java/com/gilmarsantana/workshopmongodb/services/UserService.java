package com.gilmarsantana.workshopmongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gilmarsantana.workshopmongodb.domain.User;
import com.gilmarsantana.workshopmongodb.dto.UserDTO;
import com.gilmarsantana.workshopmongodb.repository.UserRepository;
import com.gilmarsantana.workshopmongodb.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){		
		return repository.findAll();
	}
	
	public User findById(String id){		
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User insert (User obj) {
		return repository.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
	
	public User update(User newObj) {
		User oldObj = findById(newObj.getId());
		updatData(newObj, oldObj);
		return repository.save(newObj);
	}

	private void updatData(User newObj, User oldObj) {
		oldObj.setName(newObj.getName());
		oldObj.setEmail(newObj.getEmail());
	}
	
}
