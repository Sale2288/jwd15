package sasa_jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import sasa_jwd.wafepa.model.Activity;
import sasa_jwd.wafepa.service.ActivityService;
import sasa_jwd.wafepa.web.dto.ActivityDTO;

@Component
public class ActivityDTOtoActivity implements Converter<ActivityDTO, Activity> {

	@Autowired
	private ActivityService activityService;
	
	@Override
	public Activity convert(ActivityDTO dto) {
		Activity activity = null;
		
		if (dto.getId() != null) {
			activity = activityService.findOne(dto.getId());
		}
		else {
			activity = new Activity();
		}

		activity.setId(dto.getId());
		activity.setName(dto.getName());
		
		return activity;
	}
	
	public List<Activity> convert(List<ActivityDTO> dtos) {
		
		List<Activity> activities = new ArrayList<>();
		
		for (ActivityDTO dto : dtos) {
			activities.add(convert(dto));
		}
		
		return activities;
	}

}
