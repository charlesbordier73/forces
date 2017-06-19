package bl.model;


public class Feedback {

    private int id;
    private Requisition requisition;
    private int nbOfPersonControlled;
    private int nbOfInfractions;
    private int nbOfWeaponInfractions;
    private int nbOfRoadInfractions;
    private int nbOfPenalInfractions;
    private int nbOfOffences;
    private int nbOfFinesFifthClass;
    private int nbOfFinesOtherClass;
    private int nbOfVehiculesControlled;
    private int nbOfImmobilisations;
    private int nbOfReports;
    private int nbOfPersonsListened;
    private int nbOfCustody;

    public Feedback() {
    }

    public Feedback(Requisition requisition, int nbOfPersonControlled, int nbOfInfractions, int nbOfWeaponInfractions, int nbOfRoadInfractions, int nbOfPenalInfractions, int nbOfOffences, int nbOfFinesFifthClass, int nbOfFinesOtherClass, int nbOfVehiculesControlled, int nbOfImmobilisations, int nbOfReports, int nbOfPersonsListened, int nbOfCustody) {
        this.requisition = requisition;
        this.nbOfPersonControlled = nbOfPersonControlled;
        this.nbOfInfractions = nbOfInfractions;
        this.nbOfWeaponInfractions = nbOfWeaponInfractions;
        this.nbOfRoadInfractions = nbOfRoadInfractions;
        this.nbOfPenalInfractions = nbOfPenalInfractions;
        this.nbOfOffences = nbOfOffences;
        this.nbOfFinesFifthClass = nbOfFinesFifthClass;
        this.nbOfFinesOtherClass = nbOfFinesOtherClass;
        this.nbOfVehiculesControlled = nbOfVehiculesControlled;
        this.nbOfImmobilisations = nbOfImmobilisations;
        this.nbOfReports = nbOfReports;
        this.nbOfPersonsListened = nbOfPersonsListened;
        this.nbOfCustody = nbOfCustody;
    }

    public Feedback(int id, Requisition requisition, int nbOfPersonControlled, int nbOfInfractions, int nbOfWeaponInfractions, int nbOfRoadInfractions, int nbOfPenalInfractions, int nbOfOffences, int nbOfFinesFifthClass, int nbOfFinesOtherClass, int nbOfVehiculesControlled, int nbOfImmobilisations, int nbOfReports, int nbOfPersonsListened, int nbOfCustody) {
        this.id = id;
        this.requisition = requisition;
        this.nbOfPersonControlled = nbOfPersonControlled;
        this.nbOfInfractions = nbOfInfractions;
        this.nbOfWeaponInfractions = nbOfWeaponInfractions;
        this.nbOfRoadInfractions = nbOfRoadInfractions;
        this.nbOfPenalInfractions = nbOfPenalInfractions;
        this.nbOfOffences = nbOfOffences;
        this.nbOfFinesFifthClass = nbOfFinesFifthClass;
        this.nbOfFinesOtherClass = nbOfFinesOtherClass;
        this.nbOfVehiculesControlled = nbOfVehiculesControlled;
        this.nbOfImmobilisations = nbOfImmobilisations;
        this.nbOfReports = nbOfReports;
        this.nbOfPersonsListened = nbOfPersonsListened;
        this.nbOfCustody = nbOfCustody;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Requisition getRequisition() {
        return requisition;
    }

    public void setRequisition(Requisition requisition) {
        this.requisition = requisition;
    }

    public int getNbOfPersonControlled() {
        return nbOfPersonControlled;
    }

    public void setNbOfPersonControlled(int nbOfPersonControlled) {
        this.nbOfPersonControlled = nbOfPersonControlled;
    }

    public int getNbOfInfractions() {
        return nbOfInfractions;
    }

    public void setNbOfInfractions(int nbOfInfractions) {
        this.nbOfInfractions = nbOfInfractions;
    }

    public int getNbOfWeaponInfractions() {
        return nbOfWeaponInfractions;
    }

    public void setNbOfWeaponInfractions(int nbOfWeaponInfractions) {
        this.nbOfWeaponInfractions = nbOfWeaponInfractions;
    }

    public int getNbOfRoadInfractions() {
        return nbOfRoadInfractions;
    }

    public void setNbOfRoadInfractions(int nbOfRoadInfractions) {
        this.nbOfRoadInfractions = nbOfRoadInfractions;
    }

    public int getNbOfPenalInfractions() {
        return nbOfPenalInfractions;
    }

    public void setNbOfPenalInfractions(int nbOfPenalInfractions) {
        this.nbOfPenalInfractions = nbOfPenalInfractions;
    }

    public int getNbOfOffences() {
        return nbOfOffences;
    }

    public void setNbOfOffences(int nbOfOffences) {
        this.nbOfOffences = nbOfOffences;
    }

    public int getNbOfFinesFifthClass() {
        return nbOfFinesFifthClass;
    }

    public void setNbOfFinesFifthClass(int nbOfFinesFifthClass) {
        this.nbOfFinesFifthClass = nbOfFinesFifthClass;
    }

    public int getNbOfFinesOtherClass() {
        return nbOfFinesOtherClass;
    }

    public void setNbOfFinesOtherClass(int nbOfFinesOtherClass) {
        this.nbOfFinesOtherClass = nbOfFinesOtherClass;
    }

    public int getNbOfVehiculesControlled() {
        return nbOfVehiculesControlled;
    }

    public void setNbOfVehiculesControlled(int nbOfVehiculesControlled) {
        this.nbOfVehiculesControlled = nbOfVehiculesControlled;
    }

    public int getNbOfImmobilisations() {
        return nbOfImmobilisations;
    }

    public void setNbOfImmobilisations(int nbOfImmobilisations) {
        this.nbOfImmobilisations = nbOfImmobilisations;
    }

    public int getNbOfReports() {
        return nbOfReports;
    }

    public void setNbOfReports(int nbOfReports) {
        this.nbOfReports = nbOfReports;
    }

    public int getNbOfPersonsListened() {
        return nbOfPersonsListened;
    }

    public void setNbOfPersonsListened(int nbOfPersonsListened) {
        this.nbOfPersonsListened = nbOfPersonsListened;
    }

    public int getNbOfCustody() {
        return nbOfCustody;
    }

    public void setNbOfCustody(int nbOfCustody) {
        this.nbOfCustody = nbOfCustody;
    }
}


