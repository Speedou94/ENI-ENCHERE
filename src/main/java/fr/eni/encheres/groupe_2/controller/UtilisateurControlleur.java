package fr.eni.encheres.groupe_2.controller;

import fr.eni.encheres.groupe_2.bll.BuissnessException;
import fr.eni.encheres.groupe_2.bll.UtilisateurManager;
import fr.eni.encheres.groupe_2.bo.Utilisateur;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UtilisateurControlleur", value = "/utilisateurs/*")
public class UtilisateurControlleur extends HttpServlet {
    UtilisateurManager manager = UtilisateurManager.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher rd = null;

        if (request.getSession().getAttribute("login") != null) {
            rd = request.getRequestDispatcher("/profilPage");
        } else {
            rd = request.getRequestDispatcher("/signup");
        }

        rd.forward(request, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("");

        String pseudo = request.getParameter("pseudo");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String telephone = request.getParameter("telephone");
        String rue = request.getParameter("rue");
        String ville = request.getParameter("ville");
        String codePostal = request.getParameter("codePostal");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        if (request.getParameter("addNewUtilisateur") != null) {
            try {
                boolean valide = verifPassword(password, confirmPassword);
                if (valide) {
                    Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, password);
                    manager.newUtilisateur(utilisateur);
                    request.getSession().setAttribute("login", utilisateur);
                    rd = request.getRequestDispatcher("/accueil");

                }
            } catch (BuissnessException e) {

                rd = request.getRequestDispatcher("/signup");
                request.setAttribute("error", Integer.parseInt(e.getMessage()));
            }
        }
        if (request.getParameter("editUtilisateur") != null) {
            try {

                int id = Integer.parseInt(request.getParameter("editUtilisateur"));
                Utilisateur utilisateur = new Utilisateur(id, pseudo, nom, prenom, email, telephone, rue, codePostal, ville);
                //  manager.updateUtilisater(utilisateur);
                request.setAttribute("user",utilisateur);
                request.setAttribute("confirmModal", "update");
                //  request.getSession().setAttribute("login", utilisateur);
                rd = request.getRequestDispatcher("/profilPage");

            } /*catch (BuissnessException e) {
                request.setAttribute("error", Integer.parseInt(e.getMessage()));
                request.setAttribute("editProfil", true);
                rd = request.getRequestDispatcher("/profilPage");

            }*/ finally {

            }


        }
        if(request.getParameter("delete")!=null){
            rd = request.getRequestDispatcher("/profilPage");
            request.setAttribute("confirmModal","delete");

        }
        rd.forward(request, resp);
    }

    private boolean verifPassword(String password, String confirmPassword) throws BuissnessException {
        boolean ok = true;
        if (!password.equals(confirmPassword)) {
            try {
                throw new BuissnessException(CodeErrorController.BAD_PASSWORD_CONFIRMATION);
            } catch (BuissnessException e) {
                throw new RuntimeException(e);
            }
        }
        return ok;
    }
}
