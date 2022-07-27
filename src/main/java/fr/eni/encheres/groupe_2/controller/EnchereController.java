package fr.eni.encheres.groupe_2.controller;

import fr.eni.encheres.groupe_2.bll.ArticleManager;
import fr.eni.encheres.groupe_2.bll.BuissnessException;
import fr.eni.encheres.groupe_2.bll.CategorieManager;
import fr.eni.encheres.groupe_2.bll.EnchereManager;
import fr.eni.encheres.groupe_2.bo.Article;
import fr.eni.encheres.groupe_2.bo.Categorie;
import fr.eni.encheres.groupe_2.bo.Enchere;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "EnchereController", value = "/details-encheres/*")
public class EnchereController extends HttpServlet {
    /**
     * recupere une Instance de Enchere
     */
   EnchereManager managerEnchere = EnchereManager.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher  rd = request.getRequestDispatcher("/accueil");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher  rd = null;

        if(request.getSession().getAttribute("login")!=null){
            int noArticle = Integer.parseInt(request.getParameter("noArticle"));
            int noUilisateur = Integer.parseInt(request.getParameter("noUtilisateur"));
            int montant = 0;
            try{
                montant = Integer.parseInt(request.getParameter("montant"));
            } catch (NumberFormatException e) {
                request.setAttribute("error",20020);
            }
            Timestamp dateEnchere = Timestamp.valueOf(LocalDateTime.now());
            System.out.println(dateEnchere);
            rd = request.getRequestDispatcher("/detailArticle");
            Enchere nouvelleEnchere = new Enchere(dateEnchere,montant,noArticle,noUilisateur);
            try {
                managerEnchere.faireEnchere(nouvelleEnchere);
                request.setAttribute("meuilleurOffre",nouvelleEnchere.getMontantEnchere());
            } catch (BuissnessException e) {
                int meuilleurOffre = managerEnchere.montantMeuilleurOffre(noArticle);
                request.setAttribute("meuilleurOffre",meuilleurOffre);
                request.setAttribute("error", Integer.parseInt(e.getMessage()));
            }
        }
        else {
            rd=request.getRequestDispatcher("/loginpage");
        }
        rd.forward(request,response);
    }
}


