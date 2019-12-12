package servlets;

import dto.MedicineDTO;
import exception.ServiceException;
import service.MedicineService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;

@WebServlet(urlPatterns = "/add-medicine")
@MultipartConfig
public class AddMedicineServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("add", true);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("medicine-form.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            String rawPrice = req.getParameter("price");
            String rawOverdueDate = req.getParameter("overdueDate");
            String rawForm = req.getParameter("form");
            String rawId = req.getParameter("id");

            LocalDate overdueDate = new SimpleDateFormat("yyyy-MM-dd")
                    .parse(rawOverdueDate).toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            MedicineDTO medicineDTO = new MedicineDTO();

            medicineDTO.setName(name);
            medicineDTO.setPrice(Double.parseDouble(rawPrice));
            medicineDTO.setOverdueDay(overdueDate);
            medicineDTO.setForm(rawForm);
            medicineDTO.setId(Integer.parseInt(rawId));

            MedicineService medicineService = new MedicineService();
            medicineService.insert(medicineDTO);
        } catch (Exception | ServiceException ex) {
            ex.printStackTrace();
            resp.sendError(400, ex.toString());
            return;
        }
        resp.sendRedirect("./medicines");
    }

}
