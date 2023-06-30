package gr.aueb.cf.finalprojpets.service.exceptions;


public class PetNotFoundException extends RuntimeException {
    public PetNotFoundException(String message) {
        super(message);
    }

}