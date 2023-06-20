package in.fridr.modal;

import java.sql.Timestamp;

public class MessageResponse {
	private String message;
	private Timestamp record_tracking;
	
	
	
	public MessageResponse() {
		super();
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Timestamp getRecord_tracking() {
		return record_tracking;
	}
	public void setRecord_tracking(Timestamp record_tracking) {
		this.record_tracking = record_tracking;
	}
	
	

}
