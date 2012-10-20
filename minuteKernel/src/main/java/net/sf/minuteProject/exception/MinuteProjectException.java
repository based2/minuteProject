package net.sf.minuteProject.exception;

public class MinuteProjectException extends Exception {

	private String message, error;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public MinuteProjectException(String message, String error) {
		super();
		this.message = message;
		this.error = error;
	}

	public MinuteProjectException() {
		super();
	}

}
