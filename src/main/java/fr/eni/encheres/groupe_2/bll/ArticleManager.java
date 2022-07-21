package fr.eni.encheres.groupe_2.bll;

import fr.eni.encheres.groupe_2.bo.Article;
import fr.eni.encheres.groupe_2.bo.Categorie;
import fr.eni.encheres.groupe_2.dal.DAO;
import fr.eni.encheres.groupe_2.dal.DaoFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ArticleManager {

    private static ArticleManager instance;

    private DAO<Article> articleDAO = DaoFactory.articleDao();

    public static ArticleManager getInstance() {
        if (instance == null) {
            return instance = new ArticleManager();
        }
        return instance;
    }

    public List<Article> getAllArticles() {
        return articleDAO.selectALL();
    }

    public Article getSelectedArticle(int id) {
        return articleDAO.selectById(id);
    }

    public List<Article> filteredListArticles(int idCategorie) {
        List<Article> listeafiltrer = getAllArticles();
        List<Article> listefiltre = new ArrayList<>();
        for (Article a : listeafiltrer
        ) {
        if (a.getNoCategorie()==idCategorie){
            listefiltre.add(a);
            System.out.println(a);
        }

        }
        return listefiltre;
    }
    public List<Article> filteredListArticlesByName(String nomArticle) {
        List<Article> nomafiltrer = getAllArticles();
        List<Article> nomfiltrer = new ArrayList<>();
        for (Article b : nomafiltrer
        ) {
            if (Objects.equals(b.getNomArticle(), nomArticle)){
                nomfiltrer.add(b);
                System.out.println(b);
            }

        }
        return nomfiltrer;
    }
}

