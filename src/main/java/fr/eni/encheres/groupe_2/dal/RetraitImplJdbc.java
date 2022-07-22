package fr.eni.encheres.groupe_2.dal;

import fr.eni.encheres.groupe_2.bll.BuissnessException;
import fr.eni.encheres.groupe_2.bo.Retrait;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class RetraitImplJdbc implements DAO<Retrait> {
    private final String ADD_NEW_SQL="INSERT INTO dbo.RETRAITS (no_article,rue,code_postal,ville) VALUES (?,?,?,?)";
    @Override
    public void addNew(Retrait object) throws BuissnessException {
        PreparedStatement ps = null;
        try(Connection cnx = ConnectionProvider.getConnection()) {
            ps = cnx.prepareStatement(ADD_NEW_SQL);
            ps.setInt(1,object.getNo_article());
            ps.setString(2,object.getRue());
            ps.setString(3,object.getCodePostal());
            ps.setString(4,object.getVille());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Retrait selectById(int id) {
        return null;
    }

    @Override
    public void update(Retrait object) {

    }

    @Override
    public List<Retrait> selectALL() {
        return null;
    }
}
