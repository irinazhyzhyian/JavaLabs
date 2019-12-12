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
import java.util.List;

@WebServlet(urlPatterns = "/workers")
public class PersonsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<PersonDTO> persons;
        try {
            PersonService service = new PersonService();
            persons = service.findAll();
        } catch (ServiceException e) {
            e.printStackTrace();
            resp.sendRedirect("./");
            return;
        }

        req.setAttribute("persons", persons);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("workers.jsp");
        requestDispatcher.forward(req, resp);
    }
}
