package in.fridr.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="user_details")
@NamedQuery(name="UserDetails.findAll", query="SELECT i FROM UserDetails i")
public class UserDetails {
	private static final long serialVersionUID = 9104962857219926290L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	@Column(name="mobile_number")
	private String mobile_number;
	
	private String name;
	private String email;
	private String image_url;
	private String dob;
	private String gender;
	
	@Column(name="record_tracking")
	private Timestamp record_tracking;
	

	public UserDetails() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMobile_number() {
		return mobile_number;
	}

	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}

	public Timestamp getRecord_tracking() {
		return record_tracking;
	}

	public void setRecord_tracking(Timestamp record_tracking) {
		this.record_tracking = record_tracking;
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
