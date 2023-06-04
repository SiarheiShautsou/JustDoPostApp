package by.sheva.domain;

import by.sheva.repository.user.UserRepository;
import by.sheva.repository.user.UserRepositoryImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Comment comment = Comment.builder()
                .setCommentId(1)
                .setMessage("sdvsvs")
                .setCreatedAt(new Date(System.currentTimeMillis()))
                .build();

        List<Comment> comments = new ArrayList<>();
        comments.add(comment);

        Post post = Post.builder()
                .setPostId(1)
//                .setUser(user)
                .setDescription("sdvcsdvcs")
                .setImage("walhcuhc")
                .setCreatedAt(new Date(System.currentTimeMillis()))
                .setComments(comments)
                .build();

        System.out.println(post);

        User user1 = User.builder()
                .setUserId(12)
                .setUserName("eavrsvrb")
                .setPassword("srbsdra")
                .setName("abrzrb")
                .setEmail("basrar")
                .setPhoto("arbtrr")
                .build();

        User user2 = UserRepositoryImpl.getInstance().updateObject(user1);
        System.out.println(user2);

    }


}
