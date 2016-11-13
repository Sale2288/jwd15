package sasa_jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import sasa_jwd.wafepa.model.Activity;
import sasa_jwd.wafepa.web.dto.ActivityDTO;

@Component
public class ActivityToActivityDTO implements Converter<Activity, ActivityDTO> {

	@Override
	public ActivityDTO convert(Activity activity){
		
		ActivityDTO dto = new ActivityDTO();
		
		dto.setId(activity.getId());
		dto.setName(activity.getName());
		
		return dto;
	}
	
	public List<ActivityDTO> convert(List<Activity> activities) {
		
		List<ActivityDTO> dtos= new ArrayList<>();
		
		for (Activity a : activities) {
			dtos.add(convert(a));
		}
		
		return dtos;
	}
			
}
