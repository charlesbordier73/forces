package outlook;
//

import bl.dao.DAOFactory;
import bl.model.Article;
import bl.model.Infraction;
import bl.model.Requisition;
import bl.model.Unit;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import persistence.FactoryDAOPG;

import javax.mail.Address;
import javax.mail.Message;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

//JSONParser library imports
//Model imports


public class EmailParserRequest {

    //Singleton
    private static EmailParserRequest instance;
    //Requisition content to be build with email content
    Requisition requisition;
    Unit requestingUnit;
    ArrayList<Unit> assistingUnits;
    ArrayList<Infraction> infractions;
    ArrayList<Article> articles;

    //Singleton

    private EmailParserRequest(){
        this.assistingUnits = new ArrayList<Unit>();
        this.infractions = new ArrayList<Infraction>();
        this.articles = new ArrayList<Article>();
    }

    public static EmailParserRequest getInstance() {
        if (instance == null) {
            instance = new EmailParserRequest();
        }
        return instance;
    }

    /**
     * Parses the header of the email to retrieves sender's Name and Email.
     * Sender will become the requestingUnit
     * @param m : Header of the email
     * @throws Exception
     */
    public void setSender(Message m) throws Exception {

        Address[] a;
        Unit sender = null;
        // FROM
        if ((a = m.getFrom()) != null) {
            for (int j = 0; j < a.length; j++){
                String[] senderInfos = a[j].toString().split(" <");
                sender = new Unit(senderInfos[0],senderInfos[1].substring(0,(senderInfos[1].length()-1)));
                System.out.println(sender.getMail());
            }

        }
        //Check if Unit already exists in persistence (Database)
        DAOFactory daoFactory = FactoryDAOPG.getInstance();
        if (daoFactory.createUnitDAO().search(sender.getName()).size() == 0){
            daoFactory.createUnitDAO().create(sender);
        }else{
            sender = (Unit)daoFactory.createUnitDAO().search(sender.getName()).get(0);
        }
        this.requestingUnit = sender;
    }

    /**
     * Parses the formatted email content to create a requisition request to be saved in the persistence storage (Database)
     * @param content :  Email content to parse to create the requisition request
     */
    public void parseEmailContent(String content){

        try{
            DAOFactory daoFactory = FactoryDAOPG.getInstance();
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(content);

            // get the articles array from the JSON object
            JSONArray articlesArray = (JSONArray) jsonObject.get("articles");
            Iterator iterator = articlesArray.iterator();
            // take each value from the json array separately
            ArrayList<Article> articlesToLoopOver = new ArrayList<Article>();
            while (iterator.hasNext()) {
                JSONObject innerObj = (JSONObject) iterator.next();
                articlesToLoopOver = daoFactory.createArticleDAO().search((String)innerObj.get("number"));
                if (daoFactory.createArticleDAO().search((String)innerObj.get("number")).size() != 0){
                    for (int i = 0; i< articlesToLoopOver.size(); i++){
                        this.articles.add((Article)daoFactory.createArticleDAO().search((String)innerObj.get("number")).get(0));
                    }
                }
            }

            // get the assistingUnits array from the JSON object
            JSONArray assistingUnitsArray = (JSONArray) jsonObject.get("assistingUnits");
            iterator = assistingUnitsArray.iterator();
            // take each value from the json array separately
            while (iterator.hasNext()) {
                JSONObject innerObj = (JSONObject) iterator.next();
                //Check if Unit already is in persistence
                if ( daoFactory.createUnitDAO().search((String)innerObj.get("name")) == null || daoFactory.createUnitDAO().search((String)innerObj.get("name")).size() == 0){
                    daoFactory.createUnitDAO().create(new Unit( (String)innerObj.get("name"), (String)innerObj.get("sigle"), null));
                }
                this.assistingUnits.add(daoFactory.createUnitDAO().search((String)innerObj.get("name")).get(0));
            }

            // get the infractions array from the JSON object
            JSONArray infractionsArray = (JSONArray) jsonObject.get("targets");
            iterator = infractionsArray.iterator();
            // take each value from the json array separately
            while (iterator.hasNext()) {
                JSONObject innerObj = (JSONObject) iterator.next();
                if(daoFactory.createInfractionDAO().search((String)innerObj.get("infraction")).size() == 0){
                    daoFactory.createInfractionDAO().create( new Infraction((String)innerObj.get("infraction")));
                }
                this.infractions.add((Infraction)daoFactory.createInfractionDAO().search((String)innerObj.get("infraction")).get(0));
            }

            // creating the requisition request itself
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            Date startDate = formatter.parse((String)jsonObject.get("startDate"));
            Date endDate = formatter.parse((String)jsonObject.get("endDate"));
            String district = (String)jsonObject.get("district");
            String perimeter = (String)jsonObject.get("perimeter");
            String motivation = (String)jsonObject.get("motivation");

            Requisition requisition = new Requisition(startDate, endDate, perimeter, motivation,  requestingUnit, assistingUnits, infractions, articles, false);
            daoFactory.createRequisitionDAO().create(requisition);


            }catch (ParseException ex){
            ex.printStackTrace();
        }catch (NullPointerException ex){
            ex.printStackTrace();
        }catch(java.text.ParseException ex){
            ex.printStackTrace();
        }
    }
}
