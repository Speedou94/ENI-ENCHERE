package fr.eni.encheres.groupe_2.dal;

import fr.eni.encheres.groupe_2.bll.CategorieManager;
import fr.eni.encheres.groupe_2.bo.Categorie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategorieImplJdbc implements DAO<Categorie> {
    PreparedStatement ps;
    ResultSet rs;


    @Override
    public void addNew(Categorie object) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Categorie selectById(int id) {
        return null;
    }

    @Override
    public void update(Categorie object) {

    }

    @Override
    public List<Categorie> selectALL() {
        String selectAllSql = "SELECT * FROM dbo.CATEGORIES";
        List<Categorie> listCategorie = new ArrayList<>();
        try (Connection cnx = ConnectionProvider.getConnection()){
            ps = cnx.prepareStatement(selectAllSql);
            rs = ps.executeQuery();
            while (rs.next()){
                int noCategorie = rs.getInt("no_categorie");
                String libelle = rs.getString("libelle");
                Categorie categorie = new Categorie(noCategorie, libelle);
                listCategorie.add(categorie);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listCategorie;

    }}
