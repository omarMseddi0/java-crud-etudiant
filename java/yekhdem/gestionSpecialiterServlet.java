package yekhdem;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/gestionSpecialiterServlet")
public class gestionSpecialiterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private SpecialiterDAO specialiterDao;

    public gestionSpecialiterServlet() {
        super();
        specialiterDao = new SpecialiterDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "add":
                    showAddForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteSpecialiter(request, response);
                    break;
                default:
                    listSpecialiters(request, response);
                    break;
            }
        } else {
            listSpecialiters(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "add":
                    addSpecialiter(request, response);
                    break;
                case "update":
                    updateSpecialiter(request, response);
                    break;
                default:
                    listSpecialiters(request, response);
                    break;
            }
        } else {
            listSpecialiters(request, response);
        }
    }

    private void listSpecialiters(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Specialiter> specialiters = specialiterDao.getSpecialites();
        request.setAttribute("specialiters", specialiters);
        request.getRequestDispatcher("/gestionSpecialiter.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        request.getRequestDispatcher("/gestionSpecialiter.jsp").forward(request, response);
    }

    private void addSpecialiter(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nom = request.getParameter("nom");
        Specialiter specialiter = new Specialiter(nom);
        specialiterDao.createSpecialite(specialiter);
        response.sendRedirect("gestionSpecialiterServlet");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Specialiter specialiter = specialiterDao.getSpecialiteById(id);
        request.setAttribute("specialiter", specialiter);
        request.getRequestDispatcher("/gestionSpecialiter.jsp").forward(request, response);
    }

    private void updateSpecialiter(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nom = request.getParameter("nom");
        Specialiter specialiter = new Specialiter( nom);
        specialiter.setIdSpecialite(id);
        specialiterDao.updateSpecialite(specialiter);
        response.sendRedirect("gestionSpecialiterServlet");
    }

    private void deleteSpecialiter(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        specialiterDao.deleteSpecialite(id);
        response.sendRedirect("GestionSpecialiterServlet");
    }
}
