package fr.eni.encheres.groupe_2.controller;

import fr.eni.encheres.groupe_2.bll.BuissnessException;
import fr.eni.encheres.groupe_2.bll.UtilisateurManager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ConfirmController", value = "/confirm/*")
public class ConfirmController extends HttpServlet {
    UtilisateurManager manager = UtilisateurManager.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
RequestDispatcher rd = request.getRequestDispatcher("/");
if(request.getSession().getAttribute("login")!=null){
    if(request.getParameter("valider")!=null){
        String action = request.getParameter("action");
      if(action.equals("delete")){
          try {

              String password = request.getParameter("password");
              int id = Integer.parseInt(request.getParameter("idUtilisateur"));
              manager.deleteUtilisateur(password,id);
              request.removeAttribute("login");
              request.getSession().invalidate();
              rd=request.getRequestDispatcher("/logout");
          }
          catch (BuissnessException e) {
              request.setAttribute("error", Integer.parseInt(e.getMessage()));
              rd=request.getRequestDispatcher("/profilPage");
          }
          rd.forward(request,response);
      }
      if(action.equals("update")){
          System.out.println("update");
          System.out.println(request.getAttribute("user"));

          rd = request.getRequestDispatcher("/loginpage");
      }

    }


}else {
    rd = request.getRequestDispatcher("/loginpage");
}
rd.forward(request,response);
    }
}
