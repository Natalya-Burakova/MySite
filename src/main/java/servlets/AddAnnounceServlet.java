package servlets;

import db.connect.Connect;
import db.model.Announcement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/addAnnounce")
public class AddAnnounceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("html/add.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("announce") != null && req.getParameter("text") != null && req.getParameter("imgAnnounce") != null) {

            String announce = req.getParameter("announce");
            String text = req.getParameter("text");
            String image = req.getParameter("imgAnnounce");

            HttpSession session = req.getSession(false);
            String login = (String) session.getAttribute("user");

            if (Connect.getUserDao().getUsersMap().containsKey(login)) {
                Announcement announcement = new Announcement(announce, text, image, Connect.getUserDao().getUsersMap().get(login));
                Connect.getUserDao().saveAnnouncement(announcement);
                resp.sendRedirect(req.getContextPath() + "/viewAnnounce");
            }
        } else
            doGet(req, resp);
    }

}
