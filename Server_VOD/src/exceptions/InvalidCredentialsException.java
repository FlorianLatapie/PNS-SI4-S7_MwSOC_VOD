package exceptions;

public class InvalidCredentialsException extends Exception {
    public InvalidCredentialsException(String mail) {
        super("InvalidCredentialsException: " + mail + " doesn't exists");
    }
}
