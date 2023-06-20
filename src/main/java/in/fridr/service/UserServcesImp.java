package in.fridr.service;
import java.util.ArrayList;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import in.fridr.entity.Login_temp;
import in.fridr.shared.userDTO;


@Service
public class UserServcesImp implements UserServices {
	@Autowired
	private LoginService loginService;

	public UserServcesImp() {
	
	}



	@Override
	public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Login_temp userEntity=loginService.findBymobile_number(mobile);
		if(userEntity==null) {
			throw new UsernameNotFoundException(mobile);
		}
		return new User(userEntity.getMobile_number(),userEntity.getOtp(),true,true,true,true,new ArrayList<>());
	}

	@Override
	public userDTO getUserDetailsByEmail(String mobile_number) {
		// TODO Auto-generated method stub
		Login_temp userEntity=loginService.findBymobile_number(mobile_number);
		if(userEntity==null) {
			throw new UsernameNotFoundException(mobile_number);
		}
		return new ModelMapper().map(userEntity, userDTO.class) ;
	}

}
