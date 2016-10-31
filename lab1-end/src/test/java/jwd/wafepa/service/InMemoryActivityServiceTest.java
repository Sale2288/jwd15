package jwd.wafepa.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import jwd.wafepa.model.Activity;
import jwd.wafepa.service.impl.InMemoryActivitySerivce;

public class InMemoryActivityServiceTest {

	private ActivityService activityService = null;
	
	@Before
	public void нијеБитно(){
		activityService = new InMemoryActivitySerivce();
		
		activityService.save(new Activity("Running"));
		activityService.save(new Activity("Swimming"));
	}
	
	@Test
	public void testFindOne(){
		Activity running = activityService.findOne(1L);
		
		Assert.assertEquals("Running", running.getName());
	}
	
	@Test
	public void testFindAll(){
		List<Activity> all = activityService.findAll();
		
		Assert.assertNotNull(all);
		Assert.assertEquals(2, all.size());
	}
}
