package ui.sample;


import bl.model.Unit;
import facade.UnitFacade;
import javafx.scene.control.Alert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ui.Controller;
import ui.View;

import java.awt.*;


public class UnitController {

    @FXML
    private Label toto;

    @FXML
    private TextField unitName;

    @FXML
    private TextField unitSigle;

    @FXML
    private TextField unitMail;

    @FXML

    public void handleDismissButton(ActionEvent clickConnectionButton) {
        Controller.getInstance().goTo(View.MAIN);
    }

    public void handleValidationButton(ActionEvent clickConnectionButton) {
        String name = unitName.getText();
        String sigle = unitSigle.getText();
        String mail = unitMail.getText();

        Unit unit = new Unit(name, sigle, mail);
        try {
            UnitFacade.getInstance().create(unit);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("L'unité a correctement été créée !");

        alert.showAndWait();
        Controller.getInstance().goTo(View.MAIN);
    }
}
