package fr.eni.encheres.groupe_2.bll;

import fr.eni.encheres.groupe_2.bo.Article;
import fr.eni.encheres.groupe_2.bo.Categorie;
import fr.eni.encheres.groupe_2.dal.DAO;
import fr.eni.encheres.groupe_2.dal.DaoFactory;

import java.util.List;

public class ArticleManager {

    private static ArticleManager instance;

    private DAO<Article> articleDAO = DaoFactory.articleDao();

    public static ArticleManager getInstance() {
        if (instance == null) {
            return instance = new ArticleManager();
        }
        return instance;
    }

    public List<Article> getAllArticles(){
        return articleDAO.selectALL();
    }
}
