package persistence;

import bl.dao.InfractionDAO;
import bl.model.Infraction;
import persistence.connector.Connector;

import java.sql.*;
import java.util.ArrayList;


public class InfractionDAOPG extends InfractionDAO {

    @Override
    public Infraction create(Infraction infraction) {
        try {
            String query = "INSERT INTO Infraction (label) VALUES (?)";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, infraction.getLabel());
            ps.execute();

            String queryID = "SELECT MAX(id) AS id FROM infraction ";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(queryID);
            rs.next();
            infraction.setId(rs.getInt("id"));
            connection.close();
            return infraction;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(Infraction infraction) {
        try {
            String query = "DELETE FROM infraction WHERE id = ?";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, infraction .getId());
            ps.execute();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Infraction update(Infraction infraction) {
        try {
            String query = "UPDATE infraction SET label = ? " +
                    "WHERE  id = ?";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, infraction.getLabel());
            ps.setInt(2, infraction.getId());
            ps.execute();
            connection.close();
            return infraction;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Infraction> search(String label) {
        ArrayList<Infraction> infractionSearchResult = new ArrayList<Infraction>();
        try {
            String query = "SELECT id FROM infraction WHERE label LIKE ? ";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, label);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Infraction infraction = this.getOne(rs.getInt("id"));
                infractionSearchResult.add(infraction);
            }
            return infractionSearchResult;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Infraction getOne(int id) {
        try {
            String query = "SELECT * FROM Infraction WHERE id = ?";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                return null;
            } else {
                int infractionID = id;
                String label = rs.getString("label");
                Infraction infraction = new Infraction(infractionID,
                        label);
                connection.close();
                return infraction;
            }


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public ArrayList<Infraction> getAllInfraction() {
        ArrayList<Infraction> infractionSearchResult = new ArrayList<Infraction>();
        try {
            String query = "SELECT id FROM Infraction";
            Connection connection = Connector.getInstance().getConnection();
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                Infraction infraction = this.getOne(rs.getInt("id"));
                infractionSearchResult.add(infraction);
            }
            return infractionSearchResult;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


}
