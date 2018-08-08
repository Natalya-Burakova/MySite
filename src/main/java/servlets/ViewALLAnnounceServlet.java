package servlets;

import db.connect.Connect;
import db.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/viewALLAnnounce")
public class ViewALLAnnounceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        List<User> list = Connect.getUserDao().findAllAnnouncement();
        req.setAttribute("listAll", list);
        req.getRequestDispatcher("html/viewAll.jsp").forward(req, resp);
    }
}
