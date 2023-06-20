package in.fridr.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.fridr.entity.Login_temp;
import in.fridr.shared.MyUserDetails;
import javassist.NotFoundException;

@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private LoginService loginService;
	@Override
	public UserDetails loadUserByUsername(String mobile_number) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Login_temp userEntity=loginService.findBymobile_number(mobile_number);
		
		if(userEntity==null) {
			throw new UsernameNotFoundException("Mobile number is not registered");
		
		}
		
		return new MyUserDetails(userEntity);
		
	}
	

}
