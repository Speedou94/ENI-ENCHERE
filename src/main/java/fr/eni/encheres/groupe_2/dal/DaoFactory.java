package fr.eni.encheres.groupe_2.dal;


import fr.eni.encheres.groupe_2.bo.Utilisateur;

public class DaoFactory {
public static DAO<Utilisateur> utilisateurDAO(){
    return new UtilisateurImplJdbc();
}
}
