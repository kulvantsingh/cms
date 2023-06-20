package in.fridr.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.fridr.dao.LoginDao;
import in.fridr.entity.Login_temp;

@Service
@Transactional
public class LoginServiceImp implements LoginService {

	@Autowired
	private LoginDao loginDao;

	@Override
	public int saveOtp(String mobile_number, String otp) {
		// TODO Auto-generated method stub
		return loginDao.saveOtp(mobile_number,otp);
	}

	@Override
	public boolean authenticate(String mobile_number, String otp) {
		// TODO Auto-generated method stub
		return loginDao.authenticate(mobile_number,otp);
	}

	@Override
	public boolean saveToLogin(String mobile_number) {
		// TODO Auto-generated method stub
		return loginDao.saveToLogin(mobile_number);
	}

	@Override
	public Login_temp findBymobile_number(String mobile) {
		// TODO Auto-generated method stub
		return loginDao.findBymobile_number( mobile);
	}

}
