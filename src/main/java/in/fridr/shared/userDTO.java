package in.fridr.shared;

import java.io.Serializable;
import java.sql.Timestamp;

public class userDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7581573468163414650L;
	
	private String mobile_number;
	private String otp;
	private Timestamp record_tracking;
	public String getMobile_number() {
		return mobile_number;
	}
	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public Timestamp getRecord_tracking() {
		return record_tracking;
	}
	public void setRecord_tracking(Timestamp record_tracking) {
		this.record_tracking = record_tracking;
	}	

	
}
