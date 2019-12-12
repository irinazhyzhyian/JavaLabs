package servlets;

import dto.CountMedicineDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddCountMedicineServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("add", true);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("count-form.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String count = req.getParameter("count");
            String rawId = req.getParameter("id");


            CountMedicineDTO medicineDTO = new CountMedicineDTO();

            medicineDTO.setCount(Integer.parseInt(count));
            //medicineDTO.setMedicine();
            medicineDTO.setId(Integer.parseInt(rawId));

            //MedicineService medicineService = new MedicineService();
            //medicineService.insert(medicineDTO);
        } catch (Exception ex) {
            ex.printStackTrace();
            resp.sendError(400, ex.toString());
            return;
        }
        resp.sendRedirect("./countmed");
    }
}
