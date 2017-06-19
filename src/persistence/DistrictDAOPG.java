package persistence;

import bl.dao.DistrictDAO;
import bl.model.District;
import persistence.connector.Connector;

import java.sql.*;
import java.util.ArrayList;


public class DistrictDAOPG extends DistrictDAO {

    @Override
    public District create(District district) {
        try {
            String query = "INSERT INTO District (name, map) VALUES (?,?,?)";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, district.getName());
            ps.setString(2, district.getMap());
            ps.execute();

            String queryID = "SELECT MAX(id) AS id FROM district ";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(queryID);
            rs.next();
            district.setId(rs.getInt("id"));
            connection.close();
            return district;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(District district) {
        try {
            String query = "DELETE FROM district WHERE id = ?";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, district .getId());
            ps.execute();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public District update(District district) {
        try {
            String query = "UPDATE district SET name = ? ," +
                    "map = ?" +
                    "WHERE  id = ?";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, district.getName());
            ps.setString(2, district.getMap());
            ps.setInt(3, district.getId());
            ps.execute();
            connection.close();
            return district;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<District> search(String name) {
        ArrayList<District> districtSearchResult = new ArrayList<District>();
        try {
            String query = "SELECT id FROM district WHERE name LIKE ? ";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                District district = this.getOne(rs.getInt("id"));
                districtSearchResult.add(district);
            }
            return districtSearchResult;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public District getOne(int id) {
        try {
            String query = "SELECT * FROM District WHERE id = ?";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                return null;
            } else {
                int districtID = id;
                String name = rs.getString("name");
                String map = rs.getString("map");
                District district = new District(districtID,
                        name,
                        map);
                connection.close();
                return district;
            }


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public ArrayList<District> getAllDistrict() {
        ArrayList<District> districtSearchResult = new ArrayList<District>();
        try {
            String query = "SELECT id FROM District";
            Connection connection = Connector.getInstance().getConnection();
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                District district = this.getOne(rs.getInt("id"));
                districtSearchResult.add(district);
            }
            return districtSearchResult;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


}
