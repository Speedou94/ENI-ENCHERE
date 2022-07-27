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

    /**
     * Instance du manager
     * @return un instance
     */
    public static UtilisateurManager getInstance(){
        if(instance==null){
            return instance = new UtilisateurManager();
        }
        return instance;
    }

    private UtilisateurManager() {
    }

    /**
     * Fonction de login
     * @param pseudo String du pseudo
     * @param password String du mdp
     * @return Les info de l'utlisateur et le log automatiquement
     * @throws BuissnessException Renvoie les erreur en cas de mdp ou pseudo incorrect
     */
    public Utilisateur login(String pseudo ,String password) throws BuissnessException {
        return loginDao.login(pseudo,password);
    }

    /**
     * Cherge la bdd utlisateur dans l'instance
     * @return le catalogue complet des utlisateur
     */
    private static List<Utilisateur> catalogueUtilisateur(){
        return utilisateurDAO.selectALL();
    }

    /**
     * Permet d'acceder au catalogue depuis une instannce de manager
     * @return le catalogue complet des utlisateur
     */
    private List<Utilisateur> catalogue (){
        return catalogueUtilisateur();
    }


    /**
     * Insertion d'un nouvel utlisateur apres verif du pseudo et mail non utlisier en BDD
     * @param utilisateur Les info complete du nouvel utlisateur
     * @throws BuissnessException renvoie les differentes erreur si les champs de saisie ne sont pas valide
     */
    public void newUtilisateur (Utilisateur utilisateur)throws BuissnessException
    {
        boolean verifInputOk = verifInput(utilisateur);
        boolean pseudoAndMail = verifPseudoAndMail(utilisateur.getPseudo(), utilisateur.getEmail());
        if (verifInputOk && pseudoAndMail){
            utilisateurDAO.addNew(utilisateur);
        }
    }

    /**
     * Methode de mise a jour du profil utilisateur
     * @param utilisateur les info de l'utlisateur saisie dans le formulaire
     * @param password son mdp passe saise dans la modal de confirmation pour valider le update
     * @throws BuissnessException renvoie les differente erreur de saisie possible
     */
    public void updateUtilisater(Utilisateur utilisateur ,String password) throws BuissnessException {


        boolean verifPassword = loginDao.confirmPassword(password,utilisateur.getNoUtilisateur());
        if(!verifPassword){
            throw new BuissnessException(CodeErrorBll.PASSWORD_INCORRECT);
        }
        boolean verifInputOk = verifInput(utilisateur);
        boolean updateValide = verifPseudoUpdate(utilisateur.getPseudo(), utilisateur.getNoUtilisateur());;
        if(!updateValide){
            throw new BuissnessException(CodeErrorBll.CHAMP_INVALIDE);
        }
        if(verifInputOk) {

            utilisateurDAO.update(utilisateur);
        }
    }

    /**
     * Mise a jour du mot de passe avec verification de l'ancien mdp en bdd
     * Verfiei aussi si le nouveau est bien valable dans la regex
     * @param oldPassword L'ancien mot de passe
     * @param newPassword le nouveau mot de passe
     * @param idUtilisateur l'id de l utilisateur pour le update en bdd
     * @throws BuissnessException renvoie les differente erreur de saisie possible
     */
    public void updatePassword(String oldPassword ,String newPassword ,int idUtilisateur) throws BuissnessException {
        boolean oldPasswordInput = validatePassword(oldPassword);
        boolean newPasswordInput = validatePassword(newPassword);
        if(oldPasswordInput && newPasswordInput){
          boolean verifPassword= loginDao.confirmPassword(oldPassword,idUtilisateur);
          if(verifPassword){
              loginDao.changePassword(newPassword,idUtilisateur);
          }
        }else {
            throw new BuissnessException(CodeErrorBll.PASSWORD_INCORRECT);
        }
    }

    /**
     * Methode de supression du profil de l'utlisateur apres verification par son mdp
     * @param password son mot de passe saisie dans la modal de confirmation
     * @param id le no de l'utilisateur
     * @throws BuissnessException renvoie les differente erreur de saisie possible
     */
    public void deleteUtilisateur(String password,int id) throws BuissnessException {
       boolean passwordIsValide = validatePassword(password);
       boolean mdpConfirmer =  loginDao.confirmPassword(password,id);
       if(mdpConfirmer && passwordIsValide){
           utilisateurDAO.delete(id);
       } else {
          throw new BuissnessException(CodeErrorBll.PASSWORD_INCORRECT);
       }
    }

    /**
     * Verifie que le pseudo et le mail sont bien absent de la bdd pour validation
     * @param pseudo le pseudo donner dans le formulaire
     * @param email l'email donner dans le formulaire
     * @throws BuissnessException renvoie les differente erreur de saisie possible
     */
    private boolean verifPseudoAndMail(String pseudo, String email) throws BuissnessException {
      List<Utilisateur> utilisateurs = catalogue();
      boolean isValide = true ;
      for (Utilisateur u: utilisateurs) {
            if(u.getPseudo().equalsIgnoreCase(pseudo)){
               throw new BuissnessException(CodeErrorBll.PSEUDO_DEJA_UTILISE);
            }
            if(u.getEmail().equalsIgnoreCase(email)){
                throw new BuissnessException(CodeErrorBll.EMAIL_DEJA_UTILISE);
            }
        }
      return isValide ;
    }

    /**
     * Verifie que le pseudo  envoye dans la mise a jour du profil etait bien ceux de l'utilisateur connecter
     * verfie que le nouveau pseudo ne soit pas deja utiliser
     * @param pseudo Le nouveau pseudo
     * @param id le numero de l'utilsateur
     * @return un booleen autorisant ou non la suite de la requete
     */
    private boolean verifPseudoUpdate (String pseudo , int id) {
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
        if(taillePseudo==0){
            throw new BuissnessException(CodeErrorBll.PSEUDO_INCORRECT);
        }
        boolean telephooneIsNumeric =  isNumeric(utilisateur.getTelephone());
        if(!telephooneIsNumeric){
            throw new BuissnessException(CodeErrorBll.TELEPHONE_INVALIDE);
        }
        boolean codePostalIsNumeric = isNumeric(utilisateur.getCodePostal());
        if(!codePostalIsNumeric || utilisateur.getCodePostal()==null){
            throw new BuissnessException(CodeErrorBll.CODE_POSTAL_INVALIDE);
        }
        boolean emailIsValide = validateEmail(utilisateur.getEmail());
        if (!emailIsValide || utilisateur.getEmail()==null){
            throw new BuissnessException(CodeErrorBll.EMAIL_INVALIDE);
        }
        boolean passwordIsValide = validatePassword(utilisateur.getMotDePasse());
        if(!passwordIsValide){
            throw new BuissnessException(CodeErrorBll.PASSWORD_NON_ALPHABETIC);
        }
        if (taillePseudo<21){
        ok=true ;
        }
        return ok ;
    }

    /**
     * Verifie que la chaine de carater passer est bien un mot alphabetique
     * @param motDePasse String du mot de passe saisie
     * @return booleen
     */
    private boolean validatePassword(String motDePasse) {

        return  motDePasse.matches("^[A-Za-z]+$");
    }

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
