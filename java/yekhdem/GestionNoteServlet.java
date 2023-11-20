package yekhdem;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class GestionNoteServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private NoteDAO noteDAO;
    private EtudiantDAO etudiantDAO;
    private ModuleDAO moduleDAO;
    private SpecialiterDAO specialiterDAO;
    
    @Override
    public void init() throws ServletException {
        noteDAO = new NoteDAO();
        etudiantDAO = new EtudiantDAO();
        moduleDAO = new ModuleDAO();
        specialiterDAO = new SpecialiterDAO();
        
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        
        switch (action) {
            case "list":
                listNotes(request, response);
                break;
            case "add":
                addNoteForm(request, response);
                break;
            case "edit":
                editNoteForm(request, response);
                break;
            case "delete":
                deleteNote(request, response);
                break;
            default:
                listNotes(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        
        switch (action) {
            case "add":
                addNote(request, response);
                break;
            case "edit":
                editNote(request, response);
                break;
            default:
                listNotes(request, response);
        }
    }
    
    private void listNotes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Note> notes = noteDAO.getNotes();
        request.setAttribute("notes", notes);
        request.getRequestDispatcher("/GestionNote.jsp").forward(request, response);
    }
    
    private void addNoteForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	List<Specialiter> specialiters = specialiterDAO.getSpecialites();
        List<Etudiant> etudiants = etudiantDAO.getEtudiants();
        List<Module> modules = moduleDAO.getModules();
        request.setAttribute("specialiters",specialiters);
        request.setAttribute("etudiants",etudiants);
        request.setAttribute("modules", modules);
        request.getRequestDispatcher("/GestionNlote.jsp").forward(request, response);
    }
    
    private void addNote(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	int specialiterId = Integer.parseInt(request.getParameter("spesialiter"));
        int etudiantId = Integer.parseInt(request.getParameter("etudiant"));
        int moduleId = Integer.parseInt(request.getParameter("module"));
        float note = Float.parseFloat(request.getParameter("note"));
        Specialiter specialiter = specialiterDAO.getSpecialiteById(specialiterId) ;
        Etudiant etudiant = etudiantDAO.readEtudiant(etudiantId);
        Module module = moduleDAO.getModuleById(moduleId);
        Note newNote = new Note(etudiant, module, note);
        
        noteDAO.createNote(newNote);
        response.sendRedirect(request.getContextPath() + "/GestionNoteServlet");
    }
    
    private void editNoteForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int noteId = Integer.parseInt(request.getParameter("id"));
        Note note = noteDAO.getNoteById(noteId);
        List<Etudiant> etudiants = etudiantDAO.getEtudiants();
        List<Specialiter> specialiters = specialiterDAO.getSpecialites();
        
        List<Module> modules = moduleDAO.getModules();
        request.setAttribute("note", note);
        request.setAttribute("etudiants", etudiants);
        request.setAttribute("modules", modules);
        request.setAttribute("specialiters",specialiters);
        request.getRequestDispatcher("/GestionNote.jsp").forward(request, response);
    }
    
    private void editNote(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int noteId = Integer.parseInt(request.getParameter("id"));
        int etudiantId = Integer.parseInt(request.getParameter("etudiant"));
        int moduleId = Integer.parseInt(request.getParameter("module"));
        float valeur = Float.parseFloat(request.getParameter("note"));
        Etudiant etudiant = etudiantDAO.readEtudiant(etudiantId);
        Module module = moduleDAO.getModuleById(moduleId);

        Note newNote = new Note(etudiant, module, valeur);
        newNote.setIdNote(noteId); 

        noteDAO.updateNote(newNote);

        response.sendRedirect("GestionNoteServlet");
    }
    private void deleteNote(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int noteId = Integer.parseInt(request.getParameter("id"));
        noteDAO.deleteNote(noteId);
        response.sendRedirect("gestionNote");
    }

}
