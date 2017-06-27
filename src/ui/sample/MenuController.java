package ui.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import ui.Controller;
import ui.View;

public class MenuController {

    @FXML
    public void handleGoToRequisitions(ActionEvent clickMenu) throws Exception {
        Controller.getInstance().goTo(View.REQUISITIONS);
    }


    @FXML
    public void handleGoToFeedbacks(ActionEvent clickMenu) throws Exception {
        Controller.getInstance().goTo(View.FEEDBACKS);
    }

    public void handleGoToUnits(ActionEvent clickMenu) throws Exception {
        Controller.getInstance().goTo(View.UNITS);
    }

}