package servlets;

import db.Connect;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("start/signUp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String mail = req.getParameter("mail");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        Connection connection = Connect.openConnection();
        Statement statement = Connect.openStatement();

        try {
            statement.executeUpdate("INSERT into new_user(name, mail, login, password) VALUES "+"('" +name+"', '"+mail+"', '"+login+"', '"+password+"');");
            Connect.closeStatement();
            Connect.closeConnection();
        } catch (SQLException e) { e.printStackTrace();}

        req.getRequestDispatcher("start/home.jsp").forward(req, resp);

    }
}
