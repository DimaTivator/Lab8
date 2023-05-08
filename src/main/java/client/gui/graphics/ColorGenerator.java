package client.gui.graphics;

import java.awt.Color;
import java.util.Objects;

public class ColorGenerator {

    public static Color getColor(int number) {

        int hashCode = Objects.hash(number);

        int r = (hashCode & 0xFF0000) >> 16;
        int g = (hashCode & 0xFF00) >> 8;
        int b = hashCode & 0xFF;

        return new Color(r, g, b);
    }
}
