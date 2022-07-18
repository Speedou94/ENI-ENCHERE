package fr.eni.encheres.groupe_2.dal;

import fr.eni.encheres.groupe_2.bll.BuissnessException;
import fr.eni.encheres.groupe_2.bo.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UtilisateurImplJdbc implements DAO<Utilisateur>,Login {
    PreparedStatement ps;
    ResultSet rs;
    @Override
    public void addNew(Utilisateur object) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Utilisateur selectById(int id) {
        return null;
    }

    @Override
    public void update(Utilisateur object) {

    }

    @Override
    public List<Utilisateur> selectALL() {
        return null;
    }
    public Utilisateur login(String pseudo,String password) throws BuissnessException{
        String loginSql="SELECT * FROM dbo.UTILISATEURS WHERE pseudo = ?";
        Utilisateur utilisateur;

        try(Connection cnx = ConnectionProvider.getConnection()) {
            ps = cnx.prepareStatement(loginSql);
            ps.setString(1,pseudo);
            rs = ps.executeQuery();
            if (!rs.next()){
                throw new BuissnessException(ErrorCodeDAL.UTILISATEUR_INCONNU);
            }else {
                String psw = rs.getString("mot_de_passe");
                if (!psw.equals(password)){
                    throw new BuissnessException(ErrorCodeDAL.PASSWORD_INCORRECT);
                }else {
                    int id = rs.getInt("no_utilisateur");
                    String nom = rs.getString("nom");
                    String prenom = rs.getString("prenom");
                    String email = rs.getString("email");
                    String telephone = rs.getString("telephone");
                    String rue = rs.getString("rue");
                    String codePostal = rs.getString("code_postal");
                    String ville = rs.getString("ville");
                    int credit = rs.getInt("credit");
                    boolean admin = rs.getBoolean("administrateur");
                    utilisateur = new Utilisateur(id,pseudo,nom,prenom,email,telephone,rue,codePostal,ville,psw,credit,admin);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return utilisateur;
    }
}
