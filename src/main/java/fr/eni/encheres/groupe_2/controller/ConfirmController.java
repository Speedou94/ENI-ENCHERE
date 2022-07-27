package fr.eni.encheres.groupe_2.controller;

import fr.eni.encheres.groupe_2.bll.BuissnessException;
import fr.eni.encheres.groupe_2.bll.UtilisateurManager;
import fr.eni.encheres.groupe_2.bo.Utilisateur;

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
                    request.setAttribute("editProfil", true);
                    request.setAttribute("error", Integer.parseInt(e.getMessage()));
                    rd=request.getRequestDispatcher("/profilPage");
                }
            }

            if(action.equals("update")){
                    String password = request.getParameter("password");
                    Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("user");
                    utilisateur.setMotDePasse(password);
                    try {
                        manager.updateUtilisater(utilisateur,password);
                        request.getSession().setAttribute("login", utilisateur);
                        request.setAttribute("succes",50001);
                        rd=request.getRequestDispatcher("/profilPage");
                    } catch (BuissnessException e) {
                        request.setAttribute("editProfil", true);
                        rd=request.getRequestDispatcher("/profilPage");
                        request.setAttribute("error", Integer.parseInt(e.getMessage()));
                    }
            }

            if (action.equals("newpassword")){
                    String password = request.getParameter("password");
                    String confimpassword = request.getParameter("confirmPassword");
                    String newpassord =request.getParameter("newPassword");


                    try {

                        boolean inputConfirm = verifPassword(password,confimpassword);
                        System.out.println(inputConfirm);
                        int id = Integer.parseInt(request.getParameter("idUtilisateur"));

                             if(inputConfirm){
                                 manager.updatePassword(password,newpassord,id);
                                 request.setAttribute("succes",50000);
                                 rd=request.getRequestDispatcher("/profilPage");
                                }
                        } catch (BuissnessException e) {

                            request.setAttribute("editProfil", true);
                            rd=request.getRequestDispatcher("/profilPage");
                            request.setAttribute("error", Integer.parseInt(e.getMessage()));


                        }
                rd.forward(request,response);
        }
            rd.forward(request,response);
    }
}
    else {
        rd = request.getRequestDispatcher("/loginpage");
        rd.forward(request,response);
         }

    }

    /**
     * Verifie que les input mot de passe et confirm mot de passe sont bien les meme
     * @param password la valeur de l'input mot de passe
     * @param confirmPassword la valeur de l'input confirm mot de passe
     * @return un boolen true si les chmaps sont identique
     * @throws BuissnessException Remonte l'erreur a l'utlisateur en cas de mauvaise saisie
     */
    private boolean verifPassword(String password, String confirmPassword) throws BuissnessException {
        boolean ok = true;
        if (!password.equals(confirmPassword)) {
           throw new BuissnessException(CodeErrorController.BAD_PASSWORD_CONFIRMATION);
        }
        return ok;
    }
}
