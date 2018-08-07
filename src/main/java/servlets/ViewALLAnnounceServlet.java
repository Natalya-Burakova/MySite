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
import java.util.Optional;

@WebServlet("/viewALLAnnounce")
public class ViewALLAnnounceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //  List<User> user = Connect.getUserDao().findAllAnnounce(); //вернули всех пользователей со всеми объялениями
    }
}
