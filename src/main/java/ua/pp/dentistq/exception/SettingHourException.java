package ua.pp.dentistq.exception;

/**
 * @author Vitalii Hrushyn
 *
 */

public class SettingHourException extends Exception {

	private static final long serialVersionUID = -4383528414205398023L;
	
	private String message;
	
	public SettingHourException() {
		super();
	}
	
	public SettingHourException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
