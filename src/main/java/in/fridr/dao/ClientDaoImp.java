package in.fridr.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.fridr.entity.QB_mapping;
import in.fridr.entity.UserDetails;

@Repository
public class ClientDaoImp implements ClientDao {
	@Autowired
	private EntityManager entityManager ;

	@Override
	public UserDetails getUserDetails(UserDetails userDetails) {
		
		//get the current hibernate session
		Session currentSession=entityManager.unwrap(Session.class);
		String mobile=userDetails.getMobile_number();
		String hql="SELECT s from UserDetails s where s.mobile_number=:mobile";
		UserDetails userRow=(UserDetails) currentSession.createQuery(hql).setParameter("mobile",userDetails.getMobile_number()).uniqueResult();
		
		if(userRow!=null) {
			userRow.setName(userDetails.getName());
			userRow.setDob(userDetails.getDob());
			userRow.setEmail(userDetails.getEmail());
			userRow.setGender(userDetails.getGender());
			currentSession.saveOrUpdate(userRow);
		}
		System.out.println("userRow is "+userRow.getName());
		return userRow;
	}

	@Override
	public void saveQBMapping(QB_mapping qb) {
		
		//get the current hibernate session
				Session currentSession=entityManager.unwrap(Session.class);
				String hql="INSERT INTO afcat_qb_mapping(qb_desc,set1_qno,set2_qno,set3_qno,set4_qno) values (?,?,?,?,?)";
				
				 currentSession.createNativeQuery(hql)
						 .setParameter(1,qb.getQb_desc())
						 .setParameter(2,qb.getSet1_qno())
						 .setParameter(3,qb.getSet2_qno())
						 .setParameter(4,qb.getSet3_qno())
						 .setParameter(5,qb.getSet4_qno())
						 .executeUpdate();
				 System.out.println("DB UPdated"+qb.toString());
	}

}
