package by.sheva.web.servlet;

import by.sheva.security.SessionPrincipalUser;
import by.sheva.service.post.PostServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/post/delete")
public class DeletePostServlet extends HttpServlet {

    public static final String USER = "user";

    private final PostServiceImpl postService = PostServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SessionPrincipalUser sessionPrincipalUser = (SessionPrincipalUser) getServletContext().getAttribute(USER);

        int postId = Integer.parseInt(req.getParameter("id"));
        String username = sessionPrincipalUser.getUsername();

        try {
            postService.deletePost(postId);
            resp.sendRedirect(req.getContextPath() + "/account?username=" + username);

        } catch (Exception e) {
            req.setAttribute("errormessage", "Something went wrong on our side");
            getServletContext().getRequestDispatcher("/pages/error.jsp").forward(req, resp);
        }

    }
}
