package commonModule.exceptions;

import commonModule.auxiliaryClasses.ConsoleColors;

public class ObjectAccessException extends Exception {

    public ObjectAccessException() {
        super("You don't have access to this object");
    }
}
