package by.sheva.mapper;

import by.sheva.domain.User;
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

}
