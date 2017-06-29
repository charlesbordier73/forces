package ui.sample;

import outlook.Sender;
import bl.model.Feedback;
import bl.model.Requisition;
import facade.RequisitionFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import facade.FeedbackFacade;
import helpers.DialogHelper;
import javafx.scene.control.*;
import org.json.simple.JSONObject;
import ui.Controller;
import ui.View;

import java.net.URL;
import java.security.GeneralSecurityException;
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
                jsonFeed.put("requisitionID",String.valueOf(feedback.getRequisition().getId()));
                jsonFeed.put("nbOfPersonControlled", String.valueOf(feedback.getNbOfPersonControlled()));
                jsonFeed.put("nbOfInfractions", String.valueOf(feedback.getNbOfInfractions()));
                jsonFeed.put("nbOfWeaponInfractions", String.valueOf(feedback.getNbOfWeaponInfractions()));
                jsonFeed.put("nbOfRoadInfractions", String.valueOf(feedback.getNbOfRoadInfractions()));
                jsonFeed.put("nbOfPenalInfractions", String.valueOf(feedback.getNbOfPenalInfractions()));
                jsonFeed.put("nbOfOffences", String.valueOf(feedback.getNbOfOffences()));
                jsonFeed.put("nbOfFinesFifthClass", String.valueOf(feedback.getNbOfFinesFifthClass()));
                jsonFeed.put("nbOfFinesOtherClass", String.valueOf(feedback.getNbOfFinesOtherClass()));
                jsonFeed.put("nbOfVehiculesControlled", String.valueOf(feedback.getNbOfVehiculesControlled()));
                jsonFeed.put("nbOfImmobilisations", String.valueOf(feedback.getNbOfImmobilisations()));
                jsonFeed.put("nbOfReports", String.valueOf(feedback.getNbOfReports()));
                jsonFeed.put("nbOfPersonsListened", String.valueOf(feedback.getNbOfPersonsListened()));
                jsonFeed.put("nbOfCustody", String.valueOf(feedback.getNbOfCustody()));

            }catch (Exception e){
                e.printStackTrace();
            }

            DialogHelper.dialogPop("Envoie du mail", "Patientez quelques instants", "Ok");
            try {
                Sender.getInstance().send(jsonFeed.toJSONString(), "REPORT");
            } catch (GeneralSecurityException e) {
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
