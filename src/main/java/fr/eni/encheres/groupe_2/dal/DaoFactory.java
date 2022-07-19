package fr.eni.encheres.groupe_2.dal;


import fr.eni.encheres.groupe_2.bo.Article;
import fr.eni.encheres.groupe_2.bo.Categorie;
import fr.eni.encheres.groupe_2.bo.Utilisateur;

public class DaoFactory {

    public static DAO<Categorie> categorieDAO(){
        return new CategorieImplJdbc();
    }

    public static DAO<Article> articledao() {
        return new ArticleImplJdbc();
    }
 public static DAO<Utilisateur> utilisateurDAO(){return new UtilisateurImplJdbc();}
 public static LoginDao loginDao(){return  new UtilisateurImplJdbc();}
}
