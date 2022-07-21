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
        utilisateurDAO.addNew(utilisateur);

    }
}
    private boolean isNumeric(String texteATester) {
        boolean Isnumeric = texteATester.matches("[+-]?\\d*(\\.\\d+)?");
        ;
        return Isnumeric;
    };
//reg mail:
const re =
        /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;

//tester la longueur text
// input est bien numeric
// input mail
//et une fois la fonction test longueur text appliquer sur tout les champs en db