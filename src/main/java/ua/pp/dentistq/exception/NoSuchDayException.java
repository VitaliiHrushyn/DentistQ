package ua.pp.dentistq.exception;

/**
 * @author Vitalii Hrushyn
 *
 */

public class NoSuchDayException extends Exception {

	private static final long serialVersionUID = 8473110835109502242L;
	
	private String message;
	
	public NoSuchDayException() {
		super();
	}
	
	public NoSuchDayException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
