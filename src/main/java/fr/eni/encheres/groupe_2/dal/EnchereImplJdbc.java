package fr.eni.encheres.groupe_2.dal;

import fr.eni.encheres.groupe_2.bo.Enchere;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnchereImplJdbc implements DAO<Enchere> {
    private final String ADD_NEW_ENCHERE_SQL="INSERT INTO dbo.ENCHERES (date_enchere,montant_enchere,no_article,no_utilisateur) VALUES (?,?,?,?)";
   private final String SELECT_ALL_ENCHERE_SQL="SELECT * FROM dbo.ENCHERES";
   private final String UPDATE_ENCHERE_SQL="UPDATE dbo.ENCHERES SET date_enchere=?,montant_enchere=?,no_utilisateur=?";
    @Override
    public void addNew(Enchere object) {
        PreparedStatement ps = null;
        try(Connection cnx = ConnectionProvider.getConnection()) {
            ps= cnx.prepareStatement(ADD_NEW_ENCHERE_SQL);
            ps.setTimestamp(1,object.getDateEnchere());
            ps.setInt(2,object.getMontantEnchere());
            ps.setInt(3,object.getNo_article());
            ps.setInt(4,object.getNo_utilisateur());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Enchere selectById(int id) {
        return null;
    }

    @Override
    public void update(Enchere object) {
PreparedStatement ps;
try(Connection cnx = ConnectionProvider.getConnection()) {
    ps = cnx.prepareStatement(UPDATE_ENCHERE_SQL);
    ps.setTimestamp(1,object.getDateEnchere());
    ps.setInt(2,object.getMontantEnchere());
    ps.setInt(3,object.getNo_utilisateur());
    ps.executeUpdate();

} catch (SQLException e) {
    throw new RuntimeException(e);
}
    }

    @Override
    public List<Enchere> selectALL() {
        PreparedStatement ps =null;
        ResultSet rs = null;
        List<Enchere> listeDesEncheres = new ArrayList<>();
        try(Connection cnx = ConnectionProvider.getConnection()) {
            ps= cnx.prepareStatement(SELECT_ALL_ENCHERE_SQL);
            rs = ps.executeQuery();
            while (rs.next()){
                int no_enchere = rs.getInt("no_enchere");
                Timestamp dateEnchere = rs.getTimestamp("date_enchere");
                int montant = rs.getInt("montant_enchere");
                int no_article =rs.getInt("no_article");
                int no_utilisateur = rs.getInt("no_utilisateur");
                Enchere enchere = new Enchere(no_enchere,dateEnchere,montant,no_article,no_utilisateur);
                listeDesEncheres.add(enchere);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listeDesEncheres;


    }
}
