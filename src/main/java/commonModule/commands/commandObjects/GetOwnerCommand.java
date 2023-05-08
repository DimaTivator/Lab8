package commonModule.commands.commandObjects;

import commonModule.collectionClasses.HumanBeing;
import commonModule.commands.CommandTemplate;
import commonModule.commands.CommandWithResponse;
import commonModule.dataStructures.network.CommandResponse;
import commonModule.dataStructures.network.Response;
import commonModule.exceptions.EmptyCollectionException;
import commonModule.exceptions.commandExceptions.InvalidArgumentsException;
import server.collectionManagement.CollectionManager;
import server.database.DatabaseManager;

import java.sql.SQLException;
import java.util.Map;

public class GetOwnerCommand extends CommandTemplate implements CommandWithResponse {

    private StringBuilder output;

    public GetOwnerCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    public GetOwnerCommand() {}

    @Override
    public void setArgs(String[] args) throws InvalidArgumentsException {
        Long key;
        try {
            key = Long.parseLong(args[0]);
            super.setArgs(new String[]{ String.valueOf(key) });
        } catch (NumberFormatException e) {
            throw new InvalidArgumentsException("The key must be a number! Please Try to enter a command again");
        }
    }

    @Override
    public void execute() throws EmptyCollectionException, SQLException {

        output = new StringBuilder();

        long id = Long.parseLong(getArgs()[0]);
        DatabaseManager databaseManager = getDatabaseManager();

        output.append(databaseManager.getOwner(id));
    }

    @Override
    public Response getCommandResponse() {
        return new CommandResponse("show", getArgs(), output.toString());
    }
}
