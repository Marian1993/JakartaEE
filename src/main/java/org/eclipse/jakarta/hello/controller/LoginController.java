package org.eclipse.jakarta.hello.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.eclipse.jakarta.hello.dao.UsuariDao;
import org.eclipse.jakarta.hello.dao.UsuariDaoI;
import org.eclipse.jakarta.hello.model.Usuari;
import org.eclipse.jakarta.hello.service.UsuariService;
import org.eclipse.jakarta.hello.service.UsuariServiceI;

import java.io.IOException;
//es loginFrom, saps que ha de venir en aque
@WebServlet(name="LoginControllerServlet", value = "/login")
public class LoginController extends HttpServlet {

    UsuariServiceI usuariService;
    public LoginController(){
        UsuariDaoI usuariDao = new UsuariDao();

        this.usuariService = new UsuariService(usuariDao);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("loginForm.jsp").forward(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String ususari = request.getParameter("user");
        String contrassenya = request.getParameter("password");

        Usuari user =this.usuariService.findUsuariByUsernameAndPassword(ususari,contrassenya);

        if(user != null){
            HttpSession session = request.getSession();

            //El primer parametre es el nom que li dones en aquest atribut a la sessio,lo que es guarda al navegador, per quen la vulguis cridar
            session.setAttribute("usuari",user);
            session.setAttribute("autenticat","SI");

            response.sendRedirect("photos");
        }else {
            request.setAttribute("error","Usuari no vàlid");
            request.getRequestDispatcher("loginForm.jsp").forward(request,response);
        }


    }
}
