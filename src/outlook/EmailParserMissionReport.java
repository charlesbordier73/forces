package outlook;
//

import bl.dao.DAOFactory;
import bl.model.Feedback;
import bl.model.Requisition;
import facade.FeedbackFacade;
import facade.RequisitionFacade;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import persistence.FactoryDAOPG;

//JSONParser library imports
//Model imports

public class EmailParserMissionReport {

    //Singleton
    private static EmailParserMissionReport instance;
    //Requisition content to be build with email content
    Requisition requisition;
    Feedback feedback;

    //Singleton

    private EmailParserMissionReport(){
    }

    public static EmailParserMissionReport getInstance() {
        if (instance == null) {
            instance = new EmailParserMissionReport();
        }
        return instance;
    }

    /**
     * Parses the formatted email content to create a requisition report to be saved in the persistence storage (Database)
     * @param content :  Email content to parse to create the requisition report
     */
    public void parseEmailContent(String content){

        try{
            this.feedback = null;
            this.requisition = null;
            DAOFactory daoFactory = FactoryDAOPG.getInstance();
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(content);

            // creating the requisition feedback itself
            int nbOfPersonControlled = Integer.parseInt((String)jsonObject.get("nbOfPersonControlled"));
            int nbOfInfractions = Integer.parseInt((String)jsonObject.get("nbOfInfractions"));
            int nbOfWeaponInfractions = Integer.parseInt((String)jsonObject.get("nbOfWeaponInfractions"));
            int nbOfRoadInfractions = Integer.parseInt((String)jsonObject.get("nbOfRoadInfractions"));
            int nbOfPenalInfractions = Integer.parseInt((String)jsonObject.get("nbOfPenalInfractions"));
            int nbOfOffences = Integer.parseInt((String)jsonObject.get("nbOfOffences"));
            int nbOfFinesFifthClass = Integer.parseInt((String)jsonObject.get("nbOfFinesFifthClass"));
            int nbOfFinesOtherClass = Integer.parseInt((String)jsonObject.get("nbOfFinesOtherClass"));
            int nbOfVehiculesControlled = Integer.parseInt((String)jsonObject.get("nbOfVehiculesControlled"));
            int nbOfImmobilisations = Integer.parseInt((String)jsonObject.get("nbOfImmobilisations"));
            int nbOfReports = Integer.parseInt((String)jsonObject.get("nbOfReports"));
            int nbOfPersonsListened = Integer.parseInt((String)jsonObject.get("nbOfPersonsListened"));
            int nbOfCustody = Integer.parseInt((String)jsonObject.get("nbOfCustody"));

            this.requisition = RequisitionFacade.getInstance().getOne(Integer.parseInt((String)jsonObject.get("requisitionID")));
            this.feedback = new Feedback( this.requisition,
                    nbOfPersonControlled,
                    nbOfInfractions,
                    nbOfWeaponInfractions,
                    nbOfRoadInfractions,
                    nbOfPenalInfractions,
                    nbOfOffences,
                    nbOfFinesFifthClass,
                    nbOfFinesOtherClass,
                    nbOfVehiculesControlled,
                    nbOfImmobilisations,
                    nbOfReports,
                    nbOfPersonsListened,
                    nbOfCustody);

                FeedbackFacade.getInstance().create(this.feedback);


            }catch (ParseException ex){
            ex.printStackTrace();
        }catch (NullPointerException ex){
            ex.printStackTrace();
        }
    }
}
