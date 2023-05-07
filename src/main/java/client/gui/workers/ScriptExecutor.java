package client.gui.workers;

import client.CommandResponseReceiver;
import client.CommandSender;
import commonModule.commands.Command;
import commonModule.dataStructures.Triplet;
import commonModule.exceptions.ScriptsRecursionException;
import commonModule.exceptions.serverExceptions.ServerIsDownException;
import commonModule.io.consoleIO.CommandParser;

import javax.swing.*;
import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.jsoup.Jsoup;

/**
 * The {@code ScriptExecutor} class provides the functionality to execute commands from a script file.
 */
public class ScriptExecutor extends SwingWorker<Void, String> {

    private final Set<String> usedScripts = new HashSet<>();
    private final String filePath;
    private final CommandSender commandSender;
    private final CommandResponseReceiver commandResponseReceiver;
    private final JTextArea resultTextField;
    private final JLabel doneLabel;

    public ScriptExecutor(String filePath, CommandSender commandSender,
                          CommandResponseReceiver commandResponseReceiver,
                          JTextArea resultTextField, JLabel doneLabel) {

        this.filePath = filePath;
        this.commandSender = commandSender;
        this.commandResponseReceiver = commandResponseReceiver;
        this.resultTextField = resultTextField;
        this.doneLabel = doneLabel;
    }

    @Override
    protected Void doInBackground() throws Exception {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        CommandParser commandParser = new CommandParser();

        usedScripts.add(filePath);

        while (!isCancelled() && scanner.hasNext()) {
            Triplet<String, String[], Object> parsedCommand = commandParser.parseCommandFromFile(scanner);
            String commandName = parsedCommand.getFirst();
            String[] args = parsedCommand.getSecond();

            if (commandName.equals("execute_script")) {
                if (usedScripts.contains(args[0])) {
                    throw new ScriptsRecursionException("You should not call execute_script recursively!");
                }
                usedScripts.add(args[0]);
                ScriptExecutor scriptExecutor = new ScriptExecutor(args[0], commandSender, commandResponseReceiver, resultTextField, doneLabel);
                scriptExecutor.execute();
            } else {
                Command command = commandParser.pack(parsedCommand);
                commandSender.sendCommand(command);
                String response = commandResponseReceiver.receiveCommandResponse();
                if (response == null) {
                    throw new ServerIsDownException();
                } else {
                    publish(Jsoup.parse(response).text().replaceAll("LINE_BREAK", "\n") + '\n');
                }
            }
        }

        usedScripts.clear();
        return null;
    }

    @Override
    protected void process(java.util.List<String> chunks) {
        for (String chunk : chunks) {
            resultTextField.append(chunk);
        }
    }

    @Override
    protected void done() {
        try {
            get();
            doneLabel.setVisible(true);
            doneLabel.setText("Done!");
        } catch (Exception e) {
            if (e.getMessage().contains("java.io.FileNotFoundException")) {
                JOptionPane.showMessageDialog(null, "No such file or directory: " + filePath);
            } else {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }
}
