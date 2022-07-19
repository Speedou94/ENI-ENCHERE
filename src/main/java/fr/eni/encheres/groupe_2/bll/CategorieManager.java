package fr.eni.encheres.groupe_2.bll;

import fr.eni.encheres.groupe_2.bo.Categorie;
import fr.eni.encheres.groupe_2.dal.DAO;
import fr.eni.encheres.groupe_2.dal.DaoFactory;

import java.util.List;

public class CategorieManager {
    private static CategorieManager instance;
    private DAO<Categorie> categorieDAO = DaoFactory.categorieDAO();
    public static CategorieManager getInstance(){
        if (instance == null){
            return instance = new CategorieManager();
        }
        return instance;
    }

    public CategorieManager() {
    }
    public List<Categorie> getAllCategorie(){
        return categorieDAO.selectALL();

    }
}
