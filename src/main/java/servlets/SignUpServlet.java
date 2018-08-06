package servlets;

import db.Connect;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("html/signUp.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String mail = req.getParameter("mail");
        String login = req.getParameter("login");
        String password = req.getParameter("password");


        Connection connection = Connect.openConnection();
        Statement statement = Connect.openStatement();

        if (checkUser(login))
            resp.sendRedirect(req.getContextPath() + "/signUp");
        else{
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO new_user(name,mail,login, password) VALUES (?,?,?,?)");
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, mail);
                preparedStatement.setString(3, login);
                preparedStatement.setString(4, login);
                preparedStatement.execute();
            } catch (SQLException e) { e.printStackTrace();
            } finally { Connect.closeStatement(); Connect.closeConnection(); }
            resp.sendRedirect(req.getContextPath() + "/logIn");
        }

    }

    private boolean checkUser(String login){
        boolean flag = false;

        Connection conection = Connect.openConnection();
        Statement statement = Connect.openStatement();

        try (ResultSet result = statement.executeQuery("SELECT * FROM new_user")) {
            while (result.next()) {
                if (login.equals(result.getString("login")))
                    flag = true;
            }
        } catch (SQLException e) { e.printStackTrace();
        } finally { Connect.closeStatement(); Connect.closeConnection(); }

        return flag;
    }

}
