package by.sheva.web.servlet;

import by.sheva.domain.Comment;
import by.sheva.domain.Post;
import by.sheva.security.SessionPrincipalUser;
import by.sheva.service.comment.CommentServiceImpl;
import by.sheva.service.post.PostServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/view_post")
public class ViewPostServlet extends HttpServlet {

    public static final int DEFAULT_COMMENTS_PER_PAGE = 5;

    private final PostServiceImpl postService = PostServiceImpl.getInstance();

    private final CommentServiceImpl commentService = CommentServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SessionPrincipalUser sessionPrincipalUser = (SessionPrincipalUser) req.getSession().getAttribute("user");

        try{
            int postId = Integer.parseInt(req.getParameter("id"));

            Post post = postService.findPostById(postId);
            if(post != null){
                req.setAttribute("post", post);
                req.setAttribute("likes", postService.getLikesCount(post));

                int commentsPerPage = DEFAULT_COMMENTS_PER_PAGE;
                int commentsCountByPost = commentService.getCommentCountByPost(post);
                int pagesCount = (int)Math.ceil(commentsCountByPost/commentsPerPage);

                req.setAttribute("countOfPages", pagesCount);

                String page = req.getParameter("page");
                if(page != null){
                    req.setAttribute("page", 1);
                    page = "1";
                }
                int paginationOffset = (Integer.parseInt(page) - 1) * commentsPerPage;

                List<Comment> comments = commentService.findAllCommentsByPost(post, paginationOffset, commentsPerPage);
                req.setAttribute("commentsList", comments);


                boolean like =postService.isLikedPost(post, sessionPrincipalUser.getId());
                req.setAttribute("like", like);

                getServletContext().getRequestDispatcher("pages/post.jsp").forward(req, resp);

            }else{
                req.setAttribute("errormessage", "Post not found!");
                getServletContext().getRequestDispatcher("/pages/error.jsp").forward(req, resp);
            }


        }catch (Exception e){
            req.setAttribute("errormessage", "Something went wrong on our side.");
            getServletContext().getRequestDispatcher("/pages/error.jsp").forward(req, resp);        }


    }
}

