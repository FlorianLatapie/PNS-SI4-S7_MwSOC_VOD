package exceptions;

public class SignUpFailed extends Exception {
    public SignUpFailed(String username) {
        super("SignUpFailed: " + username + " already exists");
    }
}
