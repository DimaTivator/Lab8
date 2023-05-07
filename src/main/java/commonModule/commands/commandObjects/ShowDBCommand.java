package commonModule.commands.commandObjects;

import commonModule.collectionClasses.HumanBeing;
import commonModule.commands.CommandTemplate;
import commonModule.commands.CommandWithResponse;
import commonModule.dataStructures.network.CommandResponse;
import commonModule.dataStructures.network.Response;
import commonModule.exceptions.EmptyCollectionException;
import server.collectionManagement.CollectionManager;

import java.util.Map;

public class ShowDBCommand extends CommandTemplate implements CommandWithResponse {

    private StringBuilder output;

    public ShowDBCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    public ShowDBCommand() {}


    @Override
    public void execute() throws EmptyCollectionException {

        output = new StringBuilder();

        Map<Long, HumanBeing> data = getCollectionManager().getCollection();

        if (data.isEmpty()) {
            throw new EmptyCollectionException();
        }

        data.forEach((key, value) -> {
            output.append(key).append("\n");
            output.append(value.getId()).append("\n");
            output.append(value.getName()).append("\n");
            output.append(value.getCoordinates().getX()).append("\n");
            output.append(value.getCoordinates().getY()).append("\n");
            output.append(value.getCreationDate()).append("\n");
            output.append(value.getMood()).append("\n");
            output.append(value.getWeaponType()).append("\n");
            output.append(value.isRealHero()).append("\n");
            output.append(value.getImpactSpeed()).append("\n");
            output.append(value.isHasToothpick()).append("\n");
            output.append((value.getCar() != null && value.getCar().getName() != null ? value.getCar().getName() : "null")).append("\n");
            output.append((value.getCar() != null && value.getCar().getCool() != null ? value.getCar().getCool() : "null")).append("\n");
        });
    }

    @Override
    public Response getCommandResponse() {
        return new CommandResponse("show", getArgs(), output.toString());
    }
}
