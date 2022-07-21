package fr.eni.encheres.groupe_2.bll;

import fr.eni.encheres.groupe_2.bo.Utilisateur;
import fr.eni.encheres.groupe_2.dal.DAO;
import fr.eni.encheres.groupe_2.dal.DaoFactory;
import fr.eni.encheres.groupe_2.dal.LoginDao;

public class UtilisateurManager {
    private static UtilisateurManager instance;
    private LoginDao loginDao = DaoFactory.loginDao();


    //private LoginDao loginDao = DaoFactory.loginDao();
private DAO<Utilisateur> utilisateurDAO = DaoFactory.utilisateurDAO();
    public static UtilisateurManager getInstance(){
        if(instance==null){
            return instance = new UtilisateurManager();
        }
        return instance;
    }

    public UtilisateurManager() {
    }
    public Utilisateur login(String pseudo ,String password) throws BuissnessException {
        return loginDao.login(pseudo,password);
    }
    /*public Utilisateur login(String pseudo ,String password) throws BuissnessException {
        return loginDao.login(pseudo,password);
    }*/
    public void newUtilisateur (Utilisateur utilisateur)throws BuissnessException
    {
        boolean verifInputOk = verifInput(utilisateur);
        if (verifInputOk){

            utilisateurDAO.addNew(utilisateur);

        }else {
            throw new BuissnessException(CodeErrorBll.CHAMP_INVALIDE);
        }

    }
    private boolean verifInput(Utilisateur utilisateur) {
        boolean ok = false ;


        if (utilisateur.getPseudo().length()>0 && utilisateur.getPseudo().length()<21){
        ok = true ;
        }
        ok = isNumeric(utilisateur.getTelephone());
        ok = isNumeric(utilisateur.getCodePostal());
        ok = utilisateur.getEmail().matches("/^(([^<>()[]\\.,;:\\s@\"]+(.[^<>()[]\\.,;:\\s@\"]+)*)|(\".+\"))@(([[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}])|(([a-zA-Z-0-9]+.)+[a-zA-Z]{2,}))$/\n");

      return ok ;



    }
    private boolean isNumeric(String texteATester) {
        boolean Isnumeric = texteATester.matches("[+-]?\\d*(\\.\\d+)?");

        return Isnumeric;
    };
}
// input est bin numeric
// input mail
//et une fois la fonction test longueur text appliquer sur tout les champs en db