package sasa_jwd.wafepa.web.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sasa_jwd.wafepa.model.Activity;
import sasa_jwd.wafepa.service.ActivityService;
import sasa_jwd.wafepa.support.ActivityDTOtoActivity;
import sasa_jwd.wafepa.support.ActivityToActivityDTO;
import sasa_jwd.wafepa.web.dto.ActivityDTO;

@RestController
@RequestMapping(value="/api/activities")
public class ApiActivityController {
	
	
	@Autowired
	private ActivityService activityService;
	@Autowired
	private ActivityToActivityDTO toDTO;
	@Autowired
	private ActivityDTOtoActivity toActivity;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ActivityDTO>> getActivities() {
		
		List<Activity> activities = activityService.findAll();
		if (activities == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<ActivityDTO>>(toDTO.convert(activities), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<ActivityDTO> getActivity(@PathVariable Long id) {
		
		Activity activity = activityService.findOne(id);
		if (activity == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else
			return new ResponseEntity<ActivityDTO>(toDTO.convert(activity), HttpStatus.OK);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, params = "name")
	public ResponseEntity<List<ActivityDTO>> getActivityByName(@RequestParam String name) {
		
		List<Activity> ret = activityService.findByName(name);
		if (ret.size() == 0 || ret == null) 
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<List<ActivityDTO>>(toDTO.convert(ret), HttpStatus.OK);
	}
	
	
	@RequestMapping(method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<ActivityDTO> saveActivity(@RequestBody ActivityDTO toSaveDTO) {
		Activity a = activityService.save(toActivity.convert(toSaveDTO));
		return new ResponseEntity<ActivityDTO>(toDTO.convert(a), HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<ActivityDTO> editActivity(@RequestBody ActivityDTO dto, @PathVariable Long id) {
		
		if (dto.getId() != id)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		else {
			Activity persisted = activityService.save(toActivity.convert(dto));
			return new ResponseEntity<ActivityDTO>(toDTO.convert(persisted), HttpStatus.OK);	
		}
		
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ActivityDTO> removeActivity(@PathVariable Long id) {
		Activity deleted = activityService.findOne(id);
		
		if (deleted == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		else {
			activityService.remove(id);
			return new ResponseEntity<>(toDTO.convert(deleted), HttpStatus.OK);
		}
		
	}
	
	
	
	@PostConstruct
	public void setUp() {
		activityService.save(new Activity("Running"));
		activityService.save(new Activity("Swimming"));
		activityService.save(new Activity(5L, "Hiking"));
		activityService.save(new Activity("Dancing"));
	}

}
