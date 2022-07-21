package fr.eni.encheres.groupe_2.dal;

import fr.eni.encheres.groupe_2.bll.BuissnessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminImplJdbc implements AdminDao{
    PreparedStatement ps;
    ResultSet rs;
    @Override
    public void deleteUtilisateur(int id) {

    }

    @Override
    public void addNewCategorie(String libelle) throws BuissnessException {
        String newCategorieSql = "INSERT INTO dbo.CATEGORIES (libelle) VALUES (?)";
        boolean libelleDisponible = verifCategorie(libelle);
        if (libelleDisponible){
            try(Connection cnx = ConnectionProvider.getConnection()) {
                ps= cnx.prepareStatement(newCategorieSql);
                ps.setString(1,libelle);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    }
    private boolean verifCategorie(String libelle) throws BuissnessException{
        boolean libelleVerifie =true;
        List<String> listeDesLibelles = new ArrayList<>();
        String getAllLibelle = "SELECT libelle FROM dbo.CATEGORIES";
        try(Connection cnx = ConnectionProvider.getConnection()) {
            ps = cnx.prepareStatement(getAllLibelle);
            rs = ps.executeQuery();
            while (rs.next()){
                String libelleDB = rs.getString("libelle");
                listeDesLibelles.add(libelleDB);
            }
            if (listeDesLibelles.contains(libelle)){
                throw new BuissnessException(ErrorCodeDAL.CATEGORIE_DEJA_EXISTANTE);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return libelleVerifie;
    }
}
