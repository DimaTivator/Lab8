package commonModule.io;

import commonModule.collectionClasses.Coordinates;
import commonModule.exceptions.InvalidCoordinatesException;
import commonModule.exceptions.InvalidInputException;

public class HumanBeingParser {

    public static String parseName(String input) throws InvalidInputException {

        if (input == null || input.isEmpty()) {
            throw new InvalidInputException("Name can't be empty");
        }

        return input;
    }

    public static Coordinates parseCoordinates(String input) throws InvalidCoordinatesException {

        String[] c = input.strip().replaceAll(" +", " ").replaceAll(",", ".").split(" ");

        if (c.length!= 2) {
            throw new InvalidCoordinatesException("Coordinates must be in the format X Y");
        }

        Coordinates coordinates = new Coordinates();

        coordinates.setX(Double.parseDouble(c[0]));
        coordinates.setY(Float.parseFloat(c[1]));

        return coordinates;
    }

    public static double parseImpactSpeed(String input) throws InvalidInputException {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Impact speed must be a number");
        }
    }

}
