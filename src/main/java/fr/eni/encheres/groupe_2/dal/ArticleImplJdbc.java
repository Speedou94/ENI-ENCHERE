package fr.eni.encheres.groupe_2.dal;

import fr.eni.encheres.groupe_2.bo.Article;
import fr.eni.encheres.groupe_2.bo.Categorie;
import fr.eni.encheres.groupe_2.bo.Utilisateur;
import javassist.bytecode.analysis.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArticleImplJdbc<ARTICLES_VENDUS> implements DAO<Article> {
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
        String selectSQL = "SELECT * FROM dbo.ARTICLES_VENDUS WHERE no_article=?";
        Article article = null;
        try (Connection cnx = ConnectionProvider.getConnection()) {
            ps = cnx.prepareStatement(selectSQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int noArticle = rs.getInt("no_article");
                String nomArticle = rs.getString("nom_article");
                String description = rs.getString("description");
                Date dateDebut = rs.getDate("date_debut_encheres");
                Date dateFin = rs.getDate("date_fin_encheres");
                int prixIn = rs.getInt("prix_initial");
                int prixVente = rs.getInt("prix_vente");
                int idUtilisateur = rs.getInt("no_utilisateur");
                int idCategorie = rs.getInt("no_categorie");
                article = new Article(noArticle, nomArticle, description, dateDebut, dateFin, prixIn, prixVente, idUtilisateur, idCategorie);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return article;
    }


    @Override
    public void update(Article object) {

    }

    @Override
    public List<Article> selectALL() {
        String selectAllSql = "SELECT * FROM dbo.ARTICLES_VENDUS JOIN UTILISATEURS U on U.no_utilisateur = ARTICLES_VENDUS.no_utilisateur JOIN CATEGORIES C on C.no_categorie = ARTICLES_VENDUS.no_categorie";
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
                String pseudo = rs.getString("pseudo");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String email = rs.getString("email");
                String telephone = rs.getString("telephone");
                String rue = rs.getString("rue");
                String code_postal = rs.getString("code_postal");
                String ville = rs.getString("ville");
                String libelle = rs.getString("libelle");

                Categorie categorie = new Categorie(idCategorie,libelle);
                Utilisateur vendeur = new Utilisateur(idUtilisateur, pseudo, nom, prenom, email,telephone,rue,code_postal,ville);

                Article article = new Article(noArticle,nomArticle,description,dateDebut,dateFin,prixIn,prixVente,vendeur, categorie);
                listArticle.add(article);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listArticle;
    }
}


