package by.sheva.web.servlet;

import by.sheva.domain.Comment;
import by.sheva.domain.Post;
import by.sheva.domain.User;
import by.sheva.security.SessionPrincipalUser;
import by.sheva.service.comment.CommentServiceImpl;
import by.sheva.service.post.PostServiceImpl;
import by.sheva.service.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/comment/add")
public class AddCommentServlet extends HttpServlet {

    public static final String USER = "user";

    private final PostServiceImpl postService = PostServiceImpl.getInstance();

    private final CommentServiceImpl commentService = CommentServiceImpl.getInstance();

    private final UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SessionPrincipalUser sessionPrincipalUser = (SessionPrincipalUser) getServletContext().getAttribute(USER);

        int postId = Integer.parseInt(req.getParameter("postId"));

        try{
            User user = userService.findUserById(sessionPrincipalUser.getId());
            Post post = postService.findPostById(postId);
            if(post != null){

                String commentMessage = req.getParameter("commentMessage");
                Comment comment = Comment.builder()
                        .setMessage(commentMessage)
                        .setPost(post)
                        .setUser(user)
                        .setCreatedAt(new Date(System.currentTimeMillis()))
                        .build();
                commentService.creatComment(comment);

                resp.sendRedirect(req.getContextPath() + "view_post?id=" + post.getPostId());

            }else{
                req.setAttribute("errormessage", "We can't found post.");
                getServletContext().getRequestDispatcher("/pages/error.jsp").forward(req, resp);
            }

        }catch (Exception e){
            req.setAttribute("errormessage", "Something went wrong on our side.");
            getServletContext().getRequestDispatcher("/pages/error.jsp").forward(req, resp);
        }


    }
}
