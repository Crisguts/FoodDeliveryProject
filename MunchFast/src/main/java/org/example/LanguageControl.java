package org.example;

import lombok.Getter;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageControl {
    @Getter
    private static Locale currentLocale = new Locale("en");

    public static String getLanguage() {
        return currentLocale.getLanguage();
    }

    public static void setLanguage(String language) {
        if (language.equalsIgnoreCase("fr")) {
            currentLocale = new Locale("fr");
        } else {
            currentLocale = new Locale("en");
        }
    }

    public static String getString(String key) {
        ResourceBundle bundle = ResourceBundle.getBundle("language", currentLocale);
        return bundle.getString(key);
    }
}
