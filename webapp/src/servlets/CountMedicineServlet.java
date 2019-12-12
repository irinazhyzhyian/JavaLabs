package servlets;

import dto.CountMedicineDTO;
import exception.ServiceException;
import lab5.model.Medicine;
import service.CountMedicineService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/countMedicine")
public class CountMedicineServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String rawId = req.getParameter("id");
        String rawMedId = req.getParameter("medicine_id");
        if (rawId != null) {
            Integer id = null;

            try {
                id = Integer.parseInt(rawId);
            } catch (Exception ignored) {
            }

            if (id != null) {
                CountMedicineDTO countMedicineDTO;
                try {
                    CountMedicineService countMedicine = new CountMedicineService();
                    countMedicineDTO = countMedicine.findById(id);
                    if(countMedicineDTO==null) {
                        resp.sendRedirect("./");
                        return;
                    }

                    Medicine medicine = countMedicineDTO.getMedicine();

                    req.setAttribute("medicine", medicine);
                } catch (ServiceException e) {
                    e.printStackTrace();
                }
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("countMedicine.jsp");
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
                CountMedicineService service = new CountMedicineService();
                service.delete(id);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
            resp.sendRedirect("./");
        }
    }
}
