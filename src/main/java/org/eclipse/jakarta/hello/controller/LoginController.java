package org.eclipse.jakarta.hello.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.eclipse.jakarta.hello.model.Foto;

import java.io.IOException;
//es loginFrom, saps que ha de venir en aque
@WebServlet(name="LoginControllerServlet", value = "/login")
public class LoginController extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("loginForm.jsp").forward(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String ususari = request.getParameter("user");
        String contrassenya = request.getParameter("password");

        if(ususari.equals("politecnic") && contrassenya.equals("1234")){
            //Crear la sessió
            HttpSession session = request.getSession();
            session.setAttribute("usuari",ususari);
            session.setAttribute("autenticat","SI");

            response.sendRedirect("photos");
        }else {
            request.setAttribute("error","Usuari no vàlid");
            request.getRequestDispatcher("loginForm.jsp").forward(request,response);
        }
    }
}
