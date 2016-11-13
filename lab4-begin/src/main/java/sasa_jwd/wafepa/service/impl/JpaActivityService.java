package sasa_jwd.wafepa.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sasa_jwd.wafepa.model.Activity;
import sasa_jwd.wafepa.repository.ActivityRepository;
import sasa_jwd.wafepa.service.ActivityService;

@Service
@Transactional
public class JpaActivityService implements ActivityService {

	@Autowired
	private ActivityRepository activityRepository;
	
	/*@Autowired
	private ActivityService activityService;
*/
	@Override
	public Activity findOne(Long id) {
		
		return activityRepository.findOne(id);
	}

	public List<Activity> findByName(String name) {
		
		return activityRepository.findByName(name);
	}

	@Override
	public List<Activity> findAll() {

		return activityRepository.findAll();
	}

	@Override
	public Activity save(Activity activity) {
		
		return activityRepository.save(activity);
	}

	@Override
	public List<Activity> saveAll(List<Activity> activities) {
		
		return activityRepository.save(activities);
	}

	@Override
	public void remove(Long id) {
		
		Activity a = activityRepository.findOne(id);
		if (a != null)
			activityRepository.delete(a);
	}

	@Override
	public void remove(List<Long> ids) {
		
		for (Long id : ids) 
			remove(id);
		
	}

	@PostConstruct
	public void init(){
		save(new Activity("Swimming"));
		save(new Activity("Running"));
	}
	
}
