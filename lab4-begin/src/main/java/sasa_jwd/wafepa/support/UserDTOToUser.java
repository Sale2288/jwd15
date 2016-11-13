package sasa_jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import sasa_jwd.wafepa.model.User;
import sasa_jwd.wafepa.service.UserService;
import sasa_jwd.wafepa.web.dto.UserDTO;

@Component
public class UserDTOToUser implements Converter<UserDTO, User> {

	@Autowired
	private UserService userService;
	
	@Override
	public User convert(UserDTO dto) {
		
		User user = null;
		
		if (dto.getId() != null) {
			user = userService.findOne(dto.getId());
		}
		else {
			user = new User();
		}
	
		user.setId(dto.getId());
		user.setEmail(dto.getEmail());
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setPassword(dto.getPassword());
	
		return user;
	}
	
	public List<User> convert(List<UserDTO> dtos) {
		
		List<User> users = new ArrayList<>();
		
		for (UserDTO dto : dtos) {
			users.add(convert(dto));
		}
		
		return users;
	}

}
