package fr.eni.encheres.groupe_2.bll;

import fr.eni.encheres.groupe_2.bo.Enchere;
import fr.eni.encheres.groupe_2.bo.Utilisateur;
import fr.eni.encheres.groupe_2.dal.DAO;
import fr.eni.encheres.groupe_2.dal.DaoFactory;
import fr.eni.encheres.groupe_2.dal.EncheresDAO;

import java.util.ArrayList;
import java.util.List;

public class EnchereManager {
    private static EnchereManager instance;
    private static DAO<Enchere> enchereDAO = DaoFactory.enchereDAO();
    private static DAO<Utilisateur> utilisateurDAO = DaoFactory.utilisateurDAO();

    private static EncheresDAO encheresFeatureUtilisateur = DaoFactory.encheresFeatureUtilisateur();

        /**
     * Instance de Enchere Manager
     * @return une instance du manager
     */
    public static EnchereManager getInstance(){
        if(instance==null){
            return instance = new EnchereManager();
        }
        return instance;
    }

    /**
     * Contructeur prive
     */
    private EnchereManager() {
    }

    /**
     * Stocke la liste complet des encheres qui sont en BDD
     * @return
     */
    private static List<Enchere> catalogueEnchere(){
        return enchereDAO.selectALL();
    }

    /**
     * Faire une enchere , verifie si le montant est bie surperieur a l'enchere precedente`
     * TODO : verifie que l'utilisateur est solvable
     * @param enchere Enchere faite a partir de jsp card-detail-article
     * @throws BuissnessException remonte une erreur en cas de non coformite d'enchere
     */
    public void faireEnchere(Enchere enchere) throws BuissnessException {


        boolean nouvelleEnchere = nouvelleEnchere(enchere.getNo_article());
        boolean enchereValable = enchereValable(enchere.getNo_article(),enchere.getMontantEnchere());
        int creditDisponible = creditDisponible(enchere.getNo_utilisateur());
        boolean isDernierEncherisseur = isDernierEncherisseur(enchere.getNo_utilisateur(),enchere.getNo_article());
        if(creditDisponible<enchere.getMontantEnchere()){
            throw new BuissnessException(CodeErrorBll.CREDIT_INSUFFISANT);
        }
        if(nouvelleEnchere){
            enchereDAO.addNew(enchere);
        }
        else if(enchereValable && !isDernierEncherisseur) {
            enchereDAO.update(enchere);

        }

        // Met à jour Credits Utilisateurs

        int creditRestant = creditDisponible-enchere.getMontantEnchere();
        encheresFeatureUtilisateur.updateCredit(enchere.getNo_utilisateur(),creditRestant);


    }

    /**
     * renvoie la meuilleure offre en cours sur un article donnee
     * @param id l'id de l'article
     * @return le montant de la meuilleur offre
     */
    public int  montantMeuilleurOffre(int id){
        List<Enchere> listeDesEncheresEnCours = catalogueEnchere();
        int montant =0;
        for (Enchere e:listeDesEncheresEnCours
             ) {
            if(e.getNo_article()==id){
                montant=e.getMontantEnchere();
            }
        }
        return montant;
    }



    public List<Enchere> catalogue(){
        return catalogueEnchere();
    }

    /**
     * Defini si c'est une premire enchere ou si c'est un update d'une ancienne
     * @param idArticle l'id de l'article encheri
     * @return si oui ou no nc'est une nouvelle enchere a mettre en bdd
     */
    private boolean nouvelleEnchere(int idArticle){
        boolean nouvelleEnchere=true;
        List<Enchere> listeDesEncheresEnCours = catalogueEnchere();
        for (Enchere e:listeDesEncheresEnCours
             ) {
            if(e.getNo_article()==idArticle){
                nouvelleEnchere=false;
            }
        }
        return nouvelleEnchere;
    }

    /**
     * Filtre la liste des article mis en enchere par l'ulistateur
     * @param id id de l'utilisateur
     * @return la liste des id de ses produit mis en encheres
     */
    public List<Integer> listeMesEncheres(int id){
        List<Enchere> listeDesEncheres = catalogue();
        List<Integer> listeDesEnchresArenvoyer = new ArrayList<>();
        for (Enchere e:listeDesEncheres
             ) {
            if(e.getNo_utilisateur()==id){
                int idDeLenchers = e.getNo_article();
                listeDesEnchresArenvoyer.add(idDeLenchers);
            }

        }
        return listeDesEnchresArenvoyer;
    }

    /**
     * Verifie si le montant propose par l'ulisateur est superieur la la derniere meuilleur offre
     *
     * @param idArticle le numero de l'article encheri
     * @param montant le montant de l'enchere demander
     * @return si oui ou non c'est superieur
     * @throws BuissnessException remonte un message d'erreur dans la jsp
     */
    private boolean enchereValable(int idArticle , int montant) throws BuissnessException {
        System.out.println("ici EV" + idArticle);
        List<Enchere> listeDesEncheresEnCours = catalogueEnchere();
        for (Enchere e:listeDesEncheresEnCours
             ) {
            if(e.getNo_article()==idArticle){
              if(e.getMontantEnchere()>montant){
                  throw new BuissnessException(CodeErrorBll.MONTANT_ENCHERE_INVALIDE);
              }
            }
        }


        return true;
    }

    /**
     * Veridier les credit dispo de l'utlisateur .
     * @param idUtilisateur
     * @return
     */
    private int  creditDisponible(int idUtilisateur){
        List<Utilisateur> utilisateurs = utilisateurDAO.selectALL();
        int creditDispo = 0;
        for (Utilisateur u: utilisateurs
             ) {
            if(u.getNoUtilisateur()==idUtilisateur){
                creditDispo=u.getCredit();
            }
        }
        return creditDispo;

    }

  private boolean isDernierEncherisseur (int idUtilisateur, int noArticle) throws BuissnessException {
        boolean isDernier = false;
        List<Enchere> enchereList = catalogueEnchere();
      for (Enchere e: enchereList) {
          if (e.getNo_article()==noArticle){
              if(e.getNo_utilisateur()==idUtilisateur){
               throw new BuissnessException(CodeErrorBll.MEME_ENCHERISSEUR);
              }
          }
      }return  isDernier;
  }



}
