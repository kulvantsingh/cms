package in.fridr.dao;

import in.fridr.entity.QB_mapping;
import in.fridr.entity.UserDetails;

public interface ClientDao {


	UserDetails getUserDetails(UserDetails userDetails);

	void saveQBMapping(QB_mapping qb);

}
