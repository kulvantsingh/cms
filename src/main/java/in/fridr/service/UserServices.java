package in.fridr.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import in.fridr.entity.UserDetails;
import in.fridr.shared.userDTO;



public interface UserServices extends UserDetailsService{
	userDTO getUserDetailsByEmail(String email);

}
