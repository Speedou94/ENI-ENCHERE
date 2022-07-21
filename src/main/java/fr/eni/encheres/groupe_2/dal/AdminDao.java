package fr.eni.encheres.groupe_2.dal;

import fr.eni.encheres.groupe_2.bll.BuissnessException;

public interface AdminDao {
    void deleteUtilisateur(int id);
    void addNewCategorie(String libelle) throws BuissnessException;

    boolean isADmin(int id) throws BuissnessException;
  //  void holdAccountUtilisateur(int id);
}
