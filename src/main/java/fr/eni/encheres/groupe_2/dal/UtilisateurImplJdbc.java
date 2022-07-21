package fr.eni.encheres.groupe_2.dal;

import fr.eni.encheres.groupe_2.bll.BuissnessException;
import fr.eni.encheres.groupe_2.bo.Utilisateur;
import fr.eni.encheres.groupe_2.dal.security.JCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurImplJdbc implements DAO<Utilisateur>,LoginDao {
    PreparedStatement ps;
    ResultSet rs;
    @Override
    public void addNew(Utilisateur object) throws BuissnessException {
    String addNewSQL = "INSERT INTO dbo.UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    boolean pseudoAndMailDispo = verifPseudoAndMail(object.getPseudo(),object.getEmail());
        JCrypt jCrypt=new JCrypt();
    if (pseudoAndMailDispo){
        try(Connection cnx= ConnectionProvider.getConnection()){
            ps = cnx.prepareStatement(addNewSQL,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1,object.getPseudo());
            ps.setString(2,object.getNom());
            ps.setString(3,object.getPrenom());
            ps.setString(4,object.getEmail());
            ps.setString(5,object.getTelephone());
            ps.setString(6,object.getRue());
            ps.setString(7,object.getCodePostal());
            ps.setString(8,object.getVille());
            StringBuilder cryptedPassw = jCrypt.encrypt(object.getMotDePasse().replaceAll("\\s","").toUpperCase(),1);
            ps.setString(9, String.valueOf(cryptedPassw));
            ps.setInt(10,object.getCredit());
            ps.setBoolean(11,object.isAdministrateur());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            while (rs.next()){
                object.setNoUtilisateur(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
// creer cette methode
    }

    @Override
    public List<Utilisateur> selectALL() {
        return null;
    }
    public Utilisateur login(String pseudo,String password) throws BuissnessException{
        String loginSql="SELECT * FROM dbo.UTILISATEURS WHERE pseudo = ?";
        Utilisateur utilisateur;
        JCrypt crypt = new JCrypt();

        try(Connection cnx = ConnectionProvider.getConnection()) {
            ps = cnx.prepareStatement(loginSql);
            ps.setString(1,pseudo);
            rs = ps.executeQuery();
            if (!rs.next()){
                throw new BuissnessException(ErrorCodeDAL.UTILISATEUR_INCONNU);
            }else {
                String passdb = rs.getString("mot_de_passe");
                StringBuilder passdecrypt = crypt.decrypt(passdb.replaceAll("\\s","").toUpperCase(),1);
                String motdepasse = String.valueOf(passdecrypt);
                System.out.println(motdepasse);

                if (!motdepasse.equalsIgnoreCase(password)){
                    throw new BuissnessException(ErrorCodeDAL.PASSWORD_INCORRECT);
                }else {
                    int id = rs.getInt("no_utilisateur");
                    String nom = rs.getString("nom");
                    String prenom = rs.getString("prenom");
                    String email = rs.getString("email");
                    String telephone = rs.getString("telephone");
                    String rue = rs.getString("rue");
                    String ville = rs.getString("ville");
                    String codePostal = rs.getString("code_postal");
                    int credit = rs.getInt("credit");
                    boolean admin = rs.getBoolean("administrateur");
                    utilisateur = new Utilisateur(id,pseudo,nom,prenom,email,telephone,rue,codePostal,ville,password,credit,admin);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return utilisateur;
    }
    private boolean verifPseudoAndMail(String pseudo , String email) throws BuissnessException{
        String sql= "SELECT pseudo,email FROM dbo.UTILISATEURS";
        List<String> listePseudo = new ArrayList<>();
        List<String> listeEmail = new ArrayList<>();

        try(Connection cnx = ConnectionProvider.getConnection()) {
            ps = cnx.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                String pseudoDb = rs.getString("pseudo");
                String emailDb = rs.getString("email");
                listeEmail.add(emailDb);
                listePseudo.add(pseudoDb);
            }
            if (listePseudo.contains(pseudo)){

                throw new BuissnessException(ErrorCodeDAL.PSEUDO_DEJA_UTILISE);
            }
            if (listeEmail.contains(email)){

                throw new BuissnessException(ErrorCodeDAL.EMAIL_DEJA_UTILISE);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

}
