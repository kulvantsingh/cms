package in.fridr.sms;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class TwilioUtility {
private static Logger logger = LoggerFactory.getLogger(TwilioUtility.class);

	private	Environment env;
	public TwilioUtility(Environment env) {
		this.env=env;
	}
	public boolean sendsms(String mobileNumber,String otp) {
		
		try {
			
			System.out.println("otp is "+otp);
			
			
		Twilio.init(env.getProperty("twillo.account_ssid"), env.getProperty("twillo.auth_token"));
		Message message = Message.creator(new PhoneNumber(mobileNumber),
		        new PhoneNumber(env.getProperty("twillo.trial_number")), 
		        "You one time password for Fridr is "+otp).create();

		    System.out.println(message.getSid());
		    return true;
		}
		  catch(Exception e) {
			  e.printStackTrace();
			  return false;
		  }
	}
	
	public  String generateOTP(int length) {
		 int randomPin   =(int) (Math.random()*9000)+1000; 
	        String otp  = String.valueOf(randomPin); 
	        return otp; //returning value of otp 
	   }

}
