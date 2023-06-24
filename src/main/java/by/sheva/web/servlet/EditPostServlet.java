package by.sheva.web.servlet;

import by.sheva.domain.Post;
import by.sheva.service.post.PostServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit_post")
public class EditPostServlet extends HttpServlet {

    private final PostServiceImpl postService = PostServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/pages/editPost.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");

        if (id != null) {

            try {
                int postId = Integer.parseInt(id);
                Post post = postService.findPostById(postId);

                if (post != null) {

                    req.setAttribute("post", post);

                    String newDescription = req.getParameter("newDescription");
                    String newImage = req.getParameter("newImage");

                    Post updatedPost = Post.builder()
                            .setPostId(post.getPostId())
                            .setImage(newImage)
                            .setUser(post.getUser())
                            .setDescription(newDescription)
                            .setCreatedAt(post.getCreatedAt())
                            .setComments(post.getComments())
                            .build();

                    postService.updatePost(updatedPost);

                    resp.sendRedirect(req.getContextPath() + "user/viewpost?id=" + id);
                }

            } catch (Exception e) {
                req.setAttribute("errormessage", "Something went wrong on our side.");
                getServletContext().getRequestDispatcher("/pages/error.jsp").forward(req, resp);
            }
        }

    }
}
