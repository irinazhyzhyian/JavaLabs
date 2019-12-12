package servlets;

import dto.PersonDTO;
import exception.ServiceException;
import service.PersonService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/person")
public class PersonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String rawId = req.getParameter("id");
        if (rawId != null) {
            Integer id = null;

            try {
                id = Integer.parseInt(rawId);
            } catch (Exception ignored) {
            }

            if (id != null) {
                PersonDTO personDTO;
                try {
                    PersonService person = new PersonService();
                    personDTO = person.findById(id);

                    if(personDTO==null) {
                        resp.sendRedirect("./");
                        return;
                    }


                    req.setAttribute("person", personDTO);
                } catch (ServiceException e) {
                    e.printStackTrace();
                }
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("person.jsp");
                requestDispatcher.forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String rawId = req.getParameter("id");
        if (rawId != null) {
            Integer id = null;

            try {
                id = Integer.parseInt(rawId);
            } catch (Exception ignored) {
            }
            try {
                PersonService service = new PersonService();
                service.delete(id);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
            resp.sendRedirect("./");
        }
    }

}
