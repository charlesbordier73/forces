package persistence;

import bl.dao.FeedbackDAO;
import bl.model.Feedback;
import bl.model.Requisition;
import persistence.connector.Connector;

import java.sql.*;
import java.util.ArrayList;

public class FeedbackDAOPG extends FeedbackDAO {
    @Override
    public Feedback create(Feedback feedback) {
        try {
            String query = "INSERT INTO Feedback (requisitionID, nbOfPersonControlled, nbOfInfractions, nbOfWeaponInfractions, nbOfRoadInfractions, nbOfPenalInfractions, nbOfOffences, nbOfFinesFifthClass, nbOfFinesOtherClass, nbOfVehiculesControlled, nbOfImmobilisations, nbOfReports, nbOfPersonsListened, nbOfCustody) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, feedback.getRequisition().getId());
            ps.setInt(2, feedback.getNbOfPersonControlled());
            ps.setInt(3, feedback.getNbOfInfractions());
            ps.setInt(4, feedback.getNbOfWeaponInfractions());
            ps.setInt(5, feedback.getNbOfRoadInfractions());
            ps.setInt(6, feedback.getNbOfPenalInfractions());
            ps.setInt(7, feedback.getNbOfOffences());
            ps.setInt(8, feedback.getNbOfFinesFifthClass());
            ps.setInt(9, feedback.getNbOfFinesOtherClass());
            ps.setInt(10, feedback.getNbOfVehiculesControlled());
            ps.setInt(11, feedback.getNbOfImmobilisations());
            ps.setInt(12, feedback.getNbOfReports());
            ps.setInt(13, feedback.getNbOfPersonsListened());
            ps.setInt(14, feedback.getNbOfCustody());

            ps.execute();
            String queryID = "SELECT MAX(id) AS id FROM feedback ";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(queryID);
            rs.next();
            feedback.setId(rs.getInt("id"));
            connection.close();
            return feedback;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(Feedback feedback) {
        try {
            Connection connection = Connector.getInstance().getConnection();

            String query = "DELETE FROM Feedback WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, feedback.getId());
            ps.execute();

            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Feedback update(Feedback feedback) {
        try {
            String query = "UPDATE feedback SET requisitionID = ? ," +
                    "nbOfPersonControlled = ?," +
                    "nbOfInfractions = ?," +
                    "nbOfWeaponInfractions = ?," +
                    "nbOfRoadInfractions = ?," +
                    "nbOfPenalInfractions = ?," +
                    "nbOfOffences = ?," +
                    "nbOfFinesFifthClass = ?," +
                    "nbOfFinesOtherClass = ?," +
                    "nbOfVehiculesControlled = ?," +
                    "nbOfImmobilisations = ?," +
                    "nbOfReports = ?," +
                    "nbOfPersonsListened = ?," +
                    "nbOfCustody = ?" +
                    "WHERE  id = ?";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, feedback.getRequisition().getId());
            ps.setInt(2, feedback.getNbOfPersonControlled());
            ps.setInt(3, feedback.getNbOfInfractions());
            ps.setInt(4, feedback.getNbOfWeaponInfractions());
            ps.setInt(5, feedback.getNbOfRoadInfractions());
            ps.setInt(6, feedback.getNbOfPenalInfractions());
            ps.setInt(7, feedback.getNbOfOffences());
            ps.setInt(8, feedback.getNbOfFinesFifthClass());
            ps.setInt(9, feedback.getNbOfFinesOtherClass());
            ps.setInt(10, feedback.getNbOfVehiculesControlled());
            ps.setInt(11, feedback.getNbOfImmobilisations());
            ps.setInt(12, feedback.getNbOfReports());
            ps.setInt(13, feedback.getNbOfPersonsListened());
            ps.setInt(14, feedback.getNbOfCustody());
            ps.execute();
            connection.close();
            return feedback;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Feedback> search(Requisition requisition) {
        ArrayList<Feedback> feedbackSearchResult = new ArrayList<Feedback>();
        try {
            String query = "SELECT id FROM Feedback WHERE requisitionID = ?";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, requisition.getId());
            ResultSet rs = ps.executeQuery(query);
            while (rs.next()) {
                Feedback feedback = this.getOne(rs.getInt("id"));
                feedbackSearchResult.add(feedback);
            }
            return feedbackSearchResult;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Feedback getOne(int id) {
        try {
            String query = "SELECT * FROM Feedback WHERE id = ?";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            //Variables to create to requisition
            int feedbackID = id;
            Requisition requisition;
            int nbOfPersonControlled;
            int nbOfInfractions;
            int nbOfWeaponInfractions;
            int nbOfRoadInfractions;
            int nbOfPenalInfractions;
            int nbOfOffences;
            int nbOfFinesFifthClass;
            int nbOfFinesOtherClass;
            int nbOfVehiculesControlled;
            int nbOfImmobilisations;
            int nbOfReports;
            int nbOfPersonsListened;
            int nbOfCustody;

            if (!rs.next()) {
                return null;
            } else {
                int requisitionID = rs.getInt("requisitionID");
                requisition = FactoryDAOPG.getInstance().createRequisitionDAO().getOne(requisitionID);
                nbOfPersonControlled = rs.getInt("nbOfPersonControlled");
                nbOfInfractions = rs.getInt("nbOfInfractions");
                nbOfWeaponInfractions = rs.getInt("nbOfWeaponInfractions");
                nbOfRoadInfractions = rs.getInt("nbOfRoadInfractions");
                nbOfPenalInfractions = rs.getInt("nbOfPenalInfractions");
                nbOfOffences = rs.getInt("nbOfOffences");
                nbOfFinesFifthClass = rs.getInt("nbOfFinesFifthClass");
                nbOfFinesOtherClass = rs.getInt("nbOfFinesOtherClass");
                nbOfVehiculesControlled = rs.getInt("nbOfVehiculesControlled");
                nbOfImmobilisations = rs.getInt("nbOfImmobilisations");
                nbOfReports = rs.getInt("nbOfReports");
                nbOfPersonsListened = rs.getInt("nbOfPersonsListened");
                nbOfCustody = rs.getInt("nbOfCustody");
                connection.close();
            }


            // create the requisition model

            Feedback feedback = new Feedback(
                    feedbackID,
                    requisition,
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
                    nbOfCustody
            );
            return feedback;


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Feedback> getAllFeedback() {
        ArrayList<Feedback> feedbackSearchResult = new ArrayList<Feedback>();
        try {
            String query = "SELECT id FROM Feedback";
            Connection connection = Connector.getInstance().getConnection();
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                Feedback feedback = this.getOne(rs.getInt("id"));
                feedbackSearchResult.add(feedback);
            }
            return feedbackSearchResult;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}