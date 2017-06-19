package facade;

import bl.dao.DAOFactory;
import bl.dao.FeedbackDAO;
import bl.model.Feedback;
import bl.model.Requisition;
import persistence.FactoryDAOPG;

import java.util.ArrayList;


public class FeedbackFacade {


    private static FeedbackFacade feedbackFacade = null;

    private Feedback feedback = null;

    public static FeedbackFacade getInstance() {
        if (feedbackFacade == null) {
            synchronized(FeedbackFacade.class) {
                if (feedbackFacade == null) {
                    feedbackFacade = new FeedbackFacade();
                }
            }
        }
        return feedbackFacade;
    }

    public void setCurrentFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public Feedback getCurrentFeedback() {
        return this.feedback;
    }



    public Feedback create(Feedback feedback) {
        DAOFactory factory = FactoryDAOPG.getInstance();
        FeedbackDAO feedbackDAO = ((FactoryDAOPG) factory).createFeedbackDAO();
        Feedback createdFeedback = feedbackDAO.create(feedback);

        return createdFeedback;
    }

    public boolean delete(Feedback feedback){
        DAOFactory factory = FactoryDAOPG.getInstance();
        FeedbackDAO feedbackDAO = ((FactoryDAOPG) factory).createFeedbackDAO();
        boolean deleted = feedbackDAO.delete(feedback);

        return deleted;
    }

    public Feedback update(Feedback feedback) {
        DAOFactory factory = FactoryDAOPG.getInstance();
        FeedbackDAO feedbackDAO = ((FactoryDAOPG) factory).createFeedbackDAO();
        Feedback updatedFeedback = feedbackDAO.update(feedback);

        return updatedFeedback;
    }

    public ArrayList<Feedback> search(Requisition requisition) {
        DAOFactory factory = FactoryDAOPG.getInstance();
        FeedbackDAO feedbackDAO = ((FactoryDAOPG) factory).createFeedbackDAO();
        ArrayList<Feedback> feedbacks = feedbackDAO.search(requisition);

        return feedbacks;
    }

    public Feedback getOne(int id) {
        DAOFactory factory = FactoryDAOPG.getInstance();
        FeedbackDAO feedbackDAO = ((FactoryDAOPG) factory).createFeedbackDAO();
        Feedback feedback = feedbackDAO.getOne(id);

        return feedback;
    }

    public ArrayList<Feedback> getAllFeedback() {
        DAOFactory factory = FactoryDAOPG.getInstance();
        FeedbackDAO feedbackDAO = ((FactoryDAOPG) factory).createFeedbackDAO();
        ArrayList<Feedback> feedbacks = feedbackDAO.getAllFeedback();

        return feedbacks;
    }

}
