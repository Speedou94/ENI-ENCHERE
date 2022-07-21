package fr.eni.encheres.groupe_2.dal;

import fr.eni.encheres.groupe_2.bll.BuissnessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminImplJdbc implements AdminDao{
    private final String NEW_CATEGORIE_SQL = "INSERT INTO dbo.CATEGORIES (libelle) VALUES (?)";
    private final  String SELECT_ADMIN_BY_ID_SQL = "SELECT administrateur FROM dbo.UTILISATEURS WHERE no_utilisateur=?";
    private final  String GET_ALL_LIBELLE_SQL = "SELECT libelle FROM dbo.CATEGORIES";
    @Override
    public void deleteUtilisateur(int id) {

    }

    @Override
    public void addNewCategorie(String libelle) throws BuissnessException {
        PreparedStatement ps =null;
        ResultSet rs = null;
        boolean libelleDisponible = verifCategorie(libelle);
        if (libelleDisponible){
            try(Connection cnx = ConnectionProvider.getConnection()) {
                ps= cnx.prepareStatement(NEW_CATEGORIE_SQL);
                ps.setString(1,libelle);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    }

    @Override
    public boolean isADmin(int id)  {
        PreparedStatement ps =null;
        ResultSet rs =null;


        boolean admin = false;
        try(Connection cnx = ConnectionProvider.getConnection()) {
            ps = cnx.prepareStatement(SELECT_ADMIN_BY_ID_SQL);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            while (rs.next()){
                admin = rs.getBoolean("administrateur");
            }
            return admin;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean verifCategorie(String libelle) throws BuissnessException{
        PreparedStatement ps =null;
        ResultSet rs =null;
        boolean libelleVerifie =true;
        List<String> listeDesLibelles = new ArrayList<>();
        try(Connection cnx = ConnectionProvider.getConnection()) {
            ps = cnx.prepareStatement(GET_ALL_LIBELLE_SQL);
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
