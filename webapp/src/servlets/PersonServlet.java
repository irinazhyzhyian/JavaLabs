package servlets;

import lab5.DAO.PersonDAO;
import lab5.database.ConnectionManager;
import lab5.model.Person;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

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
                try (Connection connection = ConnectionManager.getConnection()) {

                    PersonDAO personDAO = new PersonDAO(connection);

                    Person person = personDAO.read(id);

                    req.setAttribute("person", person);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("person.jsp");
                requestDispatcher.forward(req, resp);
            }
        }
    }
}
