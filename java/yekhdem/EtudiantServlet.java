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
	@WebServlet("/etudiant")
	public class EtudiantServlet extends HttpServlet {
	    private static final long serialVersionUID = 1L;
	    private ModuleDAO Bean;
	    private NoteDAO Beane;
	    private EtudiantDAO etudiantBean;
	    public void init() {
	        // Initialize the Login object
	    	etudiantBean = new EtudiantDAO();
	    	Bean = new ModuleDAO();
	    	Beane = new NoteDAO();
	    }

	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        // Get the currently logged in etudiant's username from the session
	        HttpSession session = request.getSession();
	        String password =  (String) request.getSession().getAttribute("password");

	        // Call the getEtudiantByUsername method on the EJB session bean
	        Etudiant etudiant = etudiantBean.readeEtudiant(password);

	        // Call the getModulesBySpecialite method on the EJB session bean to get the modules for the etudiant's specialite
	        List<Module> modules = Bean.getModulesBySpecialite(etudiant.getSpecialite());

	        // Call the getNotesByEtudiant method on the EJB session bean to get the etudiant's notes
	        List<Note> notes = Beane.getNotesByEtudiant(etudiant.getIdEtudiant());

	        // Call the getAbsencesByEtudiant method on the EJB session bean to get the etudiant's absences
	        

	        // Set the etudiant, modules, notes, and absences as request attributes
	        request.setAttribute("etudiant", etudiant);
	        request.setAttribute("modules", modules);
	        request.setAttribute("notes", notes);
	        

	        // Forward the request to the etudiant.jsp page
	        request.getRequestDispatcher("Etudiant.jsp").forward(request, response);

	    }

	}


