package fr.eni.encheres.groupe_2.controller;

import fr.eni.encheres.groupe_2.bll.UtilisateurManager;

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

rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
