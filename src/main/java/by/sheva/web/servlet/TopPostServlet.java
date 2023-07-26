package by.sheva.web.servlet;

import by.sheva.domain.Post;
import by.sheva.service.post.PostServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TopPostServlet extends HttpServlet {

    private static final PostServiceImpl postService = PostServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Post> popularPosts = postService.findMostPopularPosts();

        req.setAttribute("postList", popularPosts);

        getServletContext().getRequestDispatcher("/pages/topPost.jsp").forward(req, resp);
    }
}
