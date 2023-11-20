package yekhdem;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;

public class EtudiantDAO {
	
	private SpecialiterDAO spec;
	
    public EtudiantDAO() {
        
    }


	    
	    public void createEtudiant(Etudiant etudiant) {
	        try ( Connection connection = DatabaseConnection.getConnection();            
	          
	        PreparedStatement stmt = connection.prepareStatement("INSERT INTO etudiant(nom, prenom, mot_de_passe, id_Specialiter) VALUES (?, ?,  ?, ?)")) {

	            stmt.setString(1, etudiant.getNom());
	            stmt.setString(2, etudiant.getPrenom());
	            
	            stmt.setString(3, etudiant.getMotDePasse());
	            stmt.setInt(4, etudiant.getSpecialite().getIdSpecialite());

	             stmt.executeUpdate();
	            
	             stmt.close();
	            

	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }

	    public Etudiant readeEtudiant(String idEtudiant) {
	        Etudiant etudiant = null;
	         spec=new SpecialiterDAO();
	        

	        try (    
	        		Connection connection = DatabaseConnection.getConnection();    
	        		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM etudiant WHERE mot_de_passe=?")) {

	            stmt.setString(1, idEtudiant);
	            ResultSet rs = stmt.executeQuery();

	            if (rs.next()) {
	                int id = rs.getInt("id_Etudiant");
	                String nom = rs.getString("nom");
	                String prenom = rs.getString("prenom");
	                
	                String motDePasse = rs.getString("mot_de_passe");
	                Specialiter specialite = spec.getSpecialiteById(rs.getInt("id_Specialiter"));

	                etudiant = new Etudiant( nom, prenom, motDePasse, specialite);
	            }

	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }

	        return etudiant;
	    }

	    public void updateEtudiant(Etudiant etudiant) {
	        try ( Connection connection = DatabaseConnection.getConnection();
	             PreparedStatement stmt = connection.prepareStatement("UPDATE etudiant SET nom=?, prenom=?,  mot_de_passe=?, id_Specialiter=? WHERE id_Etudiant=?")) {

	            stmt.setString(1, etudiant.getNom());
	            stmt.setString(2, etudiant.getPrenom());
	            
	            stmt.setString(3, etudiant.getMotDePasse());
	            stmt.setInt(4, etudiant.getSpecialite().getIdSpecialite());
	            stmt.setInt(5, etudiant.getIdEtudiant());

	             stmt.executeUpdate();
	             

	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    
	    }

	    public void deleteEtudiant(int idEtudiant) {
	        try (    Connection connection = DatabaseConnection.getConnection();       
	        		PreparedStatement stmt = connection.prepareStatement("DELETE FROM etudiant WHERE id_Etudiant=?")) {

	            stmt.setInt(1, idEtudiant);

	            stmt.executeUpdate();
	            

	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        
	    }
	public List<Etudiant> getEtudiants() {
	        List<Etudiant> etudiants = new ArrayList<>();
	        spec=new SpecialiterDAO();
	        try (Connection connection = DatabaseConnection.getConnection(); 
	             PreparedStatement stmt = connection.prepareStatement("SELECT * FROM etudiant")) {

	            ResultSet rs = stmt.executeQuery();

	            while (rs.next()) {
	                int id = rs.getInt("id_Etudiant");
	                String nom = rs.getString("nom");
	                String prenom = rs.getString("prenom");
	                
	                String motDePasse = rs.getString("mot_de_passe");
	                Specialiter specialite = spec.getSpecialiteById(rs.getInt("id_Specialiter"));

	                Etudiant etudiant = new Etudiant( nom, prenom,  motDePasse, specialite);
	                etudiants.add(etudiant);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return etudiants;
	    }

	 
	    public List<Etudiant> getEtudiantsBySpecialite(Specialiter specialite) {
	        List<Etudiant> etudiants = new ArrayList<>();

	        try ( Connection connection = DatabaseConnection.getConnection(); 
	             PreparedStatement stmt = connection.prepareStatement("SELECT * FROM etudiants WHERE id_Specialiter=?")) {

	            stmt.setInt(1, specialite.getIdSpecialite());
	            ResultSet rs = stmt.executeQuery();

	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String nom = rs.getString("nom");
	                String prenom = rs.getString("prenom");
	                
	                String motDePasse = rs.getString("mot_de_passe");

	                Etudiant etudiant = new Etudiant( nom, prenom, motDePasse, specialite);
	                etudiants.add(etudiant);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return etudiants;
	    }
	    public Etudiant readEtudiant(int idEtudiant) {
	        Etudiant etudiant = null;
	         spec=new SpecialiterDAO();
	        

	        try (    
	        		Connection connection = DatabaseConnection.getConnection();    
	        		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM etudiant WHERE id_Etudiant=?")) {

	            stmt.setInt(1, idEtudiant);
	            ResultSet rs = stmt.executeQuery();

	            if (rs.next()) {
	                int id = rs.getInt("id_Etudiant");
	                String nom = rs.getString("nom");
	                String prenom = rs.getString("prenom");
	                
	                String motDePasse = rs.getString("mot_de_passe");
	                Specialiter specialite = spec.getSpecialiteById(rs.getInt("id_Specialiter"));

	                etudiant = new Etudiant( nom, prenom, motDePasse, specialite);
	            }

	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }

	        return etudiant;
	    }


}
