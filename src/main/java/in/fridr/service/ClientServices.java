package in.fridr.service;

import in.fridr.entity.QB_mapping;
import in.fridr.entity.UserDetails;

public interface ClientServices {

	UserDetails getUserDetails(UserDetails userDetails);

	void saveQBMapping(QB_mapping qb);

}
