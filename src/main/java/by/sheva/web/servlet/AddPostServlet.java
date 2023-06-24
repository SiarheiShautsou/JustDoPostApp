package by.sheva.web.servlet;

import by.sheva.domain.Post;
import by.sheva.domain.User;
import by.sheva.security.SessionPrincipalUser;
import by.sheva.service.post.PostServiceImpl;
import by.sheva.service.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/add_post")
public class AddPostServlet extends HttpServlet {

    private final PostServiceImpl postService = PostServiceImpl.getInstance();

    private final UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/pages/addPost.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SessionPrincipalUser sessionPrincipalUser = (SessionPrincipalUser) req.getSession().getAttribute("user");

        String description = req.getParameter("newDescription");
        String image = req.getParameter("newImage");

        try {

            User user = userService.findUserById(sessionPrincipalUser.getId());

            Post newPost = Post.builder()
                    .setImage(image)
                    .setUser(user)
                    .setDescription(description)
                    .setCreatedAt(new Date(System.currentTimeMillis()))
                    .build();

            postService.creatPost(newPost);

            resp.sendRedirect(req.getContextPath() + "/post");

        } catch (Exception e) {
            req.setAttribute("errormessage", "Something went wrong on our side.");
            getServletContext().getRequestDispatcher("/pages/error.jsp").forward(req, resp);
        }

    }
}
