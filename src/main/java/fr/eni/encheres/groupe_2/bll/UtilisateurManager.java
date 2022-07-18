package fr.eni.encheres.groupe_2.bll;

import fr.eni.encheres.groupe_2.bo.Utilisateur;
import fr.eni.encheres.groupe_2.dal.DAO;
import fr.eni.encheres.groupe_2.dal.DaoFactory;
import fr.eni.encheres.groupe_2.dal.Login;

public class UtilisateurManager {
    private static UtilisateurManager instance;
    private Login loginDao = DaoFactory.loginDao();

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
}
