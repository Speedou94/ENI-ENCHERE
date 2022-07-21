package fr.eni.encheres.groupe_2.dal;

abstract class ErrorCodeDAL {
    public static final int UTILISATEUR_INCONNU=10000;
    public static final int PASSWORD_INCORRECT=10001;
    public static final int PSEUDO_DEJA_UTILISE=10002;
    public static final int EMAIL_DEJA_UTILISE=10003;

    public static final int CATEGORIE_DEJA_EXISTANTE=10004;
    public static final int UTILISATEUR_NON_ADMIN=10005;
}
