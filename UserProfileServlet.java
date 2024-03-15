package test;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/dis")
public class UserProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int age = Integer.parseInt(request.getParameter("age"));
        String dateOfBirth = request.getParameter("dob"); // Expected format: yyyy-mm-dd

      
        
        try (Connection connection = DBConnection.getConncetion();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO user_profile (name, email, age, date_of_birth) VALUES (?, ?, ?, ?)")) {
            
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setInt(3, age);
            statement.setDate(4, java.sql.Date.valueOf(dateOfBirth));

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("success.html"); 
    }
}
