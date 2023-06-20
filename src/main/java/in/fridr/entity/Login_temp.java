package in.fridr.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="login_temp")
@NamedQuery(name="Login_temp.findAll", query="SELECT i FROM Login_temp i")
public class Login_temp implements Serializable{
	
	private static final long serialVersionUID = 9104962857219926290L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="mobile_number")
	private String mobile_number;
	
	@NotNull(message="otp can't be null")
	@Column(name="otp")
	private String otp;
	
	@Column(name="record_tracking")
	private Timestamp record_tracking;
	
	

	public Login_temp() {
		
	}

	public Integer getId() {
		return id;
	}
	
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Login_temp [id=" + id + ", mobile_number=" + mobile_number + ", otp=" + otp + ", record_tracking="
				+ record_tracking + "]";
	}
	
	

}
