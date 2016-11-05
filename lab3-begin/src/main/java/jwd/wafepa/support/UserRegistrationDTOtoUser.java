package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.User;
import jwd.wafepa.service.UserService;
import jwd.wafepa.web.dto.UserRegistrationDTO;

@Component
public class UserRegistrationDTOtoUser implements Converter<UserRegistrationDTO, User> {

	@Autowired
	private UserService userService;
	
	@Override
	public User convert(UserRegistrationDTO dto) {
		User user = null;
		
		if (dto.getId() != null) {
			user = userService.findOne(dto.getId());
		}
		else {
			user = new User();
		}
		
		user.setId(dto.getId());
		user.setEmail(dto.getEmail());
		user.setFirstname(dto.getFirstname());
		user.setLastname(dto.getLastname());
		user.setPassword(dto.getPassword1());
		
		
		/*if (dto.getPassword1().equals(dto.getPassword2())) {
			user.setPassword(dto.getPassword1());
		}
		else throw exception 
		*/
		return user;
	}
	
	public List<User> convert(List<UserRegistrationDTO> dtos) {
		List<User> users = new ArrayList<>();
		
		for (UserRegistrationDTO dto: dtos) {
			users.add( convert(dto) );
		}
		
		return users;
	}
	

}
