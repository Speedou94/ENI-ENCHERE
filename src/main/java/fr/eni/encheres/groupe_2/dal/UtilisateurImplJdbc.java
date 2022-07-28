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
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class UtilisateurImplJdbc implements DAO<Utilisateur>, LoginDao {

    private final String SELECT_ALL_USERS_SQL = "SELECT * FROM dbo.UTILISATEURS";
    private final String ADD_NEW_SQL = "INSERT INTO dbo.UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    private final String LOGIN_SQL = "SELECT * FROM dbo.UTILISATEURS WHERE pseudo = ?;";
    private final String VERIF_PSEUDO_ET_MAIL_SQL = "SELECT pseudo,email FROM dbo.UTILISATEURS";

    private final String UPDATE_UTILISATEUR = "UPDATE dbo.UTILISATEURS SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=? WHERE no_utilisateur =?";
    private final String DELETE_RETRAIT_SQL = "DELETE FROM dbo.RETRAITS where no_article in (select no_article from ARTICLES_VENDUS where no_utilisateur=?)";
    private final String DELETE_ARTICLES="DELETE FROM dbo.ARTICLES_VENDUS where no_utilisateur = ?;";
    private final String DELETE_ENCHERE_SQL = "DELETE FROM dbo.ENCHERES where no_utilisateur = ?;";

    private final String DELETE_UTILISATEUR ="DELETE FROM dbo.UTILISATEURS where no_utilisateur=?;";
    private final String CONFIRM_PASSWORD_SQL = "SELECT mot_de_passe FROM dbo.UTILISATEURS WHERE no_utilisateur=?";

    private final String UPDATE_PASSWORD_SQL = "UPDATE dbo.UTILISATEURS SET mot_de_passe = ? WHERE no_utilisateur=?";

    /**
     * Insertion d'un nouvel utilisateur en bdd
     * @param object Utilisateur
     * @throws BuissnessException remonte les erreur en cas de mauvaise saisie
     */

    private final String UPDATE_PRIX_VENTE_SQL="UPDATE dbo.ARTICLES_VENDUS SET prix_vente=? WHERE no_article=?";
    //PreparedStatement ps;
    // ResultSet rs;
    //TODO:Faire la javadoc
    @Override
    public void addNew(Utilisateur object) throws BuissnessException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean pseudoAndMailDispo = verifPseudoAndMail(object.getPseudo(), object.getEmail());
        JCrypt jCrypt = new JCrypt();
        if (pseudoAndMailDispo) {
            try (Connection cnx = ConnectionProvider.getConnection()) {
                ps = cnx.prepareStatement(ADD_NEW_SQL, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1, object.getPseudo());
                ps.setString(2, object.getNom());
                ps.setString(3, object.getPrenom());
                ps.setString(4, object.getEmail());
                ps.setString(5, object.getTelephone());
                ps.setString(6, object.getRue());
                ps.setString(7, object.getCodePostal());
                ps.setString(8, object.getVille());
                StringBuilder cryptedPassw = jCrypt.encrypt(object.getMotDePasse().replaceAll("\\s", "").toUpperCase(), 1);
                ps.setString(9, String.valueOf(cryptedPassw));
                ps.setInt(10, object.getCredit());
                ps.setBoolean(11, object.isAdministrateur());
                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
                while (rs.next()) {
                    object.setNoUtilisateur(rs.getInt(1));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    /**
     * Supprime l'utlisateur de la BDD ainsi que toute les enchere qu'il a fait , ses article a vendre et les lieu de retraits
     * @param id no_de l'utlisateur
     */
    @Override
    public void delete(int id) {
        PreparedStatement ps=null;
        try(Connection cnx =ConnectionProvider.getConnection()) {
            ps= cnx.prepareStatement(DELETE_RETRAIT_SQL);
            ps.setInt(1,id);
            ps.executeUpdate();
            ps.close();
            ps= cnx.prepareStatement(DELETE_ENCHERE_SQL);
            ps.setInt(1,id);
            ps.executeUpdate();
            ps.close();
            ps= cnx.prepareStatement(DELETE_ARTICLES);
            ps.setInt(1,id);
            ps.executeUpdate();
            ps.close();
            ps= cnx.prepareStatement(DELETE_UTILISATEUR);
            ps.setInt(1,id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Utilisateur selectById(int id) {
        return null;
    }


    //TODO:ameliorer la methode pour verifier pseudo et mail ! attention le pseudo doit etre comparer et accepter que le meme pseudo
    @Override
    public void update(Utilisateur object) throws BuissnessException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        System.out.println("update SQL");


            try (Connection cnx = ConnectionProvider.getConnection()) {
                ps = cnx.prepareStatement(UPDATE_UTILISATEUR);
                System.out.println(object.getNoUtilisateur());
                ps.setString(1, object.getPseudo());
                ps.setString(2, object.getNom());
                ps.setString(3, object.getPrenom());
                ps.setString(4, object.getEmail());
                ps.setString(5, object.getTelephone());
                ps.setString(6, object.getRue());
                ps.setString(7, object.getCodePostal());
                ps.setString(8, object.getVille());
                ps.setInt(9, object.getNoUtilisateur());
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

    }

    /**
     * Selection de tous les utlisateurs en BDD , remonte toute les info , le mdp est encrypter
     * @return la liste de tous les utilisateurs
     */
    @Override
    public List<Utilisateur> selectALL() {
        PreparedStatement ps = null;
        ResultSet rs= null;
        List<Utilisateur> listUtilisateur = new ArrayList<>();

        try (Connection cnx = ConnectionProvider.getConnection()){
            ps = cnx.prepareStatement((SELECT_ALL_USERS_SQL));
            rs = ps.executeQuery();
            while (rs.next()){
                int noUtilisateur = rs.getInt("no_utilisateur");
                String pseudo = rs.getString("pseudo");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String email = rs.getString("email");
                String telephone = rs.getString("telephone");
                String rue = rs.getString("rue");
                String code_postal = rs.getString("code_postal");
                String ville = rs.getString("ville");
                String motDePasse = rs.getString("mot_de_passe");
                int credit = rs.getInt("credit");
                boolean admin = rs.getBoolean("administrateur");

                Utilisateur utilisateurCopie = new Utilisateur(noUtilisateur,pseudo,nom,prenom,email,telephone,rue,code_postal,ville,motDePasse,credit,admin);
                listUtilisateur.add(utilisateurCopie);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listUtilisateur;
    }


    /**
     * Fonction permetant de se logue a l'appli , verifie le pseudo et le mot de passe crypter
     * @param pseudo String du pseudo
     * @param password String du password
     * @return Les info de l'utlisateur
     * @throws BuissnessException remonte les erreur mot de passe ou pseudo incorect
     */
    public Utilisateur login(String pseudo, String password) throws BuissnessException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Utilisateur utilisateur;
        JCrypt crypt = new JCrypt();
        try (Connection cnx = ConnectionProvider.getConnection()) {
            ps = cnx.prepareStatement(LOGIN_SQL);
            ps.setString(1, pseudo);
            rs = ps.executeQuery();
            if (!rs.next()) {
                throw new BuissnessException(ErrorCodeDAL.UTILISATEUR_INCONNU);
            } else {
                String passdb = rs.getString("mot_de_passe");
                StringBuilder passdecrypt = crypt.decrypt(passdb.replaceAll("\\s", "").toUpperCase(), 1);
                String motdepasse = String.valueOf(passdecrypt);
                if (!motdepasse.equalsIgnoreCase(password)) {
                    throw new BuissnessException(ErrorCodeDAL.PASSWORD_INCORRECT);
                } else {
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
                    utilisateur = new Utilisateur(id, pseudo, nom, prenom, email, telephone, rue, codePostal, ville, password, credit, admin);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return utilisateur;
    }

    /**
     * Methode ulisiser pour confirmer le mot de passe si valide ou non
     * utiliser par le utilisateur manager pour valider les changement ou la suppression du compte
     * @param password Le mot de passe de l'utlisateur qui sera decrypter dans l'appli
     * @param id l'id de l'utilisateur
     * @return un booleen validant ou non si le mot de passe est bon
     */
    @Override
    public boolean confirmPassword(String password , int id) {
        PreparedStatement ps = null;
        ResultSet rs =null;
        JCrypt jCrypt = new JCrypt();
        boolean mdpConfirmer = false;
        try(Connection cnx = ConnectionProvider.getConnection()) {
            ps= cnx.prepareStatement(CONFIRM_PASSWORD_SQL);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            while (rs.next()){
               String mdpInDb = rs.getString("mot_de_passe");
               StringBuilder mdpDecrypt = jCrypt.decrypt(mdpInDb.replaceAll("\\s", "").toUpperCase(),1);
               String mdpAverifer = String.valueOf(mdpDecrypt);
               if (mdpAverifer.equalsIgnoreCase(password)){
                   mdpConfirmer=true;
               }

           }

       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
       return mdpConfirmer;
    }

    /**
     * Methode pour changer le mot de passe de l'utlisateur en bdd
     * @param newpassword le nouveau mot de passe a enregister
     * @param id le numero de l'utlisteur
     */
    @Override
    public void changePassword(String newpassword, int id) {
        PreparedStatement ps =null;
        JCrypt jCrypt =new JCrypt();
        try(Connection cnx = ConnectionProvider.getConnection()) {
            ps= cnx.prepareStatement(UPDATE_PASSWORD_SQL);
            StringBuilder cryptedPassw = jCrypt.encrypt(newpassword.replaceAll("\\s", "").toUpperCase(), 1);
            ps.setString(1,String.valueOf(cryptedPassw));
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }





    }



