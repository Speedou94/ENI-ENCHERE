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

@WebServlet (name= "UtilisateurControlleur", value="/utilisateurs/*")
public class UtilisateurControlleur extends HttpServlet {
    UtilisateurManager manager = UtilisateurManager.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/signup");
        rd.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("");
    String pseudo = req.getParameter("pseudo");
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        String email = req.getParameter("email");
        String telephone = req.getParameter("telephone");
        String rue = req.getParameter("rue");
        String ville = req.getParameter("ville");
        String codePostal = req.getParameter("codePostal");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");

        Utilisateur utilisateur = new Utilisateur(pseudo,nom, prenom, email, telephone, rue, codePostal,ville, password);

        try {
    manager.newUtilisateur(utilisateur);

        }catch (BuissnessException e)
        {
            e.printStackTrace();
        }
    }
}
