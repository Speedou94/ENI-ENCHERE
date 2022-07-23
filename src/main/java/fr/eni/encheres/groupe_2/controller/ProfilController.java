package fr.eni.encheres.groupe_2.controller;

import fr.eni.encheres.groupe_2.bll.UtilisateurManager;
import fr.eni.encheres.groupe_2.bo.Utilisateur;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ProfilController", value = "/profil/*")
public class ProfilController extends HttpServlet {
    UtilisateurManager manager = UtilisateurManager.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/profilPage");
        if (request.getParameter("edit") != null) {
            request.setAttribute("editProfil", true);
        }
//TODO : faire que lorsqu un ulisitateur clique sur  profil dans la navbar prive ca affiche son profil (indice le parameter "login" contient deja toute les info)
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//TODO: ici cela sera la method update pour la modif du profil , ne pas oublie de controle les champs et de faire suivre les erreur le cas echeant


    }

}
