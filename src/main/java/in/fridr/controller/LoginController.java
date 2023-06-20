package in.fridr.controller;

import java.sql.Timestamp;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import in.fridr.entity.Login_temp;
import in.fridr.modal.MessageResponse;
import in.fridr.response.AuthenticationResponse;
import in.fridr.service.LoginService;
import in.fridr.sms.TwilioUtility;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("login")
public class LoginController {
	@Autowired
	private Environment environment;
	@Autowired
	private LoginService loginService;

	@Autowired
	private AuthenticationManager authenticationManager;
	private String otp;
	
	@PostMapping("/getOtp")
	public ResponseEntity<MessageResponse> createOtp(@RequestBody Login_temp login_temp) {
		System.out.println("Otp request received");
		TwilioUtility twilloUtility=new TwilioUtility(environment);
		MessageResponse otp_response=new MessageResponse();
		try {
		otp=twilloUtility.generateOTP(4);
		if(twilloUtility.sendsms(login_temp.getMobile_number(),otp)) {
		int idCreated=loginService.saveOtp(login_temp.getMobile_number(),otp);
		otp_response.setMessage("otp sent successfully");
		
		
		}
		return new ResponseEntity<MessageResponse>(otp_response,HttpStatus.OK);
		}
		catch(Exception e) {
			otp_response.setMessage("error");
			return new ResponseEntity<MessageResponse>(otp_response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	@PostMapping("/authenticate1")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody Login_temp login_temp) {
		
		AuthenticationResponse authenticationResponse=new AuthenticationResponse();
		
		try {
			boolean authenticate=loginService.authenticate(login_temp.getMobile_number(),login_temp.getOtp());
		
			
			
		if(authenticate) {
				boolean isSavedToLogin=loginService.saveToLogin(login_temp.getMobile_number());
				
					authenticationResponse.setAuthenticated(true);
					
					return new ResponseEntity<AuthenticationResponse>(authenticationResponse,HttpStatus.OK);
				
				
		}
		else {
			authenticationResponse.setAuthenticated(false);
			return new ResponseEntity<AuthenticationResponse>(authenticationResponse,HttpStatus.UNAUTHORIZED);
		}
		
		}
		catch(Exception e) {
			return new ResponseEntity<AuthenticationResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate1(@Valid @RequestBody Login_temp login_temp,HttpServletRequest request, HttpServletResponse response,BindingResult br) throws Exception {
		
		AuthenticationResponse authenticationResponse=new AuthenticationResponse();
		try {
			
			//System.out.println(login_temp.getMobile_number()+" "+login_temp.getOtp());
		
			if(br.hasErrors()) 
			{
				authenticationResponse.setAuthenticated(false);
				return new ResponseEntity<AuthenticationResponse>(authenticationResponse,HttpStatus.UNAUTHORIZED);
			
			}
			
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login_temp.getMobile_number(), login_temp.getOtp()));
			
			String mobile=login_temp.getMobile_number();
			Login_temp userDetails=loginService.findBymobile_number(mobile);
			if(userDetails!=null) {
			String token=Jwts.builder()
						 .setSubject(userDetails.getMobile_number())
						 .setExpiration(new Date(System.currentTimeMillis()+Long.parseLong(environment.getProperty("token.expiration_time"))))
						 .signWith(SignatureAlgorithm.HS512, environment.getProperty("token.secret"))
						 .compact();
			response.addHeader("token", token);
			response.addHeader("userId", userDetails.getMobile_number());
			
			authenticationResponse.setAuthenticated(true);
			
			}else {
				System.out.println("i am in else part of user not found");
				authenticationResponse.setAuthenticated(false);
				return new ResponseEntity<AuthenticationResponse>(authenticationResponse,HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<AuthenticationResponse>(authenticationResponse,HttpStatus.OK);
		
		}
		
		catch(BadCredentialsException e) {
			authenticationResponse.setAuthenticated(false);
			return new ResponseEntity<AuthenticationResponse>(authenticationResponse,HttpStatus.UNAUTHORIZED);
		}
		
		catch(Exception e) {
			e.printStackTrace();
			authenticationResponse.setAuthenticated(false);
			return new ResponseEntity<AuthenticationResponse>(authenticationResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	@GetMapping("/authenticate2")
	public String  authenticate1() throws Exception {
		System.out.println("hello");
		
		return "hello";
		
	}

}
