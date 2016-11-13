package sasa_jwd.wafepa.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sasa_jwd.wafepa.model.Activity;
import sasa_jwd.wafepa.model.User;
import sasa_jwd.wafepa.service.impl.InMemoryActivityService;

public class InMemoryActivityServiceTest {

	private ActivityService activityService = null;
	
	@Before
	public void setUp() {
		activityService = new InMemoryActivityService();
		
		activityService.save(new Activity("Running"));
		activityService.save(new Activity("Swimming"));
	}
	
	@Test
	public void testFindOne() {
		Activity running = activityService.findOne(1L);
		
		Assert.assertEquals("Running", running.getName());
	}
	
	@Test
	public void testFindAll() {
		List<Activity> activites = activityService.findAll();
		
		Assert.assertNotNull(activites);
		Assert.assertEquals(2, activites.size());
	}
	
	@Test
	public void testSave() {
		Activity saved = activityService.save(new Activity("Hiking"));
		List<Activity> activites = activityService.findAll();
		
		Assert.assertNotNull(activites);
		Assert.assertEquals(3L, (long) saved.getId());
		Assert.assertEquals(3, activites.size());
	}
	
	@Test
	public void testRemove() {
		activityService.remove(4L);
		
		List<Activity> activites = activityService.findAll();
		
		Assert.assertNotNull(activites);
		Assert.assertEquals(2, activites.size());
	}
	
	@Test
	public void testFindByName() {
		List<Activity> activites = activityService.findByName("Running");
		
		//Assert.assertNull(activity);
		Assert.assertEquals(1, activites.size());
	}
	
	@Test
	public void testRemoveList() {
		List<Long> ids = new ArrayList<>();
		ids.add(1L);
		ids.add(2L);
		
		activityService.remove(ids);
		List<Activity> activites = activityService.findAll();
		
		Assert.assertNotNull(activites);
		Assert.assertEquals(0, activites.size());
		
	}
	
}
