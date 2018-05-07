package Exceptions;

public class DuplicateContactException extends RuntimeException {

	private long ID = 1L;

	public DuplicateContactException(){
		super();
	}
}
