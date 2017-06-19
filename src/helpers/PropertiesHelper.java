package helpers;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PropertiesHelper {

    public static StringProperty convertToProperty(String s) {
        return new SimpleStringProperty(s);
    }
}
