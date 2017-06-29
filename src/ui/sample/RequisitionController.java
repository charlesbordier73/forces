package ui.sample;

import java.security.GeneralSecurityException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import bl.model.Infraction;
import bl.model.Requisition;
import bl.model.Unit;
import com.google.gson.Gson;
import facade.RequisitionFacade;
import facade.UnitFacade;

import helpers.DialogHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lib.DateTimePicker;
import org.json.simple.JSONObject;
import outlook.Sender;
import ui.Controller;
import ui.View;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;

public class RequisitionController implements Initializable {

    @FXML
    private CheckBox infractionWeapon;

    @FXML
    private CheckBox infractionDrugs;

    @FXML
    private CheckBox infractionThefts;

    @FXML
    private CheckBox infractionTerrorism;

    @FXML
    private ChoiceBox unitChoice;

    @FXML
    private DateTimePicker startingDate;

    @FXML
    private DateTimePicker endingDate;

    @FXML
    private TextArea motivationText;


    @FXML
    Button ValidationButton;

    @FXML
    Button dismissButton;

    Requisition requisition;
    ArrayList<Infraction> infractions = new ArrayList<>();
    ArrayList<Infraction> jsonInfractions = new ArrayList<>();

    ArrayList<Unit> units;
    Unit unite;
    String motivation;
    DateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
    Date dateDebut;
    Date dateFin;


    Infraction armes = new Infraction(1, "à la législation des armes et explosifs (art.222-54, 322-11-1 du code pénal et L. 317-8 du code de la sécurité intérieure, L. 2353-4 du code de la défense) à la législation sur les stupéfiants (art.222-34 à 222-38 du Code Pénal)");
    Infraction vols = new Infraction(2, "vols et recels (art. 311-1, 311-3, 311-4 et 321.1, 321-2, 321-3, 322-1 à 322-6 du code Pénal)");
    Infraction drogues = new Infraction(3, "Drogues");
    Infraction terrorisme = new Infraction(4, "Attendu que la menace terroriste est particulièrement élevée actuellement ; que les opérations de guerre dans la zone irako-syrienne conduisent les organisations terroristes à multiplier leurs menaces et leurs visées contre la France ; que la zone montpelliéraine au sens large est à cet égard particulièrement sensible, en raison de l'importance de la radicalisation qui y a été constatée et des symboles qu'elle pourrait représenter sur le plan social et culturel ; que l'attitude hostile des mouvements terroristes impose le plus haut niveau de prévention des risques ; qu'il est donc nécessaire, pour répondre à ce risque exceptionnellement présent de prolonger au-delà de 24 heures les réquisitions de contrôles déjà ordonnées, dans des lieux particulièrement sensibles à raison de leur fréquentation ou des axes de communication qu'ils constituent");


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.units = UnitFacade.getInstance().getAllUnit();
        for (int i = 0; i < this.units.size(); i++) {
            unitChoice.getItems().add(this.units.get(i).getName());
        }

    }

    @FXML
    public void handleValidationButton(ActionEvent clickConnectionButton) {
        if (!infractionTerrorism.isSelected() && !infractionThefts.isSelected() && !infractionDrugs.isSelected() && !infractionWeapon.isSelected()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Il faut choisir au moins un type d'infractions !");
            alert.showAndWait();
        } else {
            if (startingDate.getDateTimeValue() == null || endingDate.getDateTimeValue() == null || motivationText.getText().trim().isEmpty() || unitChoice.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Attention");
                alert.setHeaderText(null);
                alert.setContentText("Tous les champs doivent être remplis !");
                alert.showAndWait();
            } else {

                if (infractionWeapon.isSelected()) {
                    try {
                        infractions.add(armes);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (infractionDrugs.isSelected()) {
                    try {
                        infractions.add(drogues);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (infractionThefts.isSelected()) {
                    try {
                        infractions.add(vols);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (infractionTerrorism.isSelected()) {
                    try {
                        infractions.add(terrorisme);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (unitChoice.getValue() != null) {
                    String unitName = unitChoice.getValue().toString();
                    unite = UnitFacade.getInstance().search(unitName).get(0);
                }


                motivation = motivationText.getText();



                LocalDateTime startDate = startingDate.getDateTimeValue();

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

                LocalDateTime dateTimeDeb = startDate;
                String formattedDateTimeDeb = dateTimeDeb.format(formatter);

                try {
                     dateDebut = sdf.parse(formattedDateTimeDeb);
                } catch (ParseException e) {
                    e.printStackTrace();
                }



                LocalDateTime endDate = endingDate.getDateTimeValue();

                LocalDateTime dateTimeFin = endDate;
                String formattedDateTimeFin = dateTimeFin.format(formatter);
                try {
                     dateFin = sdf.parse(formattedDateTimeFin);
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                if(dateDebut.before(dateFin)){
                    requisition = new Requisition(dateDebut, dateFin, null, motivation, unite, null, infractions, null);

                    try {
                        RequisitionFacade.getInstance().create(requisition);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    JSONObject jsonReq = new JSONObject();
                    try{
                        jsonReq.put("articles", "");
                        jsonReq.put("requestingUnit", requisition.getRequestingUnit().getName());
                        jsonReq.put("startDate",formattedDateTimeDeb);
                        jsonReq.put("endDate", formattedDateTimeFin);
                        jsonReq.put("district", "");
                        jsonReq.put("motivation", requisition.getMotivation());
                        jsonReq.put("perimeter", "");
                        jsonReq.put("targets", "[]");

                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    for(int i =0; i<requisition.getInfractions().size();i++){
                        jsonInfractions.add(new Infraction(requisition.getInfractions().get(i).getLabel()));
                    }
                    String jsonInfractionString = new Gson().toJson(jsonInfractions);
                    String jsonTemp = jsonReq.toJSONString();
                    String finalJSON = jsonTemp.substring(0,jsonTemp.length()-1) + ", \"targets\": "+ jsonInfractionString + "}";
                    finalJSON = finalJSON.replace("label", "infraction");

                    DialogHelper.dialogPop("Envoi du mail", "Patientez quelques instants", "Ok");
                    try {
                        Sender.getInstance().send(jsonReq.toJSONString(), "REQUEST");
                    } catch (GeneralSecurityException e) {
                        e.printStackTrace();
                    }
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Succès");
                    alert.setHeaderText(null);
                    alert.setContentText("La réquisition n°" + this.requisition.getId() + " a correctement été créée !");

                    alert.showAndWait();
                    Controller.getInstance().goTo(View.MAIN);
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Attention");
                    alert.setHeaderText(null);
                    alert.setContentText("La date de début doit être inférieure à la date de fin !");

                    alert.showAndWait();
                }

            }
        }
    }

    @FXML
    public void handleDismissButton(ActionEvent clickConnectionButton) {
        Controller.getInstance().goTo(View.MAIN);
    }

}
