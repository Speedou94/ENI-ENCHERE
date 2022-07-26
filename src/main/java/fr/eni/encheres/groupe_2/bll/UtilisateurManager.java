package fr.eni.encheres.groupe_2.bll;

import fr.eni.encheres.groupe_2.bo.Article;
import fr.eni.encheres.groupe_2.bo.Utilisateur;
import fr.eni.encheres.groupe_2.dal.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurManager {
    private static UtilisateurManager instance;
    private LoginDao loginDao = DaoFactory.loginDao();



private static DAO<Utilisateur> utilisateurDAO = DaoFactory.utilisateurDAO();
    public static UtilisateurManager getInstance(){
        if(instance==null){
            return instance = new UtilisateurManager();
        }
        return instance;
    }

    private UtilisateurManager() {
    }
    //TODO:Faire la javadoc
    public Utilisateur login(String pseudo ,String password) throws BuissnessException {
        return loginDao.login(pseudo,password);
    }

    private static List<Utilisateur> catalogueUtilisateur(){
        return utilisateurDAO.selectALL();
    }

    public List<Utilisateur> catalogue (){

        return catalogueUtilisateur();
    }



    //TODO:Faire la javadoc
    public void newUtilisateur (Utilisateur utilisateur)throws BuissnessException
    {
        boolean verifInputOk = verifInput(utilisateur);
        boolean pseudoAndMail = verifPseudoAndMail(utilisateur.getPseudo(), utilisateur.getEmail());

        if (verifInputOk && pseudoAndMail){
            utilisateurDAO.addNew(utilisateur);
        }
    }



    //TODO:Faire la javadoc
    public void updateUtilisater(Utilisateur utilisateur ,String password) throws BuissnessException {
        System.out.println(utilisateur.getPseudo());
        boolean verifPassword = loginDao.confirmPassword(password,utilisateur.getNoUtilisateur());
        System.out.println("verif pass" +  verifPassword);
        if(!verifPassword){
            throw new BuissnessException(CodeErrorBll.PASSWORD_INCORRECT);
        }
        boolean verifInputOk = verifInput(utilisateur);
        System.out.println("verfifinpiu" + verifInputOk);

        boolean updateValide = verifPseudoUpdate(utilisateur.getPseudo(), utilisateur.getNoUtilisateur());;


        System.out.println("verfif update"+updateValide);
        if(!updateValide){
            throw new BuissnessException(CodeErrorBll.CHAMP_INVALIDE);
        }
        if(verifInputOk) {
            System.out.println("je passe dans le manager");
            utilisateurDAO.update(utilisateur);
        }
    }
    public void deleteUtilisateur(String password,int id) throws BuissnessException {
      boolean mdpConfirmer =  loginDao.confirmPassword(password,id);
      if(mdpConfirmer){
          utilisateurDAO.delete(id);
      }else {
          throw new BuissnessException(CodeErrorBll.PASSWORD_INCORRECT);
      }
    }
    private boolean verifPseudoAndMail(String pseudo, String email) throws BuissnessException {
      List<Utilisateur> utilisateurs = catalogue();
      boolean isValide = true ;

      for (Utilisateur u: utilisateurs) {

            if( u.getPseudo().equalsIgnoreCase(pseudo)){
                isValide = false;
                System.out.println(u.getPseudo());
            }
            if(u.getEmail().equalsIgnoreCase(email)){
                isValide = false;
            }
        } return isValide ;


    }
    private boolean verifPseudoUpdate (String pseudo , int id) throws BuissnessException{
        List<Utilisateur> utilisateurs = catalogue();
        boolean ok =true;
            for (Utilisateur u:utilisateurs) {
                if(u.getPseudo().equalsIgnoreCase(pseudo)){
                    ok=false;
                }
            }
            if(!ok){
                for (Utilisateur u:utilisateurs
                     ) {
                    if(u.getNoUtilisateur()==id){
                        if(u.getPseudo().equalsIgnoreCase(pseudo))
                            ok=true;
                    }
                }
            }
            return ok;
    }


    /**
     * Verif les champs des input de saisie nouvel utilisateur et renvoie une erreur le cas echeant
     * Si input valide , autorise la transaction en dal
     * @param utilisateur le nouvel utilisateur s'enregistrant dans le formulaire
     * @return un booleen autorisant la suite de la fonction addnew
     * @throws BuissnessException code erreur envoye au front pour indique a l'utilisateur quel champs est mal rempli
     */
    private boolean verifInput(Utilisateur utilisateur) throws BuissnessException {
        boolean ok = false ;
        int taillePseudo = utilisateur.getPseudo().length();
        boolean telephooneIsNumeric =  isNumeric(utilisateur.getTelephone());
        if(!telephooneIsNumeric){
            throw new BuissnessException(CodeErrorBll.TELEPHONE_INVALIDE);
        }
        boolean codePostalIsNumeric = isNumeric(utilisateur.getCodePostal());
        if(!codePostalIsNumeric){
            throw new BuissnessException(CodeErrorBll.CODE_POSTAL_INVALIDE);
        }
        boolean emailIsValide = validateEmail(utilisateur.getEmail());
        if (!emailIsValide){
            throw new BuissnessException(CodeErrorBll.EMAIL_INVALIDE);
        }
        if (taillePseudo<21){
        ok=true ;
        }
      return ok ;

    };

    /**
     * Verifie que le champs saisie dans l'input est bien de type numerique
     * @param texteATester String du champs rempli par l'utilisateur
     * @return booleen
     */
    private boolean isNumeric(String texteATester) {
        return texteATester.matches("[+-]?\\d*(\\.\\d+)?");
    };

    /**
     * Verifie que le champs saisie dans l'input email est bien de type Email
     * @param email String
     * @return booleen
     */
    private boolean validateEmail(String email){
        return email.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
    }


}
