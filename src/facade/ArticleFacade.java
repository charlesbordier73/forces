package facade;

import bl.dao.ArticleDAO;
import bl.dao.DAOFactory;
import bl.model.Article;
import persistence.FactoryDAOPG;

import java.util.ArrayList;

public class ArticleFacade  {


    private static ArticleFacade articleFacade = null;

    private Article article = null;

    public static ArticleFacade getInstance() {
        if (articleFacade == null) {
            synchronized(RequisitionFacade.class) {
                if (articleFacade == null) {
                    articleFacade = new ArticleFacade();
                }
            }
        }
        return articleFacade;
    }

    public void setCurrentArticle(Article article) {
        this.article = article;
    }

    public Article getCurrentArticle() {
        return this.article;
    }

    public Article create(Article article) {
        DAOFactory factory = FactoryDAOPG.getInstance();
        ArticleDAO articleDAO = ((FactoryDAOPG) factory).createArticleDAO();
        Article createdArticle = articleDAO.create(article);

        return createdArticle;
    }

    public boolean delete(Article article) {
        DAOFactory factory = FactoryDAOPG.getInstance();
        ArticleDAO articleDAO = ((FactoryDAOPG) factory).createArticleDAO();
        boolean deleted = articleDAO.delete(article);

        return deleted;
    }

    public Article update(Article article) {
        DAOFactory factory = FactoryDAOPG.getInstance();
        ArticleDAO articleDAO = ((FactoryDAOPG) factory).createArticleDAO();
        Article updatedArticle = articleDAO.update(article);

        return updatedArticle;
    }

    public ArrayList<Article> search(String code) {
        DAOFactory factory = FactoryDAOPG.getInstance();
        ArticleDAO articleDAO = ((FactoryDAOPG) factory).createArticleDAO();
        ArrayList<Article> articles = articleDAO.search(code);

        return articles;
    }

    public Article getOne(int id) {
        DAOFactory factory = FactoryDAOPG.getInstance();
        ArticleDAO articleDAO = ((FactoryDAOPG) factory).createArticleDAO();
        Article article = articleDAO.getOne(id);

        return article;
    }

    public ArrayList<Article> getAllArticle() {
        DAOFactory factory = FactoryDAOPG.getInstance();
        ArticleDAO articleDAO = ((FactoryDAOPG) factory).createArticleDAO();
        ArrayList<Article> articles = articleDAO.getAllArticle();

        return articles;
    }

}
