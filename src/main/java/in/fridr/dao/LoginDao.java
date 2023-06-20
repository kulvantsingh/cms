package in.fridr.dao;

import org.springframework.stereotype.Repository;

import in.fridr.entity.Login_temp;


public interface LoginDao {

	int saveOtp(String mobile_number, String otp);

	boolean authenticate(String mobile_number, String otp);

	boolean saveToLogin(String mobile_number);

	Login_temp findBymobile_number(String mobile);

}
