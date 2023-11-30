package org.eclipse.jakarta.hello.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jakarta.hello.dao.FotoDaoI;
import org.eclipse.jakarta.hello.dao.FotoDatabaseDao;
import org.eclipse.jakarta.hello.model.Foto;
import org.eclipse.jakarta.hello.service.FotoDataBaseService;
import org.eclipse.jakarta.hello.service.FotoMockService;
import org.eclipse.jakarta.hello.service.FotoServiceI;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

@WebServlet(name = "PhotosForm", value = "/photoForm")
public class FotoFormController extends HttpServlet {

    ResourceBundle rb = ResourceBundle.getBundle("application");
    private String hola = rb.getString("politecnic.prova");
    private String isDevelopment = rb.getString("development");
    FotoServiceI fotoService;

    Properties properties = new Properties();
    public FotoFormController() throws IOException {
        InputStream input = this.getClass().getClassLoader().getResourceAsStream("application.properties");
        properties.load(input);

        if(isDevelopment.equals("true")){
            fotoService = new FotoMockService();
        }else {
            FotoDaoI fotoDao = new FotoDatabaseDao();
            fotoService = new FotoDataBaseService(fotoDao);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try {
            request.getRequestDispatcher("fotoForm.jsp").forward(request,response);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Entra al formulari");
        String nom = request.getParameter("nom");
        String descripcio = request.getParameter("descripcio");
        String url = request.getParameter("url");
        boolean privada = false;
        if(request.getParameter("privada") != null && request.getParameter("privada").equals("si")){

            privada = true;
        }
        Foto foto = new Foto();
        foto.setNom(nom);
        foto.setDescripcio(descripcio);
        foto.setUrl(url);
        foto.setPrivada(privada);

        try{
            fotoService.insert(foto);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        response.sendRedirect("photos");
    }
}
