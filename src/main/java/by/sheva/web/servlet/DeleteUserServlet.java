package by.sheva.web.servlet;

import by.sheva.security.SessionPrincipalUser;
import by.sheva.service.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/delete")
public class DeleteUserServlet extends HttpServlet {

    private static final String USER = "user";

    private final UserServiceImpl userService = UserServiceImpl.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SessionPrincipalUser sessionPrincipalUser = (SessionPrincipalUser) getServletContext().getAttribute(USER);

        try{

            userService.deleteUser(sessionPrincipalUser.getId());
            resp.sendRedirect("/registration");

        }catch (Exception e){
            req.setAttribute("errormessage", "Something went wrong on our side.");
            getServletContext().getRequestDispatcher("/pages/error.jsp").forward(req, resp);
        }
    }
}
