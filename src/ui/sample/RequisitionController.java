package ui.sample;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import bl.model.Infraction;
import bl.model.Requisition;
import bl.model.Unit;
import facade.InfractionFacade;
import facade.RequisitionFacade;
import facade.UnitFacade;
import helpers.DialogHelper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lib.DateTimePicker;
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
    ArrayList<Unit> units;
    Unit unite;
    Infraction armes = new Infraction(1, "Armes");
    Infraction vols = new Infraction(2, "Vols");
    Infraction drogues = new Infraction(3, "Drogues");
    Infraction terrorisme = new Infraction(4, "Terrorisme");


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.units = UnitFacade.getInstance().getAllUnit();
        for (int i = 0; i < this.units.size(); i++) {
            unitChoice.getItems().add(this.units.get(i).getName());
        }

    }

    @FXML
    public void handleValidationButton(ActionEvent clickConnectionButton) {
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


        String motivation = motivationText.getText();

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

        LocalDateTime startDate = startingDate.getDateTimeValue();

        Instant instantDeb = startDate.atZone(ZoneId.systemDefault()).toInstant();
        Date dateDebut = Date.from(instantDeb);
        df.format(dateDebut);


        LocalDateTime endDate = endingDate.getDateTimeValue();

        Instant instantFin = endDate.atZone((ZoneId.systemDefault())).toInstant();
        Date dateFin = Date.from(instantFin);
        df.format(dateFin);

    }

    @FXML
    public void handleDismissButton(ActionEvent clickConnectionButton) {
        Controller.getInstance().goTo(View.MAIN);
    }

}
