package commonModule.commands.commandObjects;

import commonModule.auxiliaryClasses.ConsoleColors;
import commonModule.commands.CommandTemplate;
import commonModule.dataStructures.network.CommandResponse;
import server.collectionManagement.CollectionPrinter;
import commonModule.dataStructures.network.Response;
import commonModule.commands.CommandWithResponse;

import java.util.Map;

/**
 * The HelpCommand class implements the behavior for the "help" command.
 * This command is used to display the available commands in the system and their descriptions.
 */
public class HelpCommand extends CommandTemplate implements CommandWithResponse {

    private StringBuilder output;

    /**
     Constructs a HelpCommand object.
     @param collectionPrinter the object that helps with printing the collection data
     */
    public HelpCommand(CollectionPrinter collectionPrinter) {
        super(collectionPrinter);
    }

    public HelpCommand() {}

    /**
     This method displays the available commands in the system and their descriptions.
     */
    @Override
    public void execute() {
        CollectionPrinter collectionPrinter = getCollectionPrinter();
        Map<String, String> commands = collectionPrinter.getCommands();

        output = new StringBuilder();

        output.append("<html>");

        commands.forEach((key, value) -> {
            output.append("<span style=\"color: green;\">");

            for (int i = 0; i < key.length(); i++) {
                char letter = key.charAt(i);

                if (letter == '<') {
                    output.append("&lt;<span style=\"color: purple;\">");
                } else if (letter == '>') {
                    output.append("</span><span style=\"color: green;\">&gt;</span>");
                } else {
                    output.append(letter);
                }
            }

            output.append("</span>: ").append(value).append("LINE_BREAK").append("<br>");
        });

        output.append("LINE_BREAK");
        output.append("<br>");
        output.append("</html>");
    }

    @Override
    public Response getCommandResponse() {
        return new CommandResponse("help", getArgs(), output.toString());
    }
}
