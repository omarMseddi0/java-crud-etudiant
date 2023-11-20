package yekhdem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoteDAO {
    


    // Methode de création
    public void createNote(Note note) {
        try ( Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement("INSERT INTO note (id_Etudiant, id_Module, note) VALUES (?, ?, ?)")) {

            stmt.setInt(1, note.getEtudiant().getIdEtudiant());
            stmt.setInt(2, note.getModule().getIdModule());
            stmt.setDouble(3, note.getNote());
            stmt.executeUpdate();

            
        } catch (SQLException e) {
            
        }
    }

    // Methode de lecture 
public Note readNote(int idNote) {
    Note note = null;
    try ( Connection connection = DatabaseConnection.getConnection();
         PreparedStatement stmt = connection.prepareStatement("SELECT * FROM note WHERE id_Note=?")) {
        stmt.setInt(1, idNote);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("id");
            float noteValue = rs.getFloat("note");
            int etudiantId = rs.getInt("id_Etudiant");
            int moduleId = rs.getInt("id_Module");

            EtudiantDAO opEtudiant = new EtudiantDAO();
            Etudiant etudiant = opEtudiant.readEtudiant(etudiantId);
            ModuleDAO  operationModule= new ModuleDAO();
            
            Module module = operationModule.readModule(moduleId);

            note = new Note(etudiant,module,noteValue);
           
        } 
    } catch (SQLException e) {
        
    }
    return note;
}

    // Methode de mise à jour
    public void updateNote(Note note) {
        try ( Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement("UPDATE note SET id_Etudiant=?, id_Module=?, note=? WHERE id_Note=?")) {

            stmt.setInt(1, note.getEtudiant().getIdEtudiant());
            stmt.setInt(2, note.getModule().getIdModule());
            stmt.setDouble(3, note.getNote());
            stmt.setInt(4, note.getIdNote());

             stmt.executeUpdate();

            
        } catch (SQLException e) {
            
        }
    }
public void deleteNote(int idNote) {
    try ( Connection connection = DatabaseConnection.getConnection();
         PreparedStatement stmt = connection.prepareStatement("DELETE FROM note WHERE id_Note=?")) {

        stmt.setInt(1, idNote);
        stmt.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
public List<Note> getNotes() {
    List<Note> notes = new ArrayList<>();
    EtudiantDAO opEtudiant = new EtudiantDAO();
    ModuleDAO  operationModule= new ModuleDAO();
    		try (Connection connection = DatabaseConnection.getConnection();
         Statement stmt = connection.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT * FROM note")) {

        while (rs.next()) {
            int id = rs.getInt("id");
            int idEtudiant = rs.getInt("id_Etudiant");
            int idModule = rs.getInt("id_Module");
            float valeur = rs.getFloat("note");
     
            Etudiant etudiant = opEtudiant.readEtudiant(idEtudiant);
            Module module = operationModule.getModuleById(idModule);

            Note note = new Note( etudiant, module, valeur);
            notes.add(note);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return notes;
}

public Note getNoteById(int id) {
    Note note = null;
    EtudiantDAO opEtudiant = new EtudiantDAO();
    ModuleDAO  operationModule= new ModuleDAO();

    try (    
    		Connection connection = DatabaseConnection.getConnection();
         PreparedStatement stmt = connection.prepareStatement("SELECT * FROM note WHERE id=?")) {

        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            int idEtudiant = rs.getInt("id_Etudiant");
            int idModule = rs.getInt("id_Module");
            float valeur = rs.getFloat("valeur");

            Etudiant etudiant = opEtudiant.readEtudiant(idEtudiant);
            Module module = operationModule.getModuleById(idModule);

            note = new Note( etudiant, module, valeur);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return note;
}

public List<Note> getNotesByEtudiant(int idEtudiant) {
    List<Note> notes = new ArrayList<>();
    EtudiantDAO opEtudiant = new EtudiantDAO();
    ModuleDAO  operationModule= new ModuleDAO();

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement stmt = connection.prepareStatement("SELECT * FROM note WHERE id_Etudiant=?")) {

        stmt.setInt(1, idEtudiant);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id_Etudiant");
            int idModule = rs.getInt("id_Module");
            float valeur = rs.getFloat("note");

            Etudiant etudiant = opEtudiant.readEtudiant(idEtudiant);
            Module module = operationModule.getModuleById(idModule);

            Note note = new Note( etudiant, module, valeur);
            notes.add(note);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return notes;
}

public List<Note> getNotesByModule(int idModule) {
    List<Note> notes = new ArrayList<>();
    EtudiantDAO opEtudiant = new EtudiantDAO();
    ModuleDAO  operationModule= new ModuleDAO();

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement stmt = connection.prepareStatement("SELECT * FROM note WHERE id_Module=?")) {

        stmt.setInt(1, idModule);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            int idEtudiant = rs.getInt("id_Etudiant");
            float valeur = rs.getFloat("note");

            Etudiant etudiant = opEtudiant.readEtudiant(idEtudiant);
            Module module = operationModule.getModuleById(idModule);

            Note note = new Note( etudiant, module, valeur);
            notes.add(note);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return notes;
}
public List<Note> getNotesByModuleAndEtudiant(Module module, Etudiant etudiant) throws SQLException {
    
    PreparedStatement stmt = null;
    ResultSet rs = null;
    List<Note> notes = new ArrayList<>();
    EtudiantDAO  EtudiantDAO = new EtudiantDAO();
    ModuleDAO  ModuleDAO= new ModuleDAO();

    try {
    	Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT * FROM note WHERE id_Module = ? AND id_Etudiant = ?";
        stmt = connection.prepareStatement(query);
        stmt.setInt(1, module.getIdModule());
        stmt.setInt(2, etudiant.getIdEtudiant());
        rs = stmt.executeQuery();

        while (rs.next()) {
            
        	
            float valeur = rs.getFloat("note");
            

            // Set the associated module and student objects
            Module associatedModule = ModuleDAO.getModuleById(rs.getInt("id_Module"));
            Etudiant associatedEtudiant = EtudiantDAO.readEtudiant(rs.getInt("id_Etudiant"));
            
            
            Note note = new Note(associatedEtudiant, associatedModule, valeur);
            notes.add(note);
        }
    } finally {
        if (rs != null) {
            rs.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        
    }

    return notes;
}

}
 
