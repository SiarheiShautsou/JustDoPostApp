package by.sheva.web.servlet;

import by.sheva.domain.Post;
import by.sheva.domain.User;
import by.sheva.service.post.PostServiceImpl;
import by.sheva.service.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {


    private static final int DEFAULT_PAGES = 0;
    private static final int  DEFAULT_POSTS_PER_PAGE = 5;

    private final UserServiceImpl userService = UserServiceImpl.getInstance();

    private final PostServiceImpl postService = PostServiceImpl.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User sessionUser = (User) req.getSession().getAttribute("user");

        try{
        List<User> subscriptions = userService.getAllSubscriptionsByUser(sessionUser);

        int pageCount = DEFAULT_PAGES;

        for (User subscription : subscriptions) {
            int userPostsCount = postService.getUserPostsCount(subscription);
            int countOfPagesWithPosts = (int)Math.ceil((double) userPostsCount / DEFAULT_POSTS_PER_PAGE);
            pageCount = pageCount + countOfPagesWithPosts;
        }
        req.setAttribute("countOfPages", pageCount);

        String page = req.getParameter("page");
        int pages = 0;

        if(page == null){
            req.setAttribute("page", 1);
            pages = 1;
        }else{
            pages = Integer.parseInt(page);
        }

        int paginationOffset = (pages - 1) * DEFAULT_POSTS_PER_PAGE;

        List<Post> posts = postService.findPostsLikedByUser(sessionUser, DEFAULT_POSTS_PER_PAGE, paginationOffset);

        req.setAttribute("postList", posts);

        req.getRequestDispatcher("/pages/home.jsp").forward(req, resp);

        } catch (Exception e) {
            req.setAttribute("errormessage", "Something went wrong on our side.");
            getServletContext().getRequestDispatcher("/pages/error.jsp").forward(req, resp);
        }

    }
}
