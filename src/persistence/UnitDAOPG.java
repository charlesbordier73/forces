package persistence;

import bl.dao.UnitDAO;
import bl.model.Unit;
import persistence.connector.Connector;

import java.sql.*;
import java.util.ArrayList;


public class UnitDAOPG extends UnitDAO {

    @Override
    public Unit create(Unit unit) {
        try {
            String query = "INSERT INTO Unit (unitName, sigle, mail) VALUES (?,?,?)";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, unit.getName());
            ps.setString(2, unit.getSigle());
            ps.setString(3, unit.getMail());
            ps.execute();

            String queryID = "SELECT MAX(id) AS id FROM unit ";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(queryID);
            rs.next();
            unit.setId(rs.getInt("id"));
            connection.close();
            return unit;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Database Access method to delete the Unit given as a parameter to the method
     * @param unit :  Unit :  the unit to be deleted from the database
     * @return boolean : true if the unit has been deleted, false otherwise
     */
    @Override
    public boolean delete(Unit unit) {
        try {
            String query = "DELETE FROM unit WHERE id = ?";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, unit .getId());
            ps.execute();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Unit update(Unit unit) {
        try {
            String query = "UPDATE unit SET unitName = ?," +
                    "sigle = ?," +
                    "mail = ?" +
                    "WHERE  id = ?";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, unit.getName());
            ps.setString(2, unit.getSigle());
            ps.setString(3, unit.getMail());
            ps.setInt(4, unit.getId());
            ps.execute();
            connection.close();
            return unit;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Unit> search(String unitName) {
        ArrayList<Unit> unitSearchResult = new ArrayList<Unit>();
        try {
            String query = "SELECT id FROM unit WHERE unitName LIKE ? ";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, (String)( "%" + unitName + "%" ));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Unit unit = this.getOne(rs.getInt("id"));
                unitSearchResult.add(unit);
            }
            return unitSearchResult;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Unit getOne(int id) {
        try {
            String query = "SELECT * FROM Unit WHERE id = ?";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                return null;
            } else {
                int unitID = id;
                String unitName = rs.getString("unitName");
                String sigle = rs.getString("sigle");
                String mail = rs.getString("mail");
                Unit unit = new Unit(unitID,
                        unitName,
                        sigle,
                        mail);
                connection.close();
                return unit;
            }


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public ArrayList<Unit> getAllUnit() {
        ArrayList<Unit> unitSearchResult = new ArrayList<Unit>();
        try {
            String query = "SELECT id FROM Unit";
            Connection connection = Connector.getInstance().getConnection();
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                Unit unit = this.getOne(rs.getInt("id"));
                unitSearchResult.add(unit);
            }
            return unitSearchResult;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


}
