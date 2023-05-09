package client.i10n;

import java.util.Locale;
import java.util.ResourceBundle;

public record Resources() {

    private static ResourceBundle resourceBundle;

    private static Locale currentLocale;

    public static ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    public static void setResourceBundle(ResourceBundle resourceBundle) {
        Resources.resourceBundle = resourceBundle;
    }

    public static Locale getCurrentLocale() {
        return currentLocale;
    }

    public static void setCurrentLocale(Locale currentLocale) {
        Resources.currentLocale = currentLocale;
    }
}
