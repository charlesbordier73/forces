package ui.sample;


import bl.model.Feedback;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import bl.model.Feedback;
import facade.FeedbackFacade;
import helpers.DialogHelper;
import helpers.PropertiesHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import outlook.CheckingMails;
import ui.Controller;
import ui.View;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UnitController {


    @FXML
    public void handleDismissButton(ActionEvent clickConnectionButton) {
        Controller.getInstance().goTo(View.MAIN);
    }

    public void handleValidationButton(ActionEvent clickConnectionButton) {


    }

}
