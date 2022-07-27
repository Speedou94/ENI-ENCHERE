package fr.eni.encheres.groupe_2.dal;


import fr.eni.encheres.groupe_2.bo.*;

public class DaoFactory {

    public static DAO<Categorie> categorieDAO(){
        return new CategorieImplJdbc();
    }

    public static DAO<Article> articleDao() {
        return new ArticleImplJdbc();
    }
    public static DAO<Utilisateur> utilisateurDAO(){return new UtilisateurImplJdbc();}
    public static LoginDao loginDao(){return  new UtilisateurImplJdbc();}
    public static AdminDao adminDao(){return new AdminImplJdbc();}
    public static DAO<Retrait> retraitDAO(){return new RetraitImplJdbc();}
    public static DAO<Enchere> enchereDAO(){return new EnchereImplJdbc();}

    public  static  EncheresDAO encheresFeatureUtilisateur (){

        return new UtilisateurImplJdbc();}
    }
