package servlets;

import lab5.DAO.CountMedicineDAO;
import lab5.database.ConnectionManager;
import lab5.model.CountMedicine;
import lab5.model.Medicine;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/countMedicine")
public class CountMedicineServlet extends HttpServlet {

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

                    CountMedicineDAO countMedicineDAO = new CountMedicineDAO(connection);
                    CountMedicine countMedicine = countMedicineDAO.read(id);
                    Medicine medicine = countMedicine.getMedicine();

                    req.setAttribute("medicine", medicine);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("countMedicine.jsp");
                requestDispatcher.forward(req, resp);
            }
        }
    }
}
