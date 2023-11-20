package yekhdem;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/GestionModuleServlet")
public class GestionModuleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ModuleDAO moduleDao;
    private SpecialiterDAO specialiterDao;
    

    public void init() {
        moduleDao = new ModuleDAO(); // initialize DAO object
        specialiterDao = new SpecialiterDAO();
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
                deleteModule(request, response);
                break;
            default:
                listModules(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        switch (action) {
            case "add":
                addModule(request, response);
                break;
            case "edit":
                updateModule(request, response);
                break;
        }
    }

    private void listModules(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Module> modules = moduleDao.getModules(); // get list of modules from DAO

        request.setAttribute("modules", modules);
        request.getRequestDispatcher("/gestionModule.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	List<Specialiter> specialiters = specialiterDao.getSpecialites();
    	request.setAttribute("specialiters",specialiters);
    	

    	request.getRequestDispatcher("/gestionModule.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	  
         
         
        int id = Integer.parseInt(request.getParameter("id"));
        Module module = moduleDao.getModuleById(id);
        List<Specialiter> specialiters = specialiterDao.getSpecialites();
    	request.setAttribute("specialiters",specialiters);
        // get module by id from DAO

        request.setAttribute("module", module);
        request.getRequestDispatcher("/gestionModule.jsp").forward(request, response);
    }

    private void addModule(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	int specialiterId = Integer.parseInt(request.getParameter("spesialiter"));
    	 Specialiter specialiter = specialiterDao.getSpecialiteById(specialiterId) ;
    	 

    	int etudiantId = Integer.parseInt(request.getParameter("coeficient"));
        String nom = request.getParameter("nom");
        

        Module module = new Module( specialiter,nom, etudiantId);
        moduleDao.createModule(module); // add module to DAO

        response.sendRedirect("GestionModuleServlet");
    }

    private void updateModule(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        int specialiterId = Integer.parseInt(request.getParameter("spesialiter"));
    
        int etudiantId = Integer.parseInt(request.getParameter("coeficient"));
        String nom = request.getParameter("nom");
        Specialiter specialiter = specialiterDao.getSpecialiteById(specialiterId) ;

        Module module = new Module(specialiter, nom,etudiantId);
        module.setIdModule(etudiantId);
        moduleDao.updateModule(module); // update module in DAO

        response.sendRedirect("GestionModuleServlet");
    }

    private void deleteModule(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        moduleDao.deleteModule(id); // delete module from DAO

        response.sendRedirect("GestionModuleServlet");
    }
}
