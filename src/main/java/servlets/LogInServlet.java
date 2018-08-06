package servlets;

import com.sun.net.httpserver.Headers;
import db.Connect;
import sun.jvm.hotspot.memory.HeapBlock;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

@WebServlet("/logIn")
public class LogInServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("html/logIn.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (checkUser(login, password)) {
            HttpSession session = req.getSession();
            session.setAttribute("user", login);
            resp.sendRedirect(req.getContextPath()+"/home");
        }
        else
            doGet(req, resp);

    }

    private boolean checkUser(String login, String password){
        boolean flag = false;

        Connection conection = Connect.openConnection();
        Statement statement = Connect.openStatement();

        try (ResultSet result = statement.executeQuery("SELECT * FROM new_user")) {
            while (result.next()) {
                if (login.equals(result.getString("login")) && password.equals(result.getString("password")))
                    flag = true;
            }
        } catch (SQLException e) { e.printStackTrace();
        } finally { Connect.closeStatement(); Connect.closeConnection(); }

        return flag;
    }


}
