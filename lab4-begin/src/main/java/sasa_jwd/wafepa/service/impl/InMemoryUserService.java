package sasa_jwd.wafepa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import sasa_jwd.wafepa.model.Activity;
import sasa_jwd.wafepa.model.User;
import sasa_jwd.wafepa.service.UserService;

// @Service
public class InMemoryUserService implements UserService {

	private HashMap<Long, User> users = new HashMap<>();
	private Long nextId = 1L;

	@Override
	public User findOne(Long id) {
		return users.get(id);
	}

	@Override
	public List<User> findByName(String name) {
		List<User> ret = new ArrayList<>();

		for (User u : users.values()) {
			if (u.getFirstName().equals(name))
				ret.add(u);
		}
		return ret;
	}

	@Override
	public List<User> findAll() {
		List<User> ret = new ArrayList<>(users.values());
		return ret;
	}

	@Override
	public User save(User user) {
		if (user.getId() == null) {
			user.setId(nextId++);
		}
		users.put(user.getId(), user);
		return user;
	}

	@Override
	public List<User> saveAll(List<User> usersToSave) {
		if (usersToSave == null)
			return null;
		else {
			List<User> ret = new ArrayList<>();
			for (User u : usersToSave) {
				save(u);
				ret.add(u);
			}
			return ret;
		}
	}

	@Override
	public void remove(Long id) {
		users.remove(id);
	}

	@Override
	public void remove(List<Long> ids) {
		for (Long id : ids) {
			users.remove(id);
		}

	}

}
