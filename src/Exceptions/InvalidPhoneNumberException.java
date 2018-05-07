package Exceptions;



public class InvalidPhoneNumberException extends RuntimeException {

	private long ID = 1L;

	public InvalidPhoneNumberException(){
		super();
	}
}
