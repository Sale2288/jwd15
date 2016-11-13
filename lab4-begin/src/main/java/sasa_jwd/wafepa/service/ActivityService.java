package sasa_jwd.wafepa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sasa_jwd.wafepa.model.Activity;


public interface ActivityService {
	Activity findOne(Long id);
	List<Activity> findByName(String name);
	List<Activity> findAll();
	Activity save(Activity activity);
	List<Activity> saveAll(List<Activity> activities);
	void remove(Long id);
	void remove(List<Long> ids);
	
}
