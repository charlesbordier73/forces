package bl.dao;

import bl.model.Article;

import java.util.ArrayList;

public abstract class ArticleDAO {

    public abstract Article create(Article article);

    public abstract boolean delete(Article article);

    public abstract Article update(Article article);

    public abstract ArrayList<Article> search(String code);

    public abstract Article getOne(int id);

    public abstract ArrayList<Article> getAllArticle();

}
