package fr.eni.encheres.groupe_2.controller;

import fr.eni.encheres.groupe_2.bll.ArticleManager;
import fr.eni.encheres.groupe_2.bll.BuissnessException;
import fr.eni.encheres.groupe_2.bll.CategorieManager;
import fr.eni.encheres.groupe_2.bll.UtilisateurManager;
import fr.eni.encheres.groupe_2.bo.Article;
import fr.eni.encheres.groupe_2.bo.Categorie;
import fr.eni.encheres.groupe_2.bo.Utilisateur;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {
    UtilisateurManager manager = UtilisateurManager.getInstance();
    ArticleManager managerArticle = ArticleManager.getInstance();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/loginpage");
        if(request.getParameter("logout")!=null){
            request.removeAttribute("login");
            request.getSession().invalidate();
            rd = request.getRequestDispatcher("/logout");
        }
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utilisateur utilisateur = null;
        RequestDispatcher rd;
        String pseudo = request.getParameter("pseudo");
        String password = request.getParameter("password");

        try {
            utilisateur = manager.login(pseudo, password);
            request.getSession().setAttribute("login", utilisateur);
            managerArticle.verifEnchereFini(utilisateur.getNoUtilisateur());
            rd = request.getRequestDispatcher("/accueil");

        } catch (BuissnessException e) {

            rd = request.getRequestDispatcher("/loginpage");
            request.setAttribute("error", Integer.parseInt(e.getMessage()));

        }
        rd.forward(request, response);

    }
}
