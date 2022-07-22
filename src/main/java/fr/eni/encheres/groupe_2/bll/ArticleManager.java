package fr.eni.encheres.groupe_2.bll;

import fr.eni.encheres.groupe_2.bo.Article;
import fr.eni.encheres.groupe_2.bo.Categorie;
import fr.eni.encheres.groupe_2.dal.DAO;
import fr.eni.encheres.groupe_2.dal.DaoFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ArticleManager {

    private static ArticleManager instance;

    private static DAO<Article> articleDAO = DaoFactory.articleDao();

    public static ArticleManager getInstance() {
        if (instance == null) {
            return instance = new ArticleManager();
        }
        return instance;
    }

    private ArticleManager() {
    }

    /**
     * Charge le catalogue d'article existant en BDD
     * @return toute la liste d'article en dbb
     */
    private static List<Article> catalogueArticle(){
        return articleDAO.selectALL();
    }

    /**
     * Permet d'acceder au catalogue hors du controller
     * @return toute la liste d'article stocker en static
     */
    public List<Article> catalogue(){
        return catalogueArticle();
    }
    public Article getSelectedArticle(int id) {
        return articleDAO.selectById(id);
    }

    /**
     * filtre la liste des article par categorie
     * @param idCategorie int id de la categorie
     * @return la liste filtre , si Toute categorie , renvoie la liste du catalogue
     */
    public List<Article> filteredByCategorie(int idCategorie) {
        List<Article> listeafiltrer = catalogueArticle();
        List<Article> listefiltre = new ArrayList<>();
        if(idCategorie==0){
            return listeafiltrer;
        }
        for (Article a : listeafiltrer
        ) {
        if (a.getNoCategorie()==idCategorie){
            listefiltre.add(a);
            System.out.println(a);
        }
        }
        return listefiltre;
    }
    public List<Article> filteredListArticlesByName(String nomArticle , int idCategorie) {
        List<Article> listeAfiltrer = filteredByCategorie(idCategorie);
        List<Article> listeFiltre = new ArrayList<>();
        System.out.println(nomArticle);

        for (Article b : listeAfiltrer
        ) {
            if (b.getNomArticle().toLowerCase().contains(nomArticle.toLowerCase())){
                listeFiltre.add(b);
                System.out.println(b);
            }

        }
        return listeFiltre;
    }
    public List<Article> filteredListByEnchereOuverte(int idCategorie,String motClef,boolean ouverte){
        List<Article> listeafiltrer = filteredListArticlesByName(motClef,idCategorie);
        List<Article> listarenvoyer = new ArrayList<>();
        if(ouverte){
            for (Article a:listeafiltrer
                 ) {
                long miliseconds = System.currentTimeMillis();
                Date today = new Date(miliseconds);
                if(a.getDateDebutEncheres().after(today)|| a.getDateDebutEncheres().equals(today)){
                    listarenvoyer.add(a);
                }
            }
        }else {
            listarenvoyer = listeafiltrer;
        }
        return listarenvoyer;
    };

    public void addNewArticle(Article article) throws BuissnessException {
        articleDAO.addNew(article);
    }

}

