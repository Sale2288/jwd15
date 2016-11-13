package sasa_jwd.wafepa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import sasa_jwd.wafepa.model.Activity;
import sasa_jwd.wafepa.service.ActivityService;



public class InMemoryActivityService implements ActivityService {
	private Map<Long, Activity> activities = new HashMap<>();
	private long nextId = 1;

	@Override
	public Activity findOne(Long id) {
		
		return activities.get(id);
	}

	@Override
	public ArrayList<Activity> findAll() {
//		List<Activity> ret = new ArrayList<>();
//		
//		for(Activity activity : activities.values()){
//			 ret.add(activity);
//		}
//		
//		return ret;
		
		return new ArrayList<>(activities.values());
	}

	@Override
	public Activity save(Activity activity) {
		if(activity.getId()==null){
			activity.setId(nextId++);
		}
		
		activities.put(activity.getId(),activity);
		return activity;
				
	}

	@Override
	public void remove(Long id) {
		activities.remove(id);
	}

	@Override
	public List<Activity> findByName(String name) {
		List<Activity> ret = new ArrayList<>();
		for (Activity a : activities.values()) {
			if (a.getName().equals(name))
				ret.add(a);
		}
		
		return ret;
	}

	@Override
	public List<Activity> saveAll(List<Activity> activitiesToSave) {
		if (activitiesToSave == null)
			return null;
		else {
			List<Activity> ret = new ArrayList<>();
			for (Activity a : activitiesToSave) {
				save(a);
				ret.add(a);
			}
			return ret;
		}
	}

	@Override
	public void remove(List<Long> ids) {
		for (Long id : ids) {
			if (activities.containsKey(id))
				activities.remove(id);
		}
		
	}


}
