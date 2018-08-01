package servlets;

import db.Connect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/logIn")
public class LogInServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("start/logIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        Connection conection = Connect.openConnection();
        Statement statement = Connect.openStatement();

        try (ResultSet result = statement.executeQuery("SELECT * FROM new_user")) {
            boolean flag = false;
            while(result.next()) {
                if (login.equals(result.getString("login")) && password.equals(result.getString("password"))) {
                    flag = true;
                    resp.sendRedirect(req.getContextPath()+"/home");
                }
            }
            if (!flag)
                resp.sendRedirect(req.getContextPath()+"/logIn");
        }catch (SQLException e){ e.printStackTrace();}

        finally {
            Connect.closeStatement();
            Connect.closeConnection();
        }

    }
}
