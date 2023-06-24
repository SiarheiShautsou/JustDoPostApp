package by.sheva.web.servlet;

import by.sheva.domain.User;
import by.sheva.mapper.UserMapper;
import by.sheva.security.SessionPrincipalUser;
import by.sheva.service.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final String USERNAME = "username";

    private static final String PASSWORD = "password";

    private final UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/pages/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter(USERNAME);
        String password = req.getParameter(PASSWORD);

        Optional<User> userByUsername = userService.findUserByUsername(username);

        try {
            if (userByUsername.isPresent()) {

                if (userByUsername.get().getPassword().equals(password)) {

                    SessionPrincipalUser principalUser = UserMapper.mapToSessionPrincipalUser(userByUsername.get());

                    req.getSession().setAttribute("user", principalUser);
                    req.setAttribute(USERNAME, principalUser.getUsername());
                    getServletContext().getRequestDispatcher("/user/account").forward(req, resp);

                } else {
                    req.getSession().setAttribute("message", "Wrong password");
                    getServletContext().getRequestDispatcher("/pages/login.jsp").forward(req, resp);
                }

            } else {
                req.setAttribute("message", "User not found");
                getServletContext().getRequestDispatcher("/pages/login.jsp").forward(req, resp);
            }
        }catch (Exception e) {
            req.setAttribute("errormessage", "Something went wrong on our side.");
            getServletContext().getRequestDispatcher("/pages/error.jsp").forward(req, resp);
        }

    }
}
