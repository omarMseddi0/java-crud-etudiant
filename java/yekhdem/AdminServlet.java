package yekhdem;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    // Inject the EJB you need to use in your servlet methods
	private ModuleDAO Bean;
	private SpecialiterDAO Beanee;
    private NoteDAO Beane;
    private EtudiantDAO etudiantBean;
    public void init() {
        // Initialize the Login object
    	etudiantBean = new EtudiantDAO();
    	Bean = new ModuleDAO();
    	Beane = new NoteDAO();
    	Beanee = new SpecialiterDAO();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the action parameter from the submitted form
        String action = request.getParameter("action");
        
        // Forward to the appropriate JSP based on the action parameter
        if ("gestionEtudiant".equals(action)) {
            // Get all the etudiants and forward to gestionEtudiant.jsp
            List<Etudiant> etudiants = etudiantBean.getEtudiants();
            request.setAttribute("etudiants", etudiants);
            request.getRequestDispatcher("/GestionEtudiant.jsp").forward(request, response);
        } else if ("gestionModule".equals(action)) {
            // Get all the modules and forward to gestionModule.jsp
            List<Module> modules = Bean.getModules();
            request.setAttribute("modules", modules);
            request.getRequestDispatcher("/GestionModule.jsp").forward(request, response);
        } else if ("gestionSpecialite".equals(action)) {
            // Get all the specialites and forward to gestionSpecialite.jsp
            List<Specialiter> specialites = Beanee.getSpecialites();
            request.setAttribute("specialites", specialites);
            request.getRequestDispatcher("/gestionSpecialite.jsp").forward(request, response);
        } else if ("gestionNote".equals(action)) {
            // Get all the notes and forward to gestionNote.jsp
            List<Note> notes = Beane.getNotes();
            request.setAttribute("notes", notes);
            request.getRequestDispatcher("/GestionNote.jsp").forward(request, response);
        } else {
            // Unknown action, forward to an error page
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
