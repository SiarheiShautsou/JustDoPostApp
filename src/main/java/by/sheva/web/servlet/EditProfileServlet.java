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

@WebServlet("/user/account/edit")
public class EditProfileServlet extends HttpServlet {

    private static final UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/editProfile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String newUserName = req.getParameter("newUserName");

        String newName = req.getParameter("newName");

        String newEmail = req.getParameter("newEmail");

        String newImage = req.getParameter("newImage");

        String newPassword = req.getParameter("newPassword");

        String repeatedNewPassword = req.getParameter("repeatNewPassword");

        if(!newPassword.equals(repeatedNewPassword)){
            req.setAttribute("errormessage", "Passwords are not equal.");
            getServletContext().getRequestDispatcher("/pages/editProfile.jsp").forward(req, resp);
        }


        SessionPrincipalUser sessionUser = (SessionPrincipalUser) req.getSession().getAttribute("user");
        User updatedUser = User.builder()
                .setUserId(sessionUser.getId())
                .setUserName(newUserName)
                .setName(newName)
                .setEmail(newEmail)
                .setPhoto(newImage)
                .setPassword(newPassword)
                .build();


        userService.updateUser(updatedUser);

        req.setAttribute("username", sessionUser.getUsername());
        getServletContext().getRequestDispatcher("/user/account").forward(req, resp);
    }

}
