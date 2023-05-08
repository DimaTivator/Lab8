package commonModule.exceptions;

import commonModule.auxiliaryClasses.ConsoleColors;

public class ScriptsRecursionException extends Exception {

    public ScriptsRecursionException(String message) {
        super(message);
    }
}
