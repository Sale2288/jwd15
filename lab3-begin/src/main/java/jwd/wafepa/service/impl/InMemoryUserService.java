package jwd.wafepa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import jwd.wafepa.model.User;
import jwd.wafepa.service.UserService;

@Service
public class InMemoryUserService implements UserService {

	private Map<Long, User> data = new HashMap<>();
	private Long idCounter = 1L;

	@PostConstruct
	public void setUp() {
		save(new User("pera.peric@gmail.com", "perica", "pera", "peric"));
		save(new User("laza.lazic@gmail.com", "lazica", "laza", "lazic"));
		save(new User("jecka.jeckic@gmail.com", "jeckica", "jecka", "jeckic"));
	}
	
	@Override
	public User findOne(Long id) {
		return data.get(id);
	}

	@Override
	public List<User> findAll() {
		return new ArrayList<>(data.values());
	}

	@Override
	public User save(User user) {
		if (user.getId() == null) {
			user.setId(idCounter);
			idCounter++;
		}
		data.put(user.getId(), user);
		return user;
	}

	@Override
	public void delete(Long id) throws IllegalArgumentException {
		User user = data.remove(id);
		if (user == null) {
			throw new IllegalArgumentException("Removing unexisting user with id=" + id);
		}
	}
	
	@Override
	public List<User> findByName(String name) {
		List<User> users = new ArrayList<>();
		
		for (User user: data.values()) {
			if (user.getFirstname().equals(name)) {
				users.add(user);
			}
		}
		
		return users;
	}

	@Override
	public List<User> save(List<User> users) {
		List<User> ret = new ArrayList<>();
		
		for (User u: users) {
			User saved = save(u);
			if (saved != null) {
				ret.add(saved);
			}
		}
		
		return ret;
	}

}
