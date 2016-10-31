package jwd.wafepa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jwd.wafepa.model.Activity;
import jwd.wafepa.service.ActivityService;

public class InMemoryActivitySerivce implements ActivityService {
	
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
		
		return activities.put(activity.getId(),activity);
	}

	@Override
	public void remove(Long id) {
		activities.remove(id);
	}

}
