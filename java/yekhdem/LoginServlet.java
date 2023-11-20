package yekhdem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    private LoginBean loginBean;
    public void init() {
        // Initialize the Login object
        loginBean = new LoginBean();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	 // Get the username and password from the request parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Call the authenticate method on the EJB session bean
        String userType = password;

        // Based on the user type, redirect to the appropriate page and set session variables
        if (userType.length() == 12) {
            request.getSession().setAttribute("username", username);
            request.getSession().setAttribute("password", password);
            response.sendRedirect("administrateur.jsp");
        } else if (userType.length() == 10) {
            request.getSession().setAttribute("username", username);
            request.getSession().setAttribute("password", password);
            response.sendRedirect("Etudiant.jsp");
        } else {
            // If authentication fails, redirect back to the login page with an error message
            response.sendRedirect("Login.jsp?error=1");
        }

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

}
