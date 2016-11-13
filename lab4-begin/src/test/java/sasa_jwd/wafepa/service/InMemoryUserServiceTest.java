package sasa_jwd.wafepa.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sasa_jwd.wafepa.model.User;
import sasa_jwd.wafepa.service.impl.InMemoryUserService;

public class InMemoryUserServiceTest {
	
	private UserService userService;
	
	@Before
	public void setUp() {
		
		userService = new InMemoryUserService();
		userService.save(new User("test", "testic", "test.mest@mail.com", "testena"));
		userService.save(new User("mast", "mastic", "mast.tast@mail.com", "mastina"));
	}
	
	@Test
	public void testFindOne() {
		User test = userService.findOne(1L);
		
		Assert.assertEquals(test.getFirstName(), "test");
	}
	
	@Test
	public void testFindByName() {
		List<User> users = userService.findByName("test");
		
		Assert.assertNotNull(users);
		Assert.assertEquals(1, users.size());
	}
	
	@Test
	public void testSave() {
		User u = new User("prst", "prstic", "prst.prstic@mail.com", "prstina");
		userService.save(u);
		List<User> users = userService.findAll();
		
		Assert.assertNotNull(users);
		Assert.assertEquals(3, users.size());
	}
	
	@Test
	public void testSaveAll() {
		List<User> usersToSave = new ArrayList<>();
		usersToSave.add(new User("san", "sanic", "san.sanic@mail.com", "sanina"));
		usersToSave.add(new User("plan", "planic", "plan.planic@mail.com", "planina"));
		
		userService.saveAll(usersToSave);
		
		List<User> users = userService.findAll();
		
		Assert.assertNotNull(users);
		Assert.assertEquals(4, users.size());
	}
}
