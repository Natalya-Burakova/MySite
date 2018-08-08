package servlets;

import db.connect.Connect;
import db.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;


@WebServlet("/logIn")
public class LogInServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if (req.getAttribute("er")==null)
            req.setAttribute("er", "");
        req.getRequestDispatcher("html/logIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if (req.getParameter("login")!=null && req.getParameter("password")!=null) {
           String login = req.getParameter("login");
           String password = req.getParameter("password");

           if (checkUser(login, password)) {
               HttpSession session = req.getSession();
               session.setAttribute("user", login);
               resp.sendRedirect(req.getContextPath() + "/home");
           }
           else {
               req.setAttribute("er", "ПОЛЬЗОВАТЕЛЯ С ТАКИМИ ДАННЫМИ НЕ СУЩЕСТВУЕТ!");
               doGet(req, resp);
           }
       }
       else {
            req.setAttribute("er", "ПОЛЯ НЕ ЗАПОЛНЕНЫ!");
            doGet(req, resp);
        }

    }

    private boolean checkUser(String login, String password){
        List<User> listUser = Connect.getUserDao().findAll();
        for(User user: listUser){
            if(user.getLogin().equals(login) && user.getPassword().equals(password))
                return true;
        }
        return false;
    }

}
