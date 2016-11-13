package jwd.wafepa.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.wafepa.model.User;
import jwd.wafepa.repository.UserRepository;
import jwd.wafepa.service.UserService;

@Service
public class JpaUserService implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public User findOne(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public void delete(Long id) {
		User toDelete = userRepository.findOne(id);
		
		if (toDelete != null)
			userRepository.delete(toDelete);
	}
	
	/*@PostConstruct
	public void setUp() {
		save(new User("pera.peric@gmail.com", "perica", "pera", "peric"));
		save(new User("laza.lazic@gmail.com", "lazica", "laza", "lazic"));
		save(new User("jecka.jeckic@gmail.com", "jeckica", "jecka", "jeckic"));
	}*/

}
