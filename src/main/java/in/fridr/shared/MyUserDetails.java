package in.fridr.shared;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import in.fridr.entity.Login_temp;

public class MyUserDetails implements UserDetails{

	private Login_temp userEntity;
	
	
	public MyUserDetails() {
		
	}

	public MyUserDetails(Login_temp userEntity) {
		// TODO Auto-generated constructor stub
		this.userEntity=userEntity;
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return userEntity.getOtp();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userEntity.getMobile_number();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	
}
