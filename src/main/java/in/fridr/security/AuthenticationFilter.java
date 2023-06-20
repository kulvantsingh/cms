package in.fridr.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.fridr.entity.Login_temp;
import in.fridr.service.UserServices;
import in.fridr.shared.userDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private UserServices userServices;
	private Environment env;
	

	public AuthenticationFilter(UserServices userServices,Environment env,AuthenticationManager authenticationManager) {
		this.userServices=userServices;
		this.env=env;
		super.setAuthenticationManager(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			Login_temp creds = new ObjectMapper().readValue(request.getInputStream(), Login_temp.class);
			return getAuthenticationManager().authenticate(
					new UsernamePasswordAuthenticationToken(creds.getMobile_number(), creds.getOtp(), new ArrayList<>()));

		} catch (IOException e) {
			throw new RuntimeException();
		}

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, 
			FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		String userName=((User)auth.getPrincipal()).getUsername();
		userDTO userDetails=userServices.getUserDetailsByEmail(userName);
		String token=Jwts.builder()
					 .setSubject(userDetails.getMobile_number())
					 .setExpiration(new Date(System.currentTimeMillis()+Long.parseLong(env.getProperty("token.expiration_time"))))
					 .signWith(SignatureAlgorithm.HS512, env.getProperty("token.secret"))
					 .compact();
		response.addHeader("token", token);
		response.addHeader("userId", userDetails.getMobile_number());
		

	}
}
