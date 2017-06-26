package ui.sample;


import bl.model.Requisition;
import bl.model.Unit;
import facade.RequisitionFacade;
import helpers.DialogHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import ui.Controller;
import ui.View;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RequisitionController implements Initializable {

    @FXML
    private Label startDate;

    @FXML
    private Label endDate;

    @FXML
    private Label unit;

    @FXML
    private Label assistingUnit;

    @FXML
    private Label motivation;

    @FXML
    private Label infractions;

    @FXML
    Button ValidationButton;

    @FXML
    Button dismissButton;

    Requisition requisition;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void handleValidationButton(ActionEvent clickConnectionButton) {

    }

    @FXML
    public void handleDismissButton(ActionEvent clickConnectionButton) {
        Controller.getInstance().goTo(View.MAIN);
    }

}
