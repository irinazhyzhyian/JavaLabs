package servlets;

import lab5.DAO.PharmacyDAO;
import lab5.database.ConnectionManager;
import lab5.model.CountMedicine;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
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
                try (Connection connection = ConnectionManager.getConnection()) {

                    PharmacyDAO pharmacyDAO = new PharmacyDAO(connection);

                    List<CountMedicine> countMedicines = pharmacyDAO.getListCountMedicine(pharmacyDAO.read(id));

                    req.setAttribute("countMedicines", countMedicines);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("pharmacy.jsp");
                requestDispatcher.forward(req, resp);
            }
        }
    }

    //TODO
    //doDelete(), doPost()
    //ask about buttons
   /* @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if("delete".equals(req.getParameter("action"))) {
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

                    PharmacyDAO pharmacyDAO = new PharmacyDAO(connection);
                    pharmacyDAO.delete(pharmacyDAO.read(id));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        CountMedicine countMedicine = new CountMedicine();
        Pharmacy pharmacy = new Pharmacy();
        Medicine medicine = new Medicine();

        medicine.setName("medicine_name");
        medicine.setForm("form");
        medicine.setOverdueDay(LocalDate.parse(req.getParameter("overdue_day")));
        countMedicine.setMedicine(medicine);
        countMedicine.setCount(Integer.parseInt(req.getParameter("count")));

        pharmacy.setCountMedicines(countMedicine);
        pharmacy.setName(req.getParameter("pharmacy_name"));

        //String[] number = req.getParameterValues();


            if (id != null) {
                try (Connection connection = ConnectionManager.getConnection()) {

                    PharmacyDAO pharmacyDAO = new PharmacyDAO(connection);

                    List<CountMedicine> countMedicines = pharmacyDAO.getListCountMedicine(pharmacyDAO.read(id));

                    req.setAttribute("countMedicines", countMedicines);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("pharmacy.jsp");
                requestDispatcher.forward(req, resp);
            }
        }
    }
    */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        try (Connection connection = ConnectionManager.getConnection()) {

            PharmacyDAO pharmacyDAO = new PharmacyDAO(connection);
            pharmacyDAO.deleteById(Integer.parseInt(req.getParameter("id")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}