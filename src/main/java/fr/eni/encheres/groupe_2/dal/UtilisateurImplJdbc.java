package fr.eni.encheres.groupe_2.dal;

import fr.eni.encheres.groupe_2.bll.BuissnessException;
import fr.eni.encheres.groupe_2.bo.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UtilisateurImplJdbc implements DAO<Utilisateur>,LoginDao {
    PreparedStatement ps;
    ResultSet rs;
    @Override
    public void addNew(Utilisateur object) {
    String addNewSQL = "INSERT INTO dbo.UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

    try(Connection cnx= ConnectionProvider.getConnection()){
        ps = cnx.prepareStatement(addNewSQL);
        ps.setString(1,object.getPseudo());
        ps.setString(2,object.getNom());
        ps.setString(3,object.getPrenom());
        ps.setString(4,object.getEmail());
        ps.setString(5,object.getTelephone());
        ps.setString(6,object.getRue());
        ps.setString(7,object.getCodePostal());
        ps.setString(8,object.getMotDePasse());
        ps.setInt(9,object.getCredit());
        ps.setBoolean(10,object.isAdministrateur());
        ps.executeUpdate();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
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
        String loginSql="SELECT * FROM dbo.USERS WHERE pseudo = ?";
        Utilisateur utilisateur;

        try(Connection cnx = ConnectionProvider.getConnection()) {
            ps = cnx.prepareStatement(loginSql,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1,pseudo);
            rs = ps.executeQuery();
            if (!rs.next()){
                throw new BuissnessException(10000);
            }else {
                String psw = rs.getString("password");
                if (!psw.equals(password)){
                    throw new BuissnessException(10001);
                }else {
                    int id = rs.getInt("id");
                    String nom = rs.getString("nom");
                    String prenom = rs.getString("prenom");
                    String email = rs.getString("email");
                    String telephone = rs.getString("telephone");
                    String rue = rs.getString("rue");
                    String ville = rs.getString("ville");
                    String codePostal = rs.getString("codePostal");
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
