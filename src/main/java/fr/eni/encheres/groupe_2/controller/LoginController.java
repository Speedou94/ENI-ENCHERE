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
    CategorieManager managerCategorie = CategorieManager.getInstance();
    ArticleManager managerArticle = ArticleManager.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/loginpage");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utilisateur utilisateur = null;
        RequestDispatcher rd;
        String pseudo = request.getParameter("pseudo");
        String password = request.getParameter("password");
        List<Categorie> listDesCategories = new ArrayList<>();
        List<Article> listDesArticles = new ArrayList<>();
        try {
            utilisateur = manager.login(pseudo, password);
            listDesCategories = managerCategorie.getAllCategorie();
            listDesArticles = managerArticle.getAllArticles();
            request.setAttribute("listDesCategories", listDesCategories);
            request.setAttribute("articlesDisponible", listDesArticles);
            request.setAttribute("login", utilisateur);
            rd = request.getRequestDispatcher("/accueil");

        } catch (BuissnessException e) {

            rd = request.getRequestDispatcher("/loginpage");
            request.setAttribute("error", Integer.parseInt(e.getMessage()));
        }
        rd.forward(request, response);
    }
}
