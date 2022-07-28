package fr.eni.encheres.groupe_2.bll;

import fr.eni.encheres.groupe_2.bo.Article;
import fr.eni.encheres.groupe_2.bo.Categorie;
import fr.eni.encheres.groupe_2.bo.Enchere;
import fr.eni.encheres.groupe_2.bo.Utilisateur;
import fr.eni.encheres.groupe_2.dal.DAO;
import fr.eni.encheres.groupe_2.dal.DaoFactory;
import fr.eni.encheres.groupe_2.dal.EncheresDAO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ArticleManager {

    private static ArticleManager instance;

    private static DAO<Article> articleDAO = DaoFactory.articleDao();

    private static EncheresDAO encheresFeatureUtilisateur = DaoFactory.encheresFeatureUtilisateur();

    private static DAO<Enchere> enchereDAO = DaoFactory.enchereDAO();

    /**
     * Instance de Article Manager
     * @return une instance du manager
     */
    public static ArticleManager getInstance() {
        if (instance == null) {
            return instance = new ArticleManager();
        }
        return instance;
    }

    /**
     * Constructeur prive
     */
    private ArticleManager() {
    }

    /**
     * Charge le catalogue d'article existant en BDD
     * @return toute la liste d'article en BDD
     */
    private static List<Article> catalogueArticle(){
        return articleDAO.selectALL();
    }

    /**
     * Permet d'acceder au catalogue hors du controller
     * @return toute la liste d'article stocker en static
     */
    public List<Article> catalogueEnchereOuverte(){
        List<Article> enchereouverte =new ArrayList<>();
        List<Article> toutLesArticles=catalogueArticle();
        for (Article a:toutLesArticles
             ) {
            Date today = new Date();
            Date yesterday = new Date(today.getTime()-(1000 * 60 * 60 * 24));
            if(a.getDateDebutEncheres().before(today) && a.getDateFinEncheres().after(yesterday) ){

                enchereouverte.add(a);
            }
        }

        return enchereouverte ;
    }

    /**
     * Permet la selection d'un article par son ID
     * @param id int envoyer par le controller
     * @return L'article selectionner
     */
    public Article getSelectedArticle(int id) {
        Article article = null;
        List<Article> listeDesArticle = catalogueArticle();
        for (Article a:listeDesArticle
             ) {
            if(a.getNoArticle()==id){
                article=a;
            }
        }
    return article;
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

    /**
     * Filtre les aricle par nom ( lettre contenues dans le titre de l'article)
     * @param nomArticle le nom de l'article (String)
     * @param idCategorie et l'id de se categorie (si O = toutes les categories)
     * @return La liste filtre des articles
     */
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

    /**
     *Filtre les Article disponible pour les encheres ouverte a partir du jour de requete
     * @param idCategorie l'id de la categorie (si O = toutes les categories)
     * @param motClef   le nom de l'article (String) si renseigner
     * @param ouverte
     * @return liste encheres ouverte
     */
    public List<Article> filteredListByEnchereOuverte(int idCategorie,String motClef,boolean ouverte){
        List<Article> listeafiltrer = filteredListArticlesByName(motClef,idCategorie);
        List<Article> listarenvoyer = new ArrayList<>();
        if(ouverte){
            for (Article a:listeafiltrer
                 ) {
//TODO verifier les methode de date
                Date today = new Date();
                Date tomorrow = new Date(today.getTime()+ (1000 * 60 * 60 * 24));

                if(a.getDateDebutEncheres().before(today) && a.getDateFinEncheres().after(tomorrow)){
                    listarenvoyer.add(a);
                }
            }
        }else {
            listarenvoyer = listeafiltrer;
        }
        return listarenvoyer;
    };

    /**
     * Filtre la liste des articles (des enchers ouverte) par numero d'article
     * @param listeDesIdArticles
     * @return liste des articles
     */
    public List<Article> filteredListByIdArticle(List<Integer> listeDesIdArticles){
        List<Article> listeafiltrer = catalogueEnchereOuverte();
        List<Article> listeFiltre =new ArrayList<>();
        for (Article a:listeafiltrer
             ) {
           if (listeDesIdArticles.contains(a.getNoArticle())){
               listeFiltre.add(a);
           }
        }
        return listeFiltre;
    }

    /**
     * filtre la liste des article dont les encheres sont terminer
     * @return liste des articles
     */
    public List<Article> filteredByStatusTermnine(){
        List<Article> listeafiltrer =catalogueArticle();
        List<Article> listeFiltrer=new ArrayList<>();
        Date today = new Date();
        for (Article a:listeafiltrer
             ) {
            if (a.getDateFinEncheres().before(today)){
                listeFiltrer.add(a);
            }
        }
        return listeFiltrer;
    }

    /**
     * filtre la liste des article dont les encheres sont pas commencer
     * @return liste des articles
     */
    public List<Article> filteredByStatusNonCommencer(){
        List<Article> listeafiltrer =catalogueArticle();
        List<Article> listeFiltrer=new ArrayList<>();
        Date today = new Date();
        //TODO verifier les methode de date
        for (Article a:listeafiltrer
             ) {
            if(a.getDateDebutEncheres().after(today)){
                listeFiltrer.add(a);
            }
        }
        return listeFiltrer;
    }

    /**
     * filtre la liste des articles dont l'utlisateur est le vendeur
     * @param id int noUtilisteur ( vendeur )
     * @return liste des articles
     */
    public List<Article> filteredByMesArticles(int id){
        List<Article> toutLesArticles=catalogueArticle();
        List<Article> listeFiltrer=new ArrayList<>();
        for (Article a:toutLesArticles
             ) {
            if(a.getUtilisateur().getNoUtilisateur()==id){
                listeFiltrer.add(a);
            }
        }
        return listeFiltrer;
    }

    /**
     * Ajoute un nouvel article a vendre en BDD
     * @param article Article renseigner par l'utlisateur
     * @throws BuissnessException Renvoye une erreur a l'utlistateur en cas de lever d'exception
     */
    public void addNewArticle(Article article) throws BuissnessException {

        if(article.getDateDebutEncheres().after(article.getDateFinEncheres())){
            throw new BuissnessException(CodeErrorBll.CHAMP_INVALIDE);
        }
        else {
            articleDAO.addNew(article);
        }

    }

    /**
     * Verifie a la connextion de l'utlisateur si ses produit mis en vente sont en ecnhere termnie et recupere le
     * montant le plus eleve des encheres faite , si aucune enchere sur l'article , la valeur de vente finale passe a -1
     * @param idUtilisateur le numero de l'utlisteur qui se connecte
     */
    public void verifEnchereFini(int idUtilisateur) {
        List<Article> mesArticles = filteredByMesArticles(idUtilisateur);
        List<Enchere> listeDesEncheres = enchereDAO.selectALL();
        Date today = new Date();
        int montant =0;
        for (Article a:mesArticles) {
            if(a.getDateFinEncheres().before(today) && a.getPrixVente()==0) {
                montant = 0;
                for(Enchere e:listeDesEncheres) {
                    // Si le numero de l'article correspond a l'"enchere et le montant est plus eleve
                    if(e.getNo_article() == a.getNoArticle() && e.getMontantEnchere() > montant){
                            montant = e.getMontantEnchere();
                    }
                }
                if(montant > 0){
                    // on peut crediter pour cet article
                    int nouveauTotal = montant + EnchereManager.creditDisponible(idUtilisateur);
                    encheresFeatureUtilisateur.updatePrixVente(a.getNoArticle(),montant);
                    encheresFeatureUtilisateur.updateCredit(idUtilisateur,nouveauTotal);
                } else {
                    // il n'y a pas eu d'enchere on credite a -1 pour par boucler dessus
                    encheresFeatureUtilisateur.updatePrixVente(a.getNoArticle(),-1);
                }
            }
        }
    }

    public void rembourseEncherisseur(int idUtilisateur) {
        List<Article> mesArticles = filteredByMesArticles(idUtilisateur);
        List<Enchere> listeDesEncheres = enchereDAO.selectALL();
        Date today = new Date();
        Date yesterday = new Date(today.getTime()-(1000 * 60 * 60 * 24));
        int montant =0;
        int idEncherisseur=0;
        for (Article a:mesArticles
             ) {
            if( a.getDateFinEncheres().after(yesterday)){
                montant =0 ;
                for (Enchere e:listeDesEncheres
                     ) {
                    if(e.getNo_article()==a.getNoArticle() && e.getMontantEnchere()>montant){
                        montant=e.getMontantEnchere();
                        idEncherisseur=e.getNo_utilisateur();
                    }
                }
                if(montant>0){
                    int nouveauTotal = montant + EnchereManager.creditDisponible(idEncherisseur);
                    encheresFeatureUtilisateur.updateCredit(idEncherisseur,nouveauTotal);
                }
            }
        }

    }
}

