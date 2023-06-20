package in.fridr.service;

import in.fridr.entity.Login_temp;
import in.fridr.entity.UserDetails;

public interface LoginService {

	int saveOtp(String mobile_number, String otp);

	boolean authenticate(String mobile_number, String otp);

	boolean saveToLogin(String mobile_number);

	Login_temp findBymobile_number(String mobile);

	

	
}
