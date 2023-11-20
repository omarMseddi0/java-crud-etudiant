package yekhdem;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/GestionEtudiantServlet")
public class GestionEtudiantServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EtudiantDAO etudiantDAO;
    private SpecialiterDAO s;
    

    public GestionEtudiantServlet() {
        etudiantDAO = new EtudiantDAO();
         s=new SpecialiterDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "add":
                showAddForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteEtudiant(request, response);
                break;
            default:
                listEtudiants(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "add":
                addEtudiant(request, response);
                break;
            case "update":
                updateEtudiant(request, response);
                break;
            default:
                listEtudiants(request, response);
        }
    }

    private void listEtudiants(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Etudiant> etudiants = etudiantDAO.getEtudiants();
        request.setAttribute("etudiants", etudiants);
        request.getRequestDispatcher("GestionEtudiant.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	List<Specialiter> specialiter = s.getSpecialites();
        request.setAttribute("specialiter", specialiter);
        request.setAttribute("etudiant", new Etudiant());
        request.getRequestDispatcher("GestionEtudiant.jsp").forward(request, response);
    }

    private void addEtudiant(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        int specialiterId = Integer.parseInt(request.getParameter("specialiter"));
        Specialiter specialiter =s.getSpecialiteById(specialiterId);
        
        String motDePasse = request.getParameter("motDePasse");

      

        Etudiant etudiant = new Etudiant(nom, prenom,  motDePasse,specialiter);
        etudiantDAO.createEtudiant(etudiant);

        response.sendRedirect("GestionEtudiantServlet");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Etudiant etudiant = etudiantDAO.readEtudiant(id);
        List<Specialiter> specialiter = s.getSpecialites();
        request.setAttribute("specialiter", specialiter);
        request.setAttribute("etudiant", etudiant);
        request.getRequestDispatcher("GestionEtudiant.jsp").forward(request, response);
    }

    private void updateEtudiant(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        int specialiterId = Integer.parseInt(request.getParameter("specialiter"));
        String motDePasse = request.getParameter("motDePasse");
        Specialiter specialiter =s.getSpecialiteById(specialiterId);

        Etudiant etudiant = new Etudiant( nom, prenom, motDePasse,specialiter);
        etudiant.setIdEtudient(id);
        
        etudiantDAO.updateEtudiant(etudiant);

        response.sendRedirect("GestionEtudiantServlet");
    }

  private void deleteEtudiant(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    etudiantDAO.deleteEtudiant(id);
    response.sendRedirect("GestionEtudiantServlet");
}
}