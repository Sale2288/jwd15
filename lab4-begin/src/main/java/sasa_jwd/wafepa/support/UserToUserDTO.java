package sasa_jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import sasa_jwd.wafepa.model.User;
import sasa_jwd.wafepa.web.dto.UserDTO;

@Component
public class UserToUserDTO implements Converter<User, UserDTO> {

	@Override
	public UserDTO convert(User user) {
		
		UserDTO dto = new UserDTO();
		
		dto.setId(user.getId());
		dto.setEmail(user.getEmail());
		dto.setFirstName(user.getFirstName());
		dto.setLastName(user.getLastName());
		dto.setPassword(user.getPassword());
		
		return dto;
	}
	
	public List<UserDTO> convert (List<User> users) {
		
		List<UserDTO> dtos = new ArrayList<>();
		
		for (User u : users) {
			dtos.add( convert(u) );
		}
		
		return dtos;
	}

}
