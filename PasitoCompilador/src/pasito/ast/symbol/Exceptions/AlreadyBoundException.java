package pasito.ast.symbol.Exceptions;

public class AlreadyBoundException extends Exception {
    public String log;
    public AlreadyBoundException() {
        log = " is already declared.";
    }
}
