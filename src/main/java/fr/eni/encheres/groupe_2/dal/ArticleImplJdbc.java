package fr.eni.encheres.groupe_2.dal;

import fr.eni.encheres.groupe_2.bo.Article;
import fr.eni.encheres.groupe_2.bo.Categorie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArticleImplJdbc implements DAO<Article> {
    PreparedStatement ps;
    ResultSet rs;
    @Override
    public void addNew(Article object) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Article selectById(int id) {
        return null;
    }

    @Override
    public void update(Article object) {

    }

    @Override
    public List<Article> selectALL() {
        String selectAllSql = "SELECT * FROM dbo.ARTICLES_VENDUS";
        List<Article> listArticle = new ArrayList<>();
        try (Connection cnx = ConnectionProvider.getConnection()){
            ps = cnx.prepareStatement(selectAllSql);
            rs = ps.executeQuery();
            while (rs.next()){
                int noArticle = rs.getInt("no_article");
                String nomArticle = rs.getString("nom_article");
                String description = rs.getString("description");
                Date dateDebut = rs.getDate("date_debut_encheres");
                Date dateFin = rs.getDate("date_fin_encheres");
                int prixIn = rs.getInt("prix_initial");
                int prixVente = rs.getInt("prix_vente");
                int idUtilisateur = rs.getInt("no_utilisateur");
                int idCategorie = rs.getInt("no_categorie");


                Article article = new Article(noArticle,nomArticle,description,dateDebut,dateFin,prixIn,prixVente,idUtilisateur,idCategorie);
                listArticle.add(article);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listArticle;
    }
}


