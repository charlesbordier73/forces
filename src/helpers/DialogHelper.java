package helpers;

import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.util.Pair;


public class DialogHelper {

    public static void dialogPop(String title, String text, String buttonName)
    {
        final Dialog<Pair<String, String>> dialog = new Dialog<Pair<String, String>>();
        dialog.setTitle(title);
        dialog.setHeaderText(text);
        final ButtonType loginButtonType = new ButtonType(buttonName, ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType);
        dialog.show();
    }
    
}