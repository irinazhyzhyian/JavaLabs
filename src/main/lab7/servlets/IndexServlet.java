package main.lab7.servlets;

import main.lab5.DAO.PharmacyDAO;
import main.lab5.database.ConnectionManager;
import main.lab5.model.Pharmacy;

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

@WebServlet(urlPatterns = "")
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(Connection connection = ConnectionManager.getConnection()) {

            PharmacyDAO pharmacyDAO = new PharmacyDAO(connection);

            List<Pharmacy> pharmacies = pharmacyDAO.findAll();

            req.setAttribute("pharmacies", pharmacies);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("pharmacies.jsp");
        requestDispatcher.forward(req, resp);
    }

}
