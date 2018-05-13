package Exceptions;

/**
 * @author Tiago Guerreiro 53649
 * @author Bernardo Borda d'Agua 53648
 *
 */

public class UnknownContactException extends RuntimeException {

    private long ID = 1L;

    public UnknownContactException(){
        super();
    }
}
