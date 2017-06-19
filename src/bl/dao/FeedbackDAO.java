package bl.dao;

import bl.model.Feedback;
import bl.model.Requisition;

import java.util.ArrayList;


public abstract class FeedbackDAO {

    public abstract Feedback create(Feedback feedback);

    public abstract boolean delete(Feedback feedback);

    public abstract Feedback update(Feedback feedback);

    public abstract ArrayList<Feedback> search(Requisition requisition);

    public abstract Feedback getOne(int id);

    public abstract ArrayList<Feedback> getAllFeedback();

}
