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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;

@WebServlet(urlPatterns = "/add-person")
public class AddPersonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("add", true);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("person-form.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            String rawBirthday = req.getParameter("birthday");
            String rawSalary = req.getParameter("salary");
            String rawId = req.getParameter("id");

            LocalDate birthday = new SimpleDateFormat("yyyy-MM-dd")
                    .parse(rawBirthday).toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            PersonDTO personDTO = new PersonDTO();

            personDTO.setFirstName(firstName);
            personDTO.setSalary(Double.parseDouble(rawSalary));
            personDTO.setBirthday(birthday);
            personDTO.setLastName(lastName);
            personDTO.setId(Integer.parseInt(rawId));

            PersonService service = new PersonService();
            service.insert(personDTO);
        } catch (Exception | ServiceException ex) {
            ex.printStackTrace();
            resp.sendError(400, ex.toString());
            return;
        }
        resp.sendRedirect("./workers");
    }
}
