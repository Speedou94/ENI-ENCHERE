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
    //TODO:Faire la javadoc , essaye de remonte une erreur diffente par champs mal rempli (Code erreur a complete dans la class CodeErrorBLL
    private boolean verifInput(Utilisateur utilisateur) {
        boolean ok = false ;
        int taillePseudo = utilisateur.getPseudo().length();
        boolean telephooneIsNumeric =  isNumeric(utilisateur.getTelephone());
        System.out.println("tele"+ telephooneIsNumeric);
        boolean codePostalIsNumeric = isNumeric(utilisateur.getCodePostal());
        System.out.println("codep"+codePostalIsNumeric);
        boolean emailIsValide = validateEmail(utilisateur.getEmail());
        System.out.println("email" +emailIsValide);
        if (taillePseudo<21 && telephooneIsNumeric && codePostalIsNumeric && emailIsValide){
        return true ;
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
