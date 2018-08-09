package servlets;

import db.connect.Connect;
import db.model.Announcement;
import db.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/viewAnnounce")
public class ViewAnnounceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession(false);
        String login = (String) session.getAttribute("user");
        Optional<User> userOpt = Connect.getUserDao().findByLoginAllAnnounce(login);
        List<Announcement> listAnnounce = userOpt.get().getAnnouncementList();

        req.setAttribute("listAnnounce", listAnnounce);
        req.getRequestDispatcher("html/view.jsp").forward(req, resp);

    }
}
