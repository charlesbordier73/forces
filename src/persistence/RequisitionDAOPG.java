package persistence;

import bl.dao.RequisitionDAO;
import bl.model.Article;
import bl.model.Infraction;
import bl.model.Requisition;
import bl.model.Unit;
import persistence.connector.Connector;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class RequisitionDAOPG extends RequisitionDAO {
    @Override
    public Requisition create(Requisition requisition) {
        try {
            String query = "INSERT INTO Requisition (startDate, endDate, map, motivation, requestingUnit, approved) VALUES (?,?,?,?,?, ?)";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setTimestamp(1, new Timestamp(requisition.getStartDate().getTime()));
            ps.setTimestamp(2, new Timestamp(requisition.getEndDate().getTime()));
            ps.setString(3, requisition.getMap());
            ps.setString(4, requisition.getMotivation());
            ps.setInt(5, requisition.getRequestingUnit().getId());
            ps.setBoolean(6, requisition.isApprouved());
            ps.execute();
            String queryID = "SELECT MAX(id) AS id FROM requisition ";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(queryID);
            rs.next();
            requisition.setId(rs.getInt("id"));
            connection.close();
            if(requisition.getAssistingUnit() != null){
                createAssistingUnit(requisition.getId(), requisition.getAssistingUnit());
            }
            if(requisition.getArticles() != null){
                createConcernedArticle(requisition.getId(), requisition.getArticles());
            }
            if(requisition.getInfractions() != null){
                createSearchedInfraction(requisition.getId(), requisition.getInfractions());
            }
            return requisition;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void createAssistingUnit(int id, ArrayList<Unit> assistingUnit) {
        try {
            for (Unit unit : assistingUnit) {
                String queryAssistingUnit = "INSERT INTO AssistingUnits (requisitionsID, unitID) VALUES (?,?)";
                Connection connection = Connector.getInstance().getConnection();
                PreparedStatement ps = connection.prepareStatement(queryAssistingUnit);
                ps.setInt(1, id);
                ps.setInt(2, unit.getId());
                ps.execute();
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void createConcernedArticle(int id, ArrayList<Article> concernedArticle) {
        try {
            for (Article article : concernedArticle) {
                String queryConcernedArticles = "INSERT INTO ConcernedArticles (requisitionsID, articleID) VALUES (?,?)";
                Connection connection = Connector.getInstance().getConnection();
                PreparedStatement ps = connection.prepareStatement(queryConcernedArticles);
                ps.setInt(1, id);
                ps.setInt(2, article.getId());
                ps.execute();
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void createSearchedInfraction(int id, ArrayList<Infraction> searchedInfraction) {
        try {
            for (Infraction infraction : searchedInfraction) {
                String querySearchedInfraction = "INSERT INTO ConcernedInfractions (requisitionsID, infractionID) VALUES (?,?)";
                Connection connection = Connector.getInstance().getConnection();
                PreparedStatement ps = connection.prepareStatement(querySearchedInfraction);
                ps.setInt(1, id);
                ps.setInt(2, infraction.getId());
                ps.execute();
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public boolean delete(Requisition requisition) {
        try {
            Connection connection = Connector.getInstance().getConnection();

            String queryUnit = "DELETE FROM AssistingUnit WHERE idReq = ?";
            PreparedStatement psUnit = connection.prepareStatement(queryUnit);
            psUnit.setInt(1, requisition.getId());
            psUnit.execute();

            String queryInfraction = "DELETE FROM SearchedInfraction WHERE idReq = ?";
            PreparedStatement psInfraction = connection.prepareStatement(queryInfraction);
            psInfraction.setInt(1, requisition.getId());
            psInfraction.execute();

            String queryArticle = "DELETE FROM ConcernedArticle WHERE idReq = ?";
            PreparedStatement psArticle = connection.prepareStatement(queryArticle);
            psArticle.setInt(1, requisition.getId());
            psArticle.execute();

            String query = "DELETE FROM Requisition WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, requisition.getId());
            ps.execute();

            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Requisition update(Requisition requisition) {
        try {
            String query = "UPDATE requisition SET startDate = ? ," +
                    "endDate = ?," +
                    "map = ?," +
                    "motivation = ?," +
                    "requestingUnit = ?," +
                    "approved = ?" +
                    "WHERE  id = ?";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setTimestamp(1, new Timestamp(requisition.getStartDate().getTime()));
            ps.setTimestamp(2, new Timestamp(requisition.getEndDate().getTime()));
            ps.setString(3, requisition.getMap());
            ps.setString(4, requisition.getMotivation());
            ps.setInt(5, requisition.getRequestingUnit().getId());
            ps.setBoolean(6, requisition.isApprouved());
            ps.setInt(7, requisition.getId());
            ps.execute();
            connection.close();
            return requisition;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Requisition> search(Date startDate) {
        ArrayList<Requisition> reqSearchResult = new ArrayList<Requisition>();
        try {
            String query = "SELECT id FROM Requisition WHERE startDate > ?";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setTimestamp(1, new Timestamp(startDate.getTime()));
            ResultSet rs = ps.executeQuery(query);
            while (rs.next()) {
                Requisition req = this.getOne(rs.getInt("id"));
                reqSearchResult.add(req);
            }
            return reqSearchResult;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Requisition getOne(int id) {
        try {
            String query = "SELECT * FROM Requisition WHERE id = ?";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            //Variables to create to requisition
            int reqID = id;
            Date startDate;
            Date endDate;
            String map;
            String motivation;
            Unit requestingUnit;
            ArrayList<Unit> assistingUnits = new ArrayList<Unit>();
            ArrayList<Article> concernedArticles = new ArrayList<Article>();
            ArrayList<Infraction> concernedInfractions = new ArrayList<Infraction>();
            boolean approved;


            if (!rs.next()) {
                return null;
            } else {
                map = rs.getString("map");
                motivation = rs.getString("motivation");
                startDate = rs.getTimestamp("startDate");
                endDate = rs.getTimestamp("endDate");
                int requestingUnitID = rs.getInt("requestingUnit");
                requestingUnit = FactoryDAOPG.getInstance().createUnitDAO().getOne(requestingUnitID);
                approved = rs.getBoolean("approved");
            }
            // Search in assistingUnits / concernedArticles / concernedInfractions
            // assistingUnits
            String queryAssistingUnits = "SELECT unitID FROM AssistingUnits where requisitionsID = ? ;";
            ps = connection.prepareStatement(queryAssistingUnits);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Unit unit = FactoryDAOPG.getInstance().createUnitDAO().getOne(rs.getInt("unitID"));
                assistingUnits.add(unit);
            }

            // concernedArticles
            String queryConcernedArticles = "SELECT articleID FROM ConcernedArticles where requisitionsID = ? ;";
            ps = connection.prepareStatement(queryConcernedArticles);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Article article = FactoryDAOPG.getInstance().createArticleDAO().getOne(rs.getInt("articleID"));
                concernedArticles.add(article);
            }

            // concernedInfractions
            String queryConcernedInractions = "SELECT infractionID FROM ConcernedInfractions where requisitionsID = ? ;";
            ps = connection.prepareStatement(queryConcernedInractions);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Infraction infraction = FactoryDAOPG.getInstance().createInfractionDAO().getOne(rs.getInt("infractionID"));
                concernedInfractions.add(infraction);
            }

            // create the requisition model

            Requisition requisition = new Requisition(
                    reqID,
                    startDate,
                    endDate,
                    map,
                    motivation,
                    requestingUnit,
                    assistingUnits,
                    concernedInfractions,
                    concernedArticles,
                    approved
                    );
            connection.close();
            return requisition;


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Requisition> getAllRequisition() {
        ArrayList<Requisition> reqSearchResult = new ArrayList<Requisition>();
        try {
            String query = "SELECT id FROM Requisition";
            Connection connection = Connector.getInstance().getConnection();
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                Requisition req = this.getOne(rs.getInt("id"));
                reqSearchResult.add(req);
            }
            return reqSearchResult;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Requisition> getRequests() {
        ArrayList<Requisition> reqSearchResult = new ArrayList<Requisition>();
        try {
            String query = "SELECT id FROM Requisition WHERE approved = FALSE ORDER BY id";
            Connection connection = Connector.getInstance().getConnection();
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                Requisition req = this.getOne(rs.getInt("id"));
                reqSearchResult.add(req);
            }
            return reqSearchResult;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Requisition> getFutureRequisitions() {
        ArrayList<Requisition> reqSearchResult = new ArrayList<Requisition>();
        try {
            String query = "SELECT id FROM Requisition WHERE startDate > CURRENT_DATE";
            Connection connection = Connector.getInstance().getConnection();
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                Requisition req = this.getOne(rs.getInt("id"));
                reqSearchResult.add(req);
            }
            return reqSearchResult;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Requisition> getAllPastRequisitions() {
        ArrayList<Requisition> reqSearchResult = new ArrayList<Requisition>();
        try {
            String query = "SELECT id FROM Requisition WHERE endDate <= CURRENT_DATE";
            Connection connection = Connector.getInstance().getConnection();
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                Requisition req = this.getOne(rs.getInt("id"));
                reqSearchResult.add(req);
            }
            return reqSearchResult;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Requisition> getWithFeedbackRequisitions() {
        ArrayList<Requisition> reqSearchResult = new ArrayList<Requisition>();
        try {
            String query = "SELECT requisitionID FROM feedback";
            Connection connection = Connector.getInstance().getConnection();
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                Requisition req = this.getOne(rs.getInt("requisitionID"));
                reqSearchResult.add(req);
            }
            return reqSearchResult;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Requisition> getWithoutFeedbackRequisitions() {
        ArrayList<Requisition> reqSearchResult = new ArrayList<Requisition>();
        try {
            String query = "SELECT id FROM requisition EXCEPT SELECT requisitionID FROM feedback";
            Connection connection = Connector.getInstance().getConnection();
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                Requisition req = this.getOne(rs.getInt("id"));
                reqSearchResult.add(req);
            }
            return reqSearchResult;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}