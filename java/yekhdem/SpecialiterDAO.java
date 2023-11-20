package yekhdem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

public class SpecialiterDAO {
	private Connection connection;
	
	public void createSpecialite(Specialiter specialite) {
        try (    Connection connection = DatabaseConnection.getConnection();   
        		PreparedStatement stmt = connection.prepareStatement("INSERT INTO specialiter (nom_Specialiter) VALUES (?)")) {

            stmt.setString(1, specialite.getNomSpecialite());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Specialiter readSpecialite(int idSpecialite) {
        Specialiter specialite = null;

        try (Connection connection = DatabaseConnection.getConnection();  
             PreparedStatement stmt = connection.prepareStatement("SELECT * FROM specialiter WHERE id_Specialiter=?")) {

            stmt.setInt(1, idSpecialite);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nom = rs.getString("nom");
                specialite = new Specialiter( nom);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return specialite;
    }

    public void updateSpecialite(Specialiter specialite) {
        try (Connection connection = DatabaseConnection.getConnection();  
             PreparedStatement stmt = connection.prepareStatement("UPDATE specialiter SET nom_Specialiter=? WHERE id_Specialiter=?")) {

            stmt.setString(1, specialite.getNomSpecialite());
            stmt.setInt(2, specialite.getIdSpecialite());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSpecialite(int idSpecialite) {
        try (Connection connection = DatabaseConnection.getConnection();  
             PreparedStatement stmt = connection.prepareStatement("DELETE FROM specialiter WHERE id_Specialiter=?")) {

            stmt.setInt(1, idSpecialite);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Specialiter> getSpecialites() {
        List<Specialiter> specialites = new ArrayList<>();
        try { Connection connection = DatabaseConnection.getConnection();  
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM specialiter");
            while (resultSet.next()) {
                int id = resultSet.getInt("id_Specialiter");
                String nom = resultSet.getString("nom_Specialiter");
                Specialiter specialite = new Specialiter( nom);
                specialites.add(specialite);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return specialites;
    }

    public Specialiter getSpecialiteById(int id) {
        Specialiter specialite = null;
        try { Connection connection = DatabaseConnection.getConnection();             
        	PreparedStatement statement = connection.prepareStatement("SELECT * FROM specialiter WHERE id_Specialiter=?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String nom = resultSet.getString("nom_Specialiter");
                specialite = new Specialiter( nom);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return specialite;
    }


}

