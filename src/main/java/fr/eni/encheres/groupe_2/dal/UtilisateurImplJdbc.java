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

    @Override
    public void delete(int id) {

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

    //TODO:Faire la javadoc
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
     * TODO: FAIRE LA JAVADOC
     *
     * @param pseudo
     * @param email
     * @return
     * @throws BuissnessException
     */

    /**
     * méthode VerifPseudoAndMail à remonter dans BLL
     * @param pseudo
     * @param email
     * @return
     * @throws BuissnessException
     */
    private boolean verifPseudoAndMail(String pseudo, String email) throws BuissnessException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> listePseudo = new ArrayList<>();
        List<String> listeEmail = new ArrayList<>();
        try (Connection cnx = ConnectionProvider.getConnection()) {
            ps = cnx.prepareStatement(VERIF_PSEUDO_ET_MAIL_SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                String pseudoDb = rs.getString("pseudo");
                String emailDb = rs.getString("email");
                listeEmail.add(emailDb);
                listePseudo.add(pseudoDb);
            }
            if (listePseudo.contains(pseudo)) {

                throw new BuissnessException(ErrorCodeDAL.PSEUDO_DEJA_UTILISE);
            }
            if (listeEmail.contains(email)) {

                throw new BuissnessException(ErrorCodeDAL.EMAIL_DEJA_UTILISE);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    private boolean verifPseudoMailUpdate (String pseudo, String email, int id) throws BuissnessException{
       boolean pseudoMailValide = verifPseudoAndMail(pseudo,email);

       if(!pseudoMailValide){
        String getPseudoMailSql = "SELECT pseudo, email FROM dbo.UTILISATEURS WHERE no_utilisateur = ?";

            try (Connection cnx = ConnectionProvider.getConnection()) {
                PreparedStatement preparedStatement = cnx.prepareStatement(getPseudoMailSql);
                preparedStatement.setInt(1, id);
                ResultSet rs = preparedStatement.executeQuery();
                System.out.println("je fais mon PrepareSt");

                while (rs.next()) {
                    String pseudoOriginal = rs.getString("pseudo");
                    String mailOriginal = rs.getString("email");
                    System.out.println("je recupère  Pseudo Original et email Ori"+pseudoOriginal+mailOriginal);

                    if (pseudoOriginal.equalsIgnoreCase((pseudo))) {

                        pseudoMailValide = true;
                    }
                    if (mailOriginal.equalsIgnoreCase(email)) {
                        pseudoMailValide = true;

                    }


                }
            }catch (SQLException e) {
                throw new RuntimeException(e);
            }


        }return pseudoMailValide;



    }

}

