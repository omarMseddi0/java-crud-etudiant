package yekhdem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginBean {
	 private Connection connection;
	  
	public LoginBean() {
		 
	   
	}
	public String authenticate(String nom, String motDePasse) {
	    String role = "";
	    String query = "SELECT * FROM etudiant WHERE nom = ? AND mot_de_passe = ? UNION SELECT * FROM administrateur WHERE nom = ? AND mot_de_passe = ?";
	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(query)) {
	        statement.setString(1, nom);
	        statement.setString(2, motDePasse);
	        statement.setString(3, nom);
	        statement.setString(4, motDePasse);
	        ResultSet result = statement.executeQuery();
	        if (result.next()) {
	            if (result.getString("id_Etudiant") != null) {
	                role = "etudiant";
	            } else if (result.getString("id_Administrateur") != null) {
	                role = "admin";
	            }
	        }
	    } catch (SQLException e) {
	        // Handle exception, e.g. log error message
	    }
	    return role;
	}
}
