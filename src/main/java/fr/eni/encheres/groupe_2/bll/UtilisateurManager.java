package fr.eni.encheres.groupe_2.bll;

import fr.eni.encheres.groupe_2.bo.Utilisateur;
import fr.eni.encheres.groupe_2.dal.DAO;
import fr.eni.encheres.groupe_2.dal.DaoFactory;
import fr.eni.encheres.groupe_2.dal.LoginDao;

public class UtilisateurManager {
    private static UtilisateurManager instance;
    private LoginDao loginDao = DaoFactory.loginDao();



private DAO<Utilisateur> utilisateurDAO = DaoFactory.utilisateurDAO();
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
    //TODO:Faire la javadoc
    public void newUtilisateur (Utilisateur utilisateur)throws BuissnessException
    {
        boolean verifInputOk = verifInput(utilisateur);
        if (verifInputOk){

            utilisateurDAO.addNew(utilisateur);

        }else {
            throw new BuissnessException(CodeErrorBll.CHAMP_INVALIDE);
        }

    }
    //TODO:Faire la javadoc
    public void updateUtilisater(Utilisateur utilisateur) throws BuissnessException {
        boolean verifInputOk = verifInput(utilisateur);
        if(verifInputOk){
            utilisateurDAO.update(utilisateur);
        }
    }

    /**
     * Verif les champs des input de saisie nouvel utilisateur et renvoie une erreur le cas echeant
     * Si input valide , autorise la transaction en dal
     * @param utilisateur le nouvel utilisateur s'enregistrant dans le formulaire
     * @return un booleen autorisant la suite de la fonction addnew
     * @throws BuissnessException code erreur envoye au front pour indique a l'utilisateur quel champs est ma rempli
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
