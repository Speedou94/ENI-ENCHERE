package fr.eni.encheres.groupe_2.bll;

import fr.eni.encheres.groupe_2.bo.Enchere;
import fr.eni.encheres.groupe_2.bo.Utilisateur;
import fr.eni.encheres.groupe_2.dal.DAO;
import fr.eni.encheres.groupe_2.dal.DaoFactory;

import java.util.List;

public class EnchereManager {
    private static EnchereManager instance;
    private static DAO<Enchere> enchereDAO = DaoFactory.enchereDAO();
    private static DAO<Utilisateur> utilisateurDAO = DaoFactory.utilisateurDAO();

    public static EnchereManager getInstance(){
        if(instance==null){
            return instance = new EnchereManager();
        }
        return instance;
    }

    private EnchereManager() {
    }
    private static List<Enchere> catalogueEnchere(){
        return enchereDAO.selectALL();
    }
    public void faireEnchere(Enchere enchere) throws BuissnessException {
        boolean nouvelleEnchere = nouvelleEnchere(enchere.getNo_article());
        boolean enchereValable = enchereValable(enchere.getNo_article(),enchere.getMontantEnchere());
        int creditDisponible = creditDisponible(enchere.getNo_utilisateur());
        if(creditDisponible<enchere.getMontantEnchere()){
            throw new BuissnessException(CodeErrorBll.CREDIT_INSUFFISANT);
        }
        if(nouvelleEnchere){
            enchereDAO.addNew(enchere);
        }
        else if(enchereValable) {
            enchereDAO.update(enchere);
        }
    }
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
}
