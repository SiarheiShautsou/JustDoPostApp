package by.sheva.web.servlet;

import by.sheva.domain.User;
import by.sheva.service.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    public static final String USERNAME = "userName";
    public static final String PASSWORD = "password";
    public static final String NAME = "name";
    public static final String PHOTO = "photo";
    public static final String EMAIL = "email";

    private final UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("pages/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userName = req.getParameter(USERNAME);

        String password = req.getParameter(PASSWORD);

        String name = req.getParameter(NAME);

        String photo = req.getParameter(PHOTO);

        String email = req.getParameter(EMAIL);

        Optional<User> userByUsername;

        try{
            userByUsername = userService.findUserByUsername(userName);

            if(userByUsername.isEmpty()){

                User user = User.builder()
                        .setUserName(userName)
                        .setPassword(password)
                        .setName(name)
                        .setPhoto(photo)
                        .setEmail(email)
                        .build();

                userService.creatUser(user);
                resp.sendRedirect("/login");

            }else{
                req.setAttribute("message", "User with this UserName already exists! Please, try with another name!");
                getServletContext().getRequestDispatcher("/pages/register.jsp").forward(req, resp);
            }


        }catch (Exception e){
            req.setAttribute("errormessage", "Something went wrong on our side.");
            getServletContext().getRequestDispatcher("/pages/error.jsp").forward(req, resp);
        }


    }
}
