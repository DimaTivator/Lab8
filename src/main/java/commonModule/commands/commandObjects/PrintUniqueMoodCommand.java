package commonModule.commands.commandObjects;

import commonModule.auxiliaryClasses.ConsoleColors;
import commonModule.dataStructures.network.CommandResponse;
import server.collectionManagement.CollectionManager;
import commonModule.dataStructures.network.Response;
import commonModule.exceptions.EmptyCollectionException;
import commonModule.collectionClasses.HumanBeing;
import commonModule.collectionClasses.Mood;
import commonModule.commands.CommandTemplate;
import commonModule.commands.CommandWithResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 PrintUniqueMoodCommand class is responsible for printing unique moods of the HumanBeing objects in the collection.
 */
public class PrintUniqueMoodCommand extends CommandTemplate implements CommandWithResponse {

    private StringBuilder output;

    /**
     Constructor for the PrintUniqueMoodCommand class.
     @param collectionManager instance of the CollectionManager class
     */
    public PrintUniqueMoodCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    public PrintUniqueMoodCommand() {}

    /**
     Overridden method from the Command class. This method is called when the "print_unique_mood" command is executed.
     @throws EmptyCollectionException if the collection is empty
     */
    public void execute() throws EmptyCollectionException {

        output = new StringBuilder();

        Map<Long, HumanBeing> data = getCollectionManager().getCollection();

        if (data.isEmpty()) {
            throw new EmptyCollectionException();
        }

        Map<Mood, Long> moodsCounter = data.values().stream()
                .collect(Collectors.groupingBy(HumanBeing::getMood, Collectors.counting()));

        List<Mood> uniqueMoodList = moodsCounter.entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey).toList();

        output.append("<html>");

        if (uniqueMoodList.isEmpty()) {
            output.append("<p><span style='color:red;'>There is no unique moods in collection :(</span></p>");
        } else {
            output.append("<p><span style='color:green;'>The list of unique moods of HumanBeing objects from the collection:</span></p>");
            output.append("<ul>");

            uniqueMoodList.forEach(x -> {
                output.append("<li>").append(x).append("</li>");
            });

            output.append("</ul>");
        }
        output.append("</html>");
    }

    @Override
    public Response getCommandResponse() {
        return new CommandResponse("print_unique_mood", getArgs(), output.toString());
    }
}
