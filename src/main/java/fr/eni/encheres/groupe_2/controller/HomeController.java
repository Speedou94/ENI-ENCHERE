package fr.eni.encheres.groupe_2.controller;

import fr.eni.encheres.groupe_2.bll.ArticleManager;
import fr.eni.encheres.groupe_2.bll.CategorieManager;
import fr.eni.encheres.groupe_2.bo.Article;
import fr.eni.encheres.groupe_2.bo.Categorie;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "HomeController", value = "/encheres/*")
public class HomeController extends HttpServlet {
    CategorieManager managerCategorie = CategorieManager.getInstance();
    ArticleManager managerArticle = ArticleManager.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/accueil");
        List<Categorie> listDesCategories = new ArrayList<>();
        List<Article> listDesArticles = new ArrayList<>();
        try {
            listDesCategories = managerCategorie.catalogue();
            listDesArticles = managerArticle.catalogueEnchereOuverte();

            request.getSession().setAttribute("listDesCategories", listDesCategories);
            request.getSession().setAttribute("articlesDisponible", listDesArticles);

            if (request.getParameter("Categories") != null) {
                int idCategorie = Integer.parseInt(request.getParameter("Categories"));
                String motClef = request.getParameter("search");
                listDesArticles = managerArticle.filteredByCategorie(idCategorie);
                request.setAttribute("articlesDisponible", listDesArticles);
                if (motClef.length()>0){
                    listDesArticles = managerArticle.filteredListArticlesByName(motClef,idCategorie);
                    request.setAttribute("articlesDisponible", listDesArticles);
                }
            }

        } finally {

        }


        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {




    }
}
