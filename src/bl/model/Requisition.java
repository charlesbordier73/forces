package bl.model;

import java.io.Serializable;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class Requisition implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("startDate")
    private Date startDate;
    @SerializedName("endDate")
    private Date endDate;
    @SerializedName("perimeter")
    private String map;
    @SerializedName("motivation")
    private String motivation;
    @SerializedName("requestingUnit")
    private Unit requestingUnit;
    @SerializedName("assistingUnits")
    private ArrayList<Unit> assistingUnit;
    @SerializedName("targets")
    private ArrayList<Infraction> infractions;
    @SerializedName("articles")
    private ArrayList<Article> articles;
    @SerializedName("approuved")
    private boolean approuved;

    public Requisition() {
    }

    public Requisition(Date startDate, Date endDate, String map, String motivation, Unit requestingUnit, ArrayList<Unit> assistingUnit, ArrayList<Infraction> infractions, ArrayList<Article> articles) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.map = map;
        this.motivation = motivation;
        this.requestingUnit = requestingUnit;
        this.assistingUnit = assistingUnit;
        this.infractions = infractions;
        this.articles = articles;
        this.approuved = false;
    }

    public Requisition(Date startDate, Date endDate, String map, String motivation, Unit requestingUnit, ArrayList<Unit> assistingUnit, ArrayList<Infraction> infractions, ArrayList<Article> articles, boolean approuved) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.map = map;
        this.motivation = motivation;
        this.requestingUnit = requestingUnit;
        this.assistingUnit = assistingUnit;
        this.infractions = infractions;
        this.articles = articles;
        this.approuved = approuved;
    }

    public Requisition(int id, Date startDate, Date endDate, String map, String motivation, Unit requestingUnit, ArrayList<Unit> assistingUnit, ArrayList<Infraction> infractions, ArrayList<Article> articles, boolean approuved) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.map = map;
        this.motivation = motivation;
        this.requestingUnit = requestingUnit;
        this.assistingUnit = assistingUnit;
        this.infractions = infractions;
        this.articles = articles;
        this.approuved = approuved;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public Unit getRequestingUnit() {
        return requestingUnit;
    }

    public void setRequestingUnit(Unit requestingUnit) {
        this.requestingUnit = requestingUnit;
    }

    public ArrayList<Unit> getAssistingUnit() {
        return assistingUnit;
    }

    public void setAssistingUnit(ArrayList<Unit> assistingUnit) {
        this.assistingUnit = assistingUnit;
    }

    public ArrayList<Infraction> getInfractions() {
        return infractions;
    }

    public void setInfractions(ArrayList<Infraction> infractions) {
        this.infractions = infractions;
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }

    public boolean isApprouved() {
        return approuved;
    }

    public void setApprouved(boolean approuved) {
        this.approuved = approuved;
    }
}
