package fr.eni.encheres.groupe_2.controller;

import fr.eni.encheres.groupe_2.bll.ArticleManager;
import fr.eni.encheres.groupe_2.bll.BuissnessException;
import fr.eni.encheres.groupe_2.bll.CategorieManager;
import fr.eni.encheres.groupe_2.bo.Article;
import fr.eni.encheres.groupe_2.bo.Categorie;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "EnchereController", value = "/details-encheres/*")
public class EnchereController extends HttpServlet {
    //CategorieManager managerCategorie = CategorieManager.getInstance();
   ArticleManager managerArticle = ArticleManager.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher  rd = null;
        if(request.getSession()!=null){
            rd = request.getRequestDispatcher("/detailArticle");
            int id = Integer.parseInt(request.getParameter("id"));
            try {
                 Article  article = managerArticle.getSelectedArticle(id);
                 request.setAttribute("detailArticle", article);
        } catch (Exception e) {
        e.printStackTrace();
        }
    }
        else {
        rd=request.getRequestDispatcher("/loginpage");
    }
        rd.forward(request,response);



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}


