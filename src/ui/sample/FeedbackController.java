package ui.sample;


import bl.dao.FeedbackDAO;
import bl.model.Feedback;
import bl.model.Requisition;
import facade.RequisitionFacade;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.simple.JSONObject;
import outlook.CheckingMails;
import ui.Controller;
import ui.View;

import java.net.URL;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FeedbackController implements Initializable {


    @FXML
    private TextField nbPersonnesControleesField;

    @FXML
    private TextField nbInfractionsField;

    @FXML
    private TextField nbOfWeaponInfractionsField;

    @FXML
    private TextField nbOfRoadInfractionsField;

    @FXML
    private TextField nbOfPenalInfractionsField;

    @FXML
    private TextField nbOfOffencesField;

    @FXML
    private TextField nbOfFinesFifthClassField;

    @FXML
    private TextField nbOfFinesOtherClassField;

    @FXML
    private TextField nbOfVehiculsControledField;

    @FXML
    private TextField nbOfImmobilisationsField;

    @FXML
    private TextField nbOfReportsField;

    @FXML
    private TextField nbOfPersonsListenField;

    @FXML
    private TextField nbOfCustodyField;

    @FXML
    private ChoiceBox idReqChoiceBox;

    Feedback feedback;
    ArrayList<Requisition> requisitions = null;
    Requisition requisition;
    int nbPersonnesControlees;
    int nbInfractions;
    int nbOfWeaponInfractions;
    int nbOfRoadInfractions;
    int nbOfPenalInfractions;
    int nbOfOffences;
    int nbOfFinesFifthClass;
    int nbOfFinesOtherClass;
    int nbOfVehiculsControled;
    int nbOfImmobilisations;
    int nbOfReports;
    int nbOfPersonsListen;
    int nbOfCustody;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            this.requisitions = RequisitionFacade.getInstance().getAllRequisition();

            for (int i = 0; i < this.requisitions.size(); i++) {
                idReqChoiceBox.getItems().add(this.requisitions.get(i).getId());
            }
        } catch (Exception e) {
            DialogHelper.dialogPop("There is an issue", e.getMessage(), "Ok");
        }

    }

    @FXML
    public void handleDismissButton(ActionEvent clickConnectionButton) {
        Controller.getInstance().goTo(View.MAIN);
    }

    public void handleValidationButton(ActionEvent clickConnectionButton) {
        if (idReqChoiceBox.getValue() == null  ||  nbPersonnesControleesField.getText().trim().isEmpty()|| nbInfractionsField.getText().trim().isEmpty() || nbOfWeaponInfractionsField.getText().trim().isEmpty() || nbOfRoadInfractionsField.getText().trim().isEmpty() || nbOfPenalInfractionsField.getText().trim().isEmpty()|| nbOfOffencesField.getText().trim().isEmpty() || nbOfFinesFifthClassField.getText().trim().isEmpty() || nbOfFinesOtherClassField.getText().trim().isEmpty() || nbOfVehiculsControledField.getText().trim().isEmpty() || nbOfImmobilisationsField.getText().trim().isEmpty() || nbOfReportsField.getText().trim().isEmpty() || nbOfPersonsListenField.getText().trim().isEmpty() || nbOfCustodyField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ATTENTION");
            alert.setHeaderText(null);
            alert.setContentText("Tous les champs n'ont pas été remplis !");

            alert.showAndWait();

        } else {

            if (idReqChoiceBox.getValue() != null) {
                int id = (Integer) idReqChoiceBox.getValue();
                requisition = RequisitionFacade.getInstance().getOne(id);
            }


            nbPersonnesControlees = Integer.parseInt(nbPersonnesControleesField.getText());
            nbInfractions = Integer.parseInt(nbInfractionsField.getText());
            nbOfWeaponInfractions = Integer.parseInt(nbOfWeaponInfractionsField.getText());
            nbOfRoadInfractions = Integer.parseInt(nbOfRoadInfractionsField.getText());
            nbOfPenalInfractions = Integer.parseInt(nbOfPenalInfractionsField.getText());
            nbOfOffences = Integer.parseInt(nbOfOffencesField.getText());
            nbOfFinesFifthClass = Integer.parseInt(nbOfFinesFifthClassField.getText());
            nbOfFinesOtherClass = Integer.parseInt(nbOfFinesOtherClassField.getText());
            nbOfVehiculsControled = Integer.parseInt(nbOfVehiculsControledField.getText());
            nbOfImmobilisations = Integer.parseInt(nbOfImmobilisationsField.getText());
            nbOfReports = Integer.parseInt(nbOfReportsField.getText());
            nbOfPersonsListen = Integer.parseInt(nbOfPersonsListenField.getText());
            nbOfCustody = Integer.parseInt(nbOfCustodyField.getText());

            feedback = new Feedback(requisition, nbPersonnesControlees, nbInfractions, nbOfWeaponInfractions, nbOfRoadInfractions, nbOfPenalInfractions, nbOfOffences, nbOfFinesFifthClass, nbOfFinesOtherClass, nbOfVehiculsControled, nbOfImmobilisations, nbOfReports, nbOfPersonsListen, nbOfCustody);

            try {
                FeedbackFacade.getInstance().create(feedback);
            } catch (Exception e) {
                e.printStackTrace();
            }

            JSONObject jsonFeed = new JSONObject();
            try{
                jsonFeed.put("requisitionID", feedback.getRequisition().getId());
                jsonFeed.put("nbOfPersonControlled", feedback.getNbOfPersonControlled());
                jsonFeed.put("nbOfInfractions", feedback.getNbOfInfractions());
                jsonFeed.put("nbOfWeaponInfractions", feedback.getNbOfWeaponInfractions());
                jsonFeed.put("nbOfRoadInfractions", feedback.getNbOfRoadInfractions());
                jsonFeed.put("nbOfPenalInfractions", feedback.getNbOfPenalInfractions());
                jsonFeed.put("nbOfOffences", feedback.getNbOfOffences());
                jsonFeed.put("nbOfFinesFifthClass", feedback.getNbOfFinesFifthClass());
                jsonFeed.put("nbOfFinesOtherClass", feedback.getNbOfFinesOtherClass());
                jsonFeed.put("nbOfVehiculesControlled", feedback.getNbOfVehiculesControlled());
                jsonFeed.put("nbOfImmobilisations", feedback.getNbOfImmobilisations());
                jsonFeed.put("nbOfReports", feedback.getNbOfReports());
                jsonFeed.put("nbOfPersonsListened", feedback.getNbOfPersonsListened());
                jsonFeed.put("nbOfCustody", feedback.getNbOfCustody());

            }catch (Exception e){
                e.printStackTrace();
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("Le retour de la réquisition n°" + this.feedback.getRequisition().getId() + " a correctement été créée !");

            alert.showAndWait();
            Controller.getInstance().goTo(View.MAIN);
        }
    }

}
