package servlets;

import dto.PharmacyDTO;
import exception.ServiceException;
import lab5.model.CountMedicine;
import service.PharmacyService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/pharmacy")
public class PharmacyServlet extends HttpServlet {

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
                PharmacyDTO pharmacyDTO;
                try {
                    PharmacyService pharmacyService = new PharmacyService();
                    pharmacyDTO = pharmacyService.findById(id);

                    List<CountMedicine> countMedicines = pharmacyService.getListCountMedicine(id);

                    req.setAttribute("pharmacy", pharmacyDTO);
                    req.setAttribute("countMedicines", countMedicines);
                } catch (SQLException | ServiceException e) {
                    e.printStackTrace();
                }
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("pharmacy.jsp");
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
                PharmacyService pharmacyService = new PharmacyService();
                pharmacyService.delete(id);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect("./");
    }
}