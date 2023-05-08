package commonModule.exceptions.serverExceptions;

import commonModule.auxiliaryClasses.ConsoleColors;

public class ServerIsDownException extends Exception {

    public ServerIsDownException(String message) {
        super(message);
    }

    public ServerIsDownException() {
        super("Server is down :(\nPlease try again later");
    }
}
