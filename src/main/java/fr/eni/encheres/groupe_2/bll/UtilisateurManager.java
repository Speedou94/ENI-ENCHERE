package fr.eni.encheres.groupe_2.bll;

import fr.eni.encheres.groupe_2.bo.Utilisateur;
import fr.eni.encheres.groupe_2.dal.DAO;
import fr.eni.encheres.groupe_2.dal.DaoFactory;
import fr.eni.encheres.groupe_2.dal.LoginDao;

public class UtilisateurManager {
    private static UtilisateurManager instance;

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
    /*public Utilisateur login(String pseudo ,String password) throws BuissnessException {
        return loginDao.login(pseudo,password);
    }*/
    public void newUtilisateur (Utilisateur utilisateur)
    {
        utilisateurDAO.addNew(utilisateur);

    }
}
