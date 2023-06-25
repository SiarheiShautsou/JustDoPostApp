package by.sheva.web.servlet;

import by.sheva.service.comment.CommentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/comment/delete")
public class DeleteCommentServlet extends HttpServlet {

    private final CommentServiceImpl commentService = CommentServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int commentId = Integer.parseInt(req.getParameter("commentId"));
        int postId = Integer.parseInt(req.getParameter("postId"));

        try {

            commentService.deleteComment(commentId);

            resp.sendRedirect(req.getContextPath() + "view_post?id=" + postId);

        } catch (Exception e) {
            req.setAttribute("errormessage", "Something went wrong on our side.");
            getServletContext().getRequestDispatcher("/pages/error.jsp").forward(req, resp);
        }


    }
}
