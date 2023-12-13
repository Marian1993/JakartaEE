package org.eclipse.jakarta.hello.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.eclipse.jakarta.hello.dao.FotoDaoI;
import org.eclipse.jakarta.hello.dao.FotoDatabaseDao;
import org.eclipse.jakarta.hello.model.Foto;
import org.eclipse.jakarta.hello.service.FotoDataBaseService;
import org.eclipse.jakarta.hello.service.FotoMockService;
import org.eclipse.jakarta.hello.service.CrudServiceI;
import org.eclipse.jakarta.hello.service.FotoServiceI;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

@WebServlet(name = "Photos", value = "/photos")
public class FotoController extends HttpServlet {

    //http://localhost:8080/jakartaee-hello-world/photos

    ResourceBundle rb = ResourceBundle.getBundle("application");
    private String hola = rb.getString("politecnic.prova");
    private String isDevelopment = rb.getString("development");
    FotoServiceI fotoService;

    Properties properties = new Properties();
    public FotoController() throws IOException {
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
            List<Foto> fotos = fotoService.findAll();

            HttpSession session = request.getSession();
            String autenticat = (String)session.getAttribute("autenticat");

            System.out.println(autenticat);

            if(autenticat == null || !autenticat.equals("SI")) {
                fotos = fotos.stream().filter(f->!f.getPrivada()).toList();
            }

            request.setAttribute("pictures", fotos);
            request.setAttribute("titol", hola);
            request.getRequestDispatcher("fotos.jsp").forward(request, response);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
