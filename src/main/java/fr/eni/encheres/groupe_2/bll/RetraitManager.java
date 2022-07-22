package fr.eni.encheres.groupe_2.bll;

import fr.eni.encheres.groupe_2.bo.Retrait;
import fr.eni.encheres.groupe_2.dal.DAO;
import fr.eni.encheres.groupe_2.dal.DaoFactory;

public class RetraitManager {

    private static RetraitManager instance;
    private static DAO<Retrait> retraitDAO = DaoFactory.retraitDAO();
    public static RetraitManager getInstance(){
        if(instance==null){
            return instance = new RetraitManager();
        }
        return instance;
    }

    private RetraitManager() {
    }
    public void addNew(Retrait retrait) throws BuissnessException {
        retraitDAO.addNew(retrait);
    }
}
