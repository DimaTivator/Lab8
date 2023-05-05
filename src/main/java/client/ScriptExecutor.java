package client;


import commonModule.auxiliaryClasses.ConsoleColors;
import commonModule.commands.Command;
import commonModule.dataStructures.network.CommandRequest;
import commonModule.dataStructures.network.CommandResponse;
import commonModule.dataStructures.network.Request;
import commonModule.dataStructures.Triplet;
import commonModule.exceptions.InvalidCoordinatesException;
import commonModule.exceptions.InvalidInputException;
import commonModule.exceptions.ScriptsRecursionException;
import commonModule.exceptions.commandExceptions.InvalidArgumentsException;
import commonModule.exceptions.commandExceptions.NoSuchCommandException;
import commonModule.exceptions.serverExceptions.ServerIsDownException;
import commonModule.io.consoleIO.CommandParser;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * The {@code ScriptExecutor} class provides the functionality to execute commands from a script file.
 */
public class ScriptExecutor {

    private Set<String> usedScripts = new HashSet<>();

    public ScriptExecutor() {}

    /**
     * Executes the commands from the script file located at the given file path.
     */
    public void executeScript(String filePath, CommandSender commandSender,
                              CommandResponseReceiver commandResponseReceiver, JTextArea resultTextField) throws ScriptsRecursionException, ServerIsDownException, FileNotFoundException {

        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        CommandParser commandParser = new CommandParser();


        usedScripts.add(filePath);

        while (true) {

            try {
                Triplet<String, String[], Object> parsedCommand = commandParser.parseCommandFromFile(scanner);
                String commandName = parsedCommand.getFirst();
                String[] args = parsedCommand.getSecond();

                if (commandName.equals("execute_script")) {
                    if (usedScripts.contains(args[0])) {
                        throw new ScriptsRecursionException("You should not call execute_script recursively!");
                    }
                    usedScripts.add(args[0]);
                    executeScript(args[0], commandSender, commandResponseReceiver, resultTextField);
                }
                else {
                    Command command = commandParser.pack(parsedCommand);
                    commandSender.sendCommand(command);

                    String response = commandResponseReceiver.receiveCommandResponse();

                    if (response == null) {
                        throw new ServerIsDownException();
                    } else {
                        resultTextField.append(Jsoup.parse(response).text().replaceAll("LINE_BREAK", "\n") + '\n');
                    }
                }

            } catch (ScriptsRecursionException | NoSuchCommandException | InvalidArgumentsException |
                     InvalidCoordinatesException | InvalidInputException e) {

                resultTextField.append(e.getMessage() + '\n');

            } catch (ServerIsDownException e) {
                throw e;
            } catch (Exception e) {
                break;
            }
        }

        usedScripts.clear();
    }
}

