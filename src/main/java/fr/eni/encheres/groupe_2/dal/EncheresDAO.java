package fr.eni.encheres.groupe_2.dal;

public interface EncheresDAO {
    /**
     * methode qui met a jour les credits Utilisateur en fonction des encheres faites
     * @param id noUtilisateur
     * @param nouveauCredit Credit restant
     */
    void updateCredit (int id, int nouveauCredit);

    void updatePrixVente(int no_article, int montantEnchere);


}
