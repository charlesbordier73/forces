package persistence;

import bl.dao.ArticleDAO;
import bl.model.Article;
import persistence.connector.Connector;

import java.sql.*;
import java.util.ArrayList;


public class ArticleDAOPG extends ArticleDAO {

    @Override
    public Article create(Article article) {
        try {
            String query = "INSERT INTO Article (code, alinea, content) VALUES (?,?,?)";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, article.getCode());
            ps.setInt(2, article.getAlinea());
            ps.setString(3, article.getContent());
            ps.execute();

            String queryID = "SELECT MAX(id) AS id FROM article ";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(queryID);
            rs.next();
            article.setId(rs.getInt("id"));
            connection.close();
            return article;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(Article article) {
        try {
            String query = "DELETE FROM article WHERE id = ?";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, article .getId());
            ps.execute();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Article update(Article article) {
        try {
            String query = "UPDATE article SET code = ? ," +
                    "alinea = ?," +
                    "content = ?" +
                    "WHERE  id = ?";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, article.getCode());
            ps.setInt(2, article.getAlinea());
            ps.setString(3, article.getContent());
            ps.setInt(4, article.getId());
            ps.execute();
            connection.close();
            return article;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Article> search(String code) {
        ArrayList<Article> articleSearchResult = new ArrayList<Article>();
        try {
            String query = "SELECT id FROM article WHERE code LIKE ? ";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Article article = this.getOne(rs.getInt("id"));
                articleSearchResult.add(article);
            }
            return articleSearchResult;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Article getOne(int id) {
        try {
            String query = "SELECT * FROM Article WHERE id = ?";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                return null;
            } else {
                int articleID = id;
                String code = rs.getString("code");
                int alinea = rs.getInt("alinea");
                String content = rs.getString("content");
                Article article = new Article(articleID,
                        code,
                        alinea,
                        content);
                connection.close();
                return article;
            }


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public ArrayList<Article> getAllArticle() {
        ArrayList<Article> articleSearchResult = new ArrayList<Article>();
        try {
            String query = "SELECT id FROM Article";
            Connection connection = Connector.getInstance().getConnection();
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                Article article = this.getOne(rs.getInt("id"));
                articleSearchResult.add(article);
            }
            return articleSearchResult;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


}
