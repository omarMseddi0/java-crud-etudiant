package yekhdem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ModuleDAO {
    
    
    public ModuleDAO() {
       
    }



    
    public void createModule(Module module) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseConnection.getConnection();
            String sql = "INSERT INTO module (id_Specialiter,nom_Module, coefficient) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(sql);
            if (module.getSpecialite() != null) {
                statement.setInt(1, module.getSpecialite().getIdSpecialite());
            } else {
                statement.setNull(1, java.sql.Types.INTEGER);
            }
            statement.setString(2, module.getNomModule());
            statement.setInt(3, module.getCoefficient());
            statement.executeUpdate();
        } catch (SQLException e) {
            // Handle the exception here
            e.printStackTrace(); // or log the error
        } finally {
            // close the connection and statement objects here to prevent leaks
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public Module readModule(int idModule) throws SQLException {
    	Connection connection = DatabaseConnection.getConnection();
       
        String sql = "SELECT nom_Module, coefficient FROM module WHERE id_Module = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idModule);
        ResultSet resultSet = statement.executeQuery();
        Module module = null;
        if (resultSet.next()) {
            module = new Module();
            module.setIdModule(idModule);
            module.setNomModule(resultSet.getString("nom_Module"));
            module.setCoefficient(resultSet.getInt("coefficient"));
        }
        resultSet.close();
        statement.close();
        return module;
    }

    public void updateModule(Module module)  {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseConnection.getConnection();
            String sql = "UPDATE Module SET nom_Module = ?, coefficient = ?, id_Specialiter=? WHERE id_Module = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, module.getNomModule());
            statement.setInt(2, module.getCoefficient());
            if(module.getSpecialite() != null) {
                statement.setInt(3, module.getSpecialite().getIdSpecialite());
            } else {
                statement.setNull(3, Types.INTEGER);
            }
            statement.setInt(4, module.getIdModule());
            statement.executeUpdate();
        } catch (SQLException e) {
            // handle the exception here, e.g. log it or throw a custom exception
            e.printStackTrace();
        } finally {
            // close the statement and connection here to prevent leaks
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



    public void deleteModule(int idModule) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseConnection.getConnection();
            String sql = "DELETE FROM modules WHERE id_Module = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idModule);
            statement.executeUpdate();
        } catch (SQLException e) {
            // Handle the exception here, e.g. log it or throw a custom exception
            e.printStackTrace();
        } finally {
            // close the statement and connection here to prevent leaks
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }




    public List<Module> getModules() {
        Connection connection = null;
        List<Module> modules = new ArrayList<>();
        String query = "SELECT * FROM Module";
        try {
            connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int coefficient = resultSet.getInt("coefficient");
                String nom = resultSet.getString("nom_Module");
                int idSpecialite = resultSet.getInt("id_Specialiter");
                modules.add(new Module(coefficient, nom, idSpecialite));
            }
        } catch (SQLException e) {
            // handle the exception here, e.g. log it or throw a custom exception
            e.printStackTrace();
        } finally {
            // close the connection here to prevent leaks
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return modules;
    }


    public Module getModuleById(int id) {
        
        Module module = null;
        String query = "SELECT * FROM Module WHERE id_Module = ?";
        try (  Connection connection = DatabaseConnection.getConnection();
        		PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String nom = resultSet.getString("nom_Module");
                    int idSpecialite = resultSet.getInt("id_Specialiter");
                    module = new Module(id, nom, idSpecialite);
                }
            }
        } catch (SQLException e) {
            // handle the exception here, e.g. log it or throw a custom exception
            e.printStackTrace();
        } finally {
            // close the connection here to prevent leaks
            
        }
        return module;
    }


    public List<Module> getModulesBySpecialite(Specialiter specialite) {
        Connection connection = null;
        List<Module> modules = new ArrayList<>();
        String query = "SELECT * FROM Module WHERE id_Specialiter = ?";
        try {
            connection = DatabaseConnection.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, specialite.getIdSpecialite());
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id_Module");
                        String nom = resultSet.getString("nom_Module");
                        int idSpecialite = resultSet.getInt("id_Specialiter");
                        modules.add(new Module(id, nom, idSpecialite));
                    }
                }
            }
        } catch (SQLException e) {
            // handle the exception, for example by logging it or showing an error message
            System.err.println("An error occurred while getting modules by specialite: " + e.getMessage());
        } finally {
            // close the connection if it was opened
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    // handle the exception, for example by logging it or showing an error message
                    System.err.println("An error occurred while closing the connection: " + e.getMessage());
                }
            }
        }
        // Return an empty list if no modules are found or an exception occurred
        if (modules.isEmpty()) {
            return new ArrayList<>();
        }
        return modules;
    }


}

