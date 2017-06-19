package bl.dao;

import bl.model.Requisition;

import java.util.ArrayList;
import java.util.Date;


public abstract class RequisitionDAO {

    public abstract Requisition create(Requisition requisition);

    public abstract boolean delete(Requisition requisition);

    public abstract Requisition update(Requisition requisition);

    public abstract ArrayList<Requisition> search(Date startDate);

    public abstract Requisition getOne(int id);

    public abstract ArrayList<Requisition> getAllRequisition();

    public abstract ArrayList<Requisition> getRequests();

    public abstract ArrayList<Requisition> getFutureRequisitions();

    public abstract ArrayList<Requisition> getAllPastRequisitions();

    public abstract ArrayList<Requisition> getWithFeedbackRequisitions();

    public abstract ArrayList<Requisition> getWithoutFeedbackRequisitions();

}
