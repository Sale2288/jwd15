package sasa_jwd.wafepa.service;

import java.util.List;

import sasa_jwd.wafepa.model.User;

public interface UserService {
	
	User findOne(Long id);
	List<User> findByName(String name);
	List<User> findAll();
	User save(User user);
	List<User> saveAll(List<User> activities);
	void remove(Long id);
	void remove(List<Long> ids);
	
}
