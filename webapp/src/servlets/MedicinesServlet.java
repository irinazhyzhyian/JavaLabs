package servlets;

import dto.MedicineDTO;
import exception.ServiceException;
import service.MedicineService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/medicines")
public class MedicinesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<MedicineDTO> medicines;
        try {
            MedicineService medicineService = new MedicineService();
            medicines = medicineService.findAll();
        } catch (ServiceException e) {
            e.printStackTrace();
            resp.sendRedirect("./");
            return;
        }

        req.setAttribute("medicines", medicines);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("medicines.jsp");
        requestDispatcher.forward(req, resp);
    }
}
