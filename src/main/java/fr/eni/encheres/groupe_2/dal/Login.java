package fr.eni.encheres.groupe_2.dal;

import fr.eni.encheres.groupe_2.bll.BuissnessException;
import fr.eni.encheres.groupe_2.bo.Utilisateur;

public interface Login {
    Utilisateur login(String pseudo , String password) throws BuissnessException;
}
