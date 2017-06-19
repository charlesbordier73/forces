package facade;

import bl.dao.DAOFactory;
import bl.dao.RequisitionDAO;
import bl.model.Requisition;
import persistence.FactoryDAOPG;

import java.util.ArrayList;
import java.util.Date;

public class RequisitionFacade {

    private static RequisitionFacade requisitionFacade = null;

    private Requisition requisition = null;

    public static RequisitionFacade getInstance() {
        if (requisitionFacade == null) {
            synchronized(RequisitionFacade.class) {
                if (requisitionFacade == null) {
                    requisitionFacade = new RequisitionFacade();
                }
            }
        }
        return requisitionFacade;
    }

    public void setCurrentRequisition(Requisition requisition) {
        this.requisition = requisition;
    }

    public Requisition getCurrentRequisition() {
        return this.requisition;
    }

    public Requisition getOne(int id) {
        DAOFactory factory = FactoryDAOPG.getInstance();
        RequisitionDAO requisitionDAO = ((FactoryDAOPG) factory).createRequisitionDAO();
        Requisition requisition = requisitionDAO.getOne(id);

        return requisition;
    }



    public Requisition create(Requisition requisition) {
        DAOFactory factory = FactoryDAOPG.getInstance();
        RequisitionDAO requisitionDAO = ((FactoryDAOPG) factory).createRequisitionDAO();
        Requisition requisitionCreated = requisitionDAO.create(requisition);

        return requisitionCreated;
    }

    public  boolean delete(Requisition requisition) {
        DAOFactory factory = FactoryDAOPG.getInstance();
        RequisitionDAO requisitionDAO = ((FactoryDAOPG) factory).createRequisitionDAO();
        boolean deleted = requisitionDAO.delete(requisition);

        return deleted;
    }

    public Requisition update(Requisition requisition) {
        DAOFactory factory = FactoryDAOPG.getInstance();
        RequisitionDAO requisitionDAO = ((FactoryDAOPG) factory).createRequisitionDAO();
        Requisition updatedRequisition = requisitionDAO.update(requisition);

        return updatedRequisition;
    }

    public  ArrayList<Requisition> search(Date startDate) {
        DAOFactory factory = FactoryDAOPG.getInstance();
        RequisitionDAO requisitionDAO = ((FactoryDAOPG) factory).createRequisitionDAO();
        ArrayList<Requisition> requisitions = requisitionDAO.search(startDate);

        return requisitions;
    }

    public  ArrayList<Requisition> getAllRequisition() {
        DAOFactory factory = FactoryDAOPG.getInstance();
        RequisitionDAO requisitionDAO = ((FactoryDAOPG) factory).createRequisitionDAO();
        ArrayList<Requisition> requisitions = requisitionDAO.getAllRequisition();

        return requisitions;
    }


    public ArrayList<Requisition> getRequests() {
        DAOFactory factory = FactoryDAOPG.getInstance();
        RequisitionDAO requisitionDAO = ((FactoryDAOPG) factory).createRequisitionDAO();
        ArrayList<Requisition> requisitions = requisitionDAO.getRequests();

        return requisitions;
    }

    public ArrayList<Requisition> getFutureRequisitions() {
        DAOFactory factory = FactoryDAOPG.getInstance();
        RequisitionDAO requisitionDAO = ((FactoryDAOPG) factory).createRequisitionDAO();
        ArrayList<Requisition> requisitions = requisitionDAO.getFutureRequisitions();

        return requisitions;
    }

    public ArrayList<Requisition> getAllPastRequisitions() {
        DAOFactory factory = FactoryDAOPG.getInstance();
        RequisitionDAO requisitionDAO = ((FactoryDAOPG) factory).createRequisitionDAO();
        ArrayList<Requisition> requisitions = requisitionDAO.getAllPastRequisitions();

        return requisitions;
    }

    public ArrayList<Requisition> getWithFeedbackRequisitions() {
        DAOFactory factory = FactoryDAOPG.getInstance();
        RequisitionDAO requisitionDAO = ((FactoryDAOPG) factory).createRequisitionDAO();
        ArrayList<Requisition> requisitions = requisitionDAO.getWithFeedbackRequisitions();

        return requisitions;
    }

    public ArrayList<Requisition> getWithoutFeedbackRequisitions() {
        DAOFactory factory = FactoryDAOPG.getInstance();
        RequisitionDAO requisitionDAO = ((FactoryDAOPG) factory).createRequisitionDAO();
        ArrayList<Requisition> requisitions = requisitionDAO.getWithoutFeedbackRequisitions();

        return requisitions;
    }

}
