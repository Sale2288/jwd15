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

import sasa_jwd.wafepa.model.User;
import sasa_jwd.wafepa.service.UserService;
import sasa_jwd.wafepa.support.UserDTOToUser;
import sasa_jwd.wafepa.support.UserToUserDTO;
import sasa_jwd.wafepa.web.dto.UserDTO;

@RestController
@RequestMapping(value = "/api/users")
public class ApiUserController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserDTOToUser toUser;
	@Autowired
	private UserToUserDTO toDTO;

	// --- FIND ALL ---
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> getAll() {

		List<User> users = userService.findAll();
		if (users == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else {
			return new ResponseEntity<List<UserDTO>>(toDTO.convert(users), HttpStatus.OK);
		}
	}

	// --- FIND ONE ---
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
		User user = userService.findOne(id);
		if (user == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else {
			return new ResponseEntity<UserDTO>(toDTO.convert(user), HttpStatus.OK);
		}
	}

	// --- FIND BY NAME ---
	@RequestMapping(method = RequestMethod.GET, params = "name")
	public ResponseEntity<List<UserDTO>> getUserByName(@RequestParam String name) {
		List<User> users = userService.findByName(name);
		if (users == null || users.size() == 0)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else {
			return new ResponseEntity<>(toDTO.convert(users), HttpStatus.OK);
		}
	}

	// --- ADD USER ---
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<UserDTO> add(@RequestBody UserDTO dto) {

		User u = userService.save(toUser.convert(dto));
		return new ResponseEntity<>(toDTO.convert(u), HttpStatus.CREATED);
		
	}

	// --- EDIT USER ---
	@RequestMapping(value="/{id}", method = RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<UserDTO> edit(@PathVariable Long id, @RequestBody UserDTO dto) {

		if (dto.getId() != id)
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		else {
			User u = userService.save(toUser.convert(dto));
			return new ResponseEntity<>(toDTO.convert(u), HttpStatus.OK);
		}
	}
	
	// --- DELETE USER ---
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> removeUser(@PathVariable Long id) {

		if (id == null) 
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		else {
			userService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	
	
	
	// PROBA ZA SVN PROBA ZA SVN
	
	
	@PostConstruct
	public void setUp() {
		userService.save(new User("test", "testic", "test.mest@mail.com", "testena"));
		userService.save(new User("mast", "mastic", "mast.tast@mail.com", "mastina"));
		userService.save(new User(5L, "san", "sanic", "san.sanic@mail.com", "sanina"));
	}

}
