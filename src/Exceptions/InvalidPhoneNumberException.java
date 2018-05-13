package Exceptions;

/**
 * @author Tiago Guerreiro 53649
 * @author Bernardo Borda d'Agua 53648
 *
 */

public class InvalidPhoneNumberException extends RuntimeException {

	private long ID = 1L;

	public InvalidPhoneNumberException(){
		super();
	}
}
