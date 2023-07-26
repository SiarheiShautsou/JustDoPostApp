package by.sheva.mapper;

import by.sheva.domain.User;
import by.sheva.security.SessionPrincipalUser;
import by.sheva.web.request.UserRequest;

public class UserMapper {

    public static UserRequest mapUserToRequest(User user){

        UserRequest userRequest = new UserRequest();

        userRequest.setId(user.getUserId());
        userRequest.setUsername(user.getUserName());
        userRequest.setName(user.getName());
        userRequest.setEmail(user.getEmail());
        userRequest.setPhoto(user.getPhoto());

        return userRequest;
    }

    public static SessionPrincipalUser mapToSessionPrincipalUser(User user){

        return SessionPrincipalUser.builder()
                .setId(user.getUserId())
                .setName(user.getName())
                .setUsername(user.getUserName())
                .setEmail(user.getEmail())
                .setPhoto(user.getPhoto())
                .build();
    }

    public static User toUser(SessionPrincipalUser sessionPrincipalUser){
        return User.builder()
                .setUserId(sessionPrincipalUser.getId())
                .setUserName(sessionPrincipalUser.getUsername())
                .setName(sessionPrincipalUser.getName())
                .setEmail(sessionPrincipalUser.getEmail())
                .setPhoto(sessionPrincipalUser.getPhoto())
                .build();
    }
}
