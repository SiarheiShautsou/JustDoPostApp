package by.sheva.web.servlet;

import by.sheva.domain.Post;
import by.sheva.domain.User;
import by.sheva.mapper.UserMapper;
import by.sheva.security.SessionPrincipalUser;
import by.sheva.service.post.PostServiceImpl;
import by.sheva.service.user.UserServiceImpl;
import by.sheva.web.request.UserRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/user/account")
public class ProfileServlet extends HttpServlet {

    private static final String USERNAME_PARAMETER = "username";
    private static final int POSTS_PER_PAGE = 9;
    private static final String PAGE_NUMBER = "page";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserServiceImpl userService = UserServiceImpl.getInstance();

        String username = req.getParameter(USERNAME_PARAMETER);
        Optional<User> account;
        try {
            account = userService.findUserByUsername(username);


            if (account.isPresent()) {
                User userAccount = account.get();
                UserRequest accountDto = UserMapper.mapUserToRequest(userAccount);
                req.setAttribute("account", accountDto);

                int subscribersCount = userService.getSubscribersCountByUser(userAccount);
                int subscriptionCount = userService.getSubscriptionsCountByUser(userAccount);

                req.setAttribute("followersCnt", subscribersCount);
                req.setAttribute("followingCnt", subscriptionCount);

                SessionPrincipalUser authorizedUser = (SessionPrincipalUser) req.getSession().getAttribute("user");
                boolean isFollowed = userService.isSubscribed(accountDto.getUsername(), authorizedUser.getUsername());

                req.setAttribute("isAlreadyFollowed", isFollowed);

                PostServiceImpl postService = PostServiceImpl.getInstance();
                int userPostsCount = postService.getUserPostsCount(userAccount);
                int countOfPages = (int) Math.ceil((double) userPostsCount / POSTS_PER_PAGE);
                req.setAttribute("countOfPages", countOfPages);

                String requestedPage = req.getParameter(PAGE_NUMBER);
                int offset;

                if (requestedPage == null) {
                    offset = 0;
                    req.setAttribute(PAGE_NUMBER, 1);
                } else {
                    offset = Integer.parseInt(requestedPage) - 1;
                    req.setAttribute(PAGE_NUMBER, requestedPage);
                }


                List<Post> posts = postService.findAllPostsByUser(userAccount, POSTS_PER_PAGE, offset);
                req.setAttribute("posts", posts);

                getServletContext().getRequestDispatcher("/pages/profile.jsp").forward(req, resp);
            } else {
                req.setAttribute("errormessage", "User not found.");
                getServletContext().getRequestDispatcher("/pages/error.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            req.setAttribute("errormessage", "Something went wrong on our side.");
            getServletContext().getRequestDispatcher("/pages/error.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
