package pasito.ast.staticEnvironment;

public class AlreadyBoundException extends Exception {
    public String log;
    public AlreadyBoundException() {
        log = " is already declared.";
    }
}
