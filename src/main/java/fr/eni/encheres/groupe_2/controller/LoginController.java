package fr.eni.encheres.groupe_2.controller;

import fr.eni.encheres.groupe_2.bll.BuissnessException;
import fr.eni.encheres.groupe_2.bll.UtilisateurManager;
import fr.eni.encheres.groupe_2.bo.Utilisateur;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {
    UtilisateurManager manager = UtilisateurManager.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
RequestDispatcher rd = request.getRequestDispatcher("/loginpage");

rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utilisateur utilisateur= null;
        RequestDispatcher rd ;
        String pseudo = request.getParameter("pseudo");
        String password = request.getParameter("password");
        try {
                utilisateur =  manager.login(pseudo,password);
                request.setAttribute("login",utilisateur);
                rd = request.getRequestDispatcher("/logged");

             }
        catch (BuissnessException e){
            //mettre un timeout sur le taost
                rd = request.getRequestDispatcher("/loginpage");
                request.setAttribute("error",Integer.parseInt(e.getMessage()));
            }
        rd.forward(request,response);
    }
}
