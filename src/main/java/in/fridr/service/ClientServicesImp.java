package in.fridr.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.fridr.dao.ClientDao;
import in.fridr.entity.QB_mapping;
import in.fridr.entity.UserDetails;

@Service
@Transactional
public class ClientServicesImp implements ClientServices {

	@Autowired
	private ClientDao clientDao;
	
	@Override
	public UserDetails getUserDetails(UserDetails userDetails) {
		// TODO Auto-generated method stub
		return clientDao.getUserDetails(userDetails);
	}

	@Override
	public void saveQBMapping(QB_mapping qb) {
		// TODO Auto-generated method stub
		clientDao.saveQBMapping(qb);
		
	}

}
