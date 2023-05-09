package client.gui.graphics;

import java.awt.Color;
import java.util.Objects;

public class ColorGenerator {

    public static Color getColor(int number) {

//        int hashCode = Objects.hash(number);
//
//        int r = (hashCode & 0xFF0000) >> 16;
//        int g = (hashCode & 0xFF00) >> 8;
//        int b = hashCode & 0xFF;
//
//        return new Color(r, g, b);

        double saturation = 0.95;
        double value = 0.85;
        double hue = (number * 137.508) % 360;
        double h = hue / 60.0;
        double c = saturation * value;
        double x = c * (1 - Math.abs(h % 2 - 1));
        double m = value - c;
        double r, g, b;
        if (h < 1) {
            r = c; g = x; b = 0;
        } else if (h < 2) {
            r = x; g = c; b = 0;
        } else if (h < 3) {
            r = 0; g = c; b = x;
        } else if (h < 4) {
            r = 0; g = x; b = c;
        } else if (h < 5) {
            r = x; g = 0; b = c;
        } else {
            r = c; g = 0; b = x;
        }
        int red = (int) ((r + m) * 255);
        int green = (int) ((g + m) * 255);
        int blue = (int) ((b + m) * 255);

        return new Color(red, green, blue);
    }
}
