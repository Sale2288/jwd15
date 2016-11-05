package jwd.wafepa.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.wafepa.model.Activity;
import jwd.wafepa.model.User;
import jwd.wafepa.service.UserService;
import jwd.wafepa.support.UserDTOToUser;
import jwd.wafepa.support.UserRegistrationDTOtoUser;
import jwd.wafepa.support.UserToUserDTO;
import jwd.wafepa.support.UserToUserRegistrationDTO;
import jwd.wafepa.web.dto.UserDTO;
import jwd.wafepa.web.dto.UserRegistrationDTO;

@RestController
@RequestMapping(value = "/api/users")
public class ApiUserController {
	@Autowired
	UserService userService;

	@Autowired
	UserDTOToUser toUser;

	@Autowired
	UserToUserDTO toDTO;

	@Autowired
	UserRegistrationDTOtoUser toUserFromRegistration;

	@Autowired
	UserToUserRegistrationDTO toRegistrationDTO;

	// GET USERS
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<UserDTO>> getUser() {
		List<User> users = userService.findAll();

		if (users == null || users.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(users), HttpStatus.OK);
	}
	
	
	// GET USERS BY NAME
	@RequestMapping(method = RequestMethod.GET, params="name")
	ResponseEntity<List<UserDTO>> getUser(@RequestParam String name) {
		
		List<User> users = userService.findByName(name);

		if (users == null || users.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(users), HttpStatus.OK);
	}
	

	// GET USER
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
		User user = userService.findOne(id);
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toDTO.convert(user), HttpStatus.OK);
	}

	// DELETE
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<User> delete(@PathVariable Long id) {
		userService.delete(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	// ADD
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<UserDTO> add(@RequestBody UserRegistrationDTO newUser) {

		if (!newUser.getPassword1().equals(newUser.getPassword2())) {
			System.out.println("Nisu vam iste sifre");
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} else {

			User savedUser = userService.save(toUserFromRegistration.convert(newUser));
			return new ResponseEntity<>(toDTO.convert(savedUser), HttpStatus.CREATED);
		}
	}

	// EDIT
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<UserDTO> edit(@RequestBody UserDTO userDTO, @PathVariable Long id) {

		if (id != userDTO.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		User persisted = userService.save(toUser.convert(userDTO));

		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}

}
