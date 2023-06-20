package in.fridr.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.fridr.entity.Login_temp;

@Repository
public class LoginDaoImp implements LoginDao {
	
	@Autowired
	private EntityManager entityManager ;

	@Override
	public int saveOtp(String mobile_number, String otp) {
		// TODO Auto-generated method stub
		//get the current hibernate session
				Session currentSession=entityManager.unwrap(Session.class);
				String hql1="FROM Login_temp where mobile_number=:mobile_number";
				List<Login_temp> data= currentSession.createQuery(hql1).setParameter("mobile_number", mobile_number).list();
				
				if(data.size()==0)
				{
					
					String hql="INSERT INTO login_temp(mobile_number,otp) values (?,?)";
					
					 return currentSession.createNativeQuery(hql)
							 .setParameter(1,mobile_number)
							 .setParameter(2,otp)
							 .executeUpdate();
				}
				else {
					String hql="Update Login_temp set otp=:otp where mobile_number=:mobile_number";
					
					 return currentSession.createQuery(hql)
							 .setParameter("otp",otp)
							 .setParameter("mobile_number",mobile_number)
							 .executeUpdate();
				}
				
	}

	@Override
	public boolean authenticate(String mobile_number, String otp) {
		Session currentSession=entityManager.unwrap(Session.class);
		String hql1="FROM Login_temp where mobile_number=:mobile_number and otp=:otp";
		List<Login_temp> data= currentSession.createQuery(hql1).setParameter("mobile_number", mobile_number).setParameter("otp", otp).list();
		if(data.size()==1) {
			return true;
		}
		else {
			return false;
		}
		
	}

	@Override
	public boolean saveToLogin(String mobile_number) {
		Session currentSession=entityManager.unwrap(Session.class);
		String hql1="FROM UserDetails where mobile_number=:mobile_number";
		List<Login_temp> data= currentSession.createQuery(hql1).setParameter("mobile_number", mobile_number).list();
		
		if(data.size()==0)
		{
			
			String hql="INSERT INTO user_details(mobile_number) values (?)";
			int newCreatedId=currentSession.createNativeQuery(hql)
					 .setParameter(1,mobile_number)
					 .executeUpdate();
			if(newCreatedId==1) {
				return true;
			}
			else
				return false;
			
			
		}
		else {
			return true;
		}
	}

	@Override
	public Login_temp findBymobile_number(String mobile) {
		Session currentSession=entityManager.unwrap(Session.class);
		String hql1="FROM Login_temp where mobile_number=:mobile_number";
		Login_temp data= (Login_temp) currentSession.createQuery(hql1).setParameter("mobile_number", mobile).uniqueResult();
		return data;
	}

}
