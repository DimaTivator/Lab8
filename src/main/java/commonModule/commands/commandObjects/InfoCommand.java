package commonModule.commands.commandObjects;

import commonModule.auxiliaryClasses.ConsoleColors;
import commonModule.commands.CommandTemplate;
import commonModule.dataStructures.network.CommandResponse;
import server.collectionManagement.CollectionManager;
import server.collectionManagement.CollectionPrinter;
import commonModule.dataStructures.network.Response;
import commonModule.commands.CommandWithResponse;

/**
 * InfoCommand is a class that provides information about the collection,
 * such as the type of the collection, the date of initialization, and the number of elements in the collection.
 */
public class InfoCommand extends CommandTemplate implements CommandWithResponse {

    private StringBuilder output;

    /**
     * Constructs a new InfoCommand with the specified CollectionManager and CollectionPrinter.
     * @param collectionManager the CollectionManager that manages the collection
     * @param collectionPrinter the CollectionPrinter that provides the functionality to print the collection
     */
    public InfoCommand(CollectionManager collectionManager, CollectionPrinter collectionPrinter) {
        super(collectionManager, collectionPrinter);
    }

    public InfoCommand() {}

    /**
     * Prints the information about the collection, including the type of the collection, the date of initialization,
     * and the number of elements in the collection.
     */
    @Override
    public void execute() {

        output = new StringBuilder();

        CollectionManager collectionManager = getCollectionManager();

        output.append("<html>");
        output.append("<p><span style='color:green;'>Collection type:</span> ").append(collectionManager.getCollection().getClass()).append("<br>");
        output.append("<p><span style='color:green;'>Date of initialization:</span> ").append(collectionManager.getCreationDate()).append("<br>");
        output.append("<p><span style='color:green;'>Number of elements in collection:</span> ").append(collectionManager.getCollection().size()).append("<br>");
        output.append("</html>");
    }

    @Override
    public Response getCommandResponse() {
        return new CommandResponse("info", getArgs(), output.toString());
    }
}
