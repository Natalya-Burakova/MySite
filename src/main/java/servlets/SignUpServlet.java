package servlets;

import db.connect.Connect;
import db.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("html/signUp.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    if (req.getParameter("name")!=null && req.getParameter("mail")!=null & req.getParameter("login")!=null && req.getParameter("password")!=null) {
        String name = req.getParameter("name");
        String mail = req.getParameter("mail");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (checkUser(login))
            resp.sendRedirect(req.getContextPath() + "/signUp");
        else {
            User user = new User(name, mail, login, password);
            Connect.getUserDao().save(user);
            resp.sendRedirect(req.getContextPath() + "/logIn");
        }
    }
    else
        doGet(req, resp);
    }

    private boolean checkUser(String login){
        Optional<User> user = Connect.getUserDao().findByLogin(login);
        if (user.isPresent())
            return true;
        return false;
    }

}
