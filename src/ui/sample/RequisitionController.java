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
        this.requisition = RequisitionFacade.getInstance().getCurrentRequisition();
        this.startDate.setText(this.startDate.getText() + this.requisition.getStartDate().toString());
        this.endDate.setText(this.endDate.getText() + this.requisition.getEndDate().toString());
        this.unit.setText(this.unit.getText() + this.requisition.getRequestingUnit().getSigle());

        ArrayList<Unit> assistingUnitsList = this.requisition.getAssistingUnit();
        String assistingUnits = "-";
        for (Unit u : assistingUnitsList) {
            assistingUnits = assistingUnits + "-  " + u.getSigle() + "  -";
        }
        assistingUnits = assistingUnits + "-";
        this.assistingUnit.setText(this.assistingUnit.getText() + assistingUnits);

        this.motivation.setText(this.motivation.getText() + requisition.getMotivation());

        this.ValidationButton.setDisable(this.requisition.isApprouved());
    }

    @FXML
    public void handleValidationButton(ActionEvent clickConnectionButton) {
        this.requisition.setApprouved(true);
        RequisitionFacade.getInstance().update(this.requisition);
        DialogHelper.dialogPop("Réquisition validée","Un mail va être envoyé", "Ok");
       /*
        try {
            AnswerSender.getInstance().sendRequisitionAnswer(this.requisition);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        */
        // TODO : Telecharger le fichier PDF pour impression
        Controller.getInstance().dismiss();
    }

    @FXML
    public void handleDismissButton(ActionEvent clickConnectionButton) {
        Controller.getInstance().dismiss();
    }

}
