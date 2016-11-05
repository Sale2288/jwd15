package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.User;
import jwd.wafepa.web.dto.UserRegistrationDTO;

@Component
public class UserToUserRegistrationDTO implements Converter<User, UserRegistrationDTO> {

	@Override
	public UserRegistrationDTO convert(User user) {
		UserRegistrationDTO dto = new UserRegistrationDTO();
		
		dto.setId(user.getId());
		dto.setEmail(user.getEmail());
		dto.setFirstname(user.getFirstname());
		dto.setLastname(user.getLastname());
		dto.setPassword1(user.getPassword());
		dto.setPassword2(user.getPassword());
		
		return dto;
	}
	
	public List<UserRegistrationDTO> convert(List<User> users) {
		List<UserRegistrationDTO> dtos = new ArrayList<>();
		
		for (User u: users) {
			dtos.add(convert(u));
		}
		
		return dtos;
	}

}
