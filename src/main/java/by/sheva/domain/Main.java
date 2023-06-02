package by.sheva.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        User user = User.builder()
                .setUserId(1)
                .setUserName("alifl")
                .setPassword("468468468135")
                .setEmail("WDKYwyGDI")
                .setPhoto("ADCKAEKCGAKEC")
                .build();

        System.out.println(user);

        Comment comment = Comment.builder()
                .setCommentId(1)
                .setMessage("sdvsvs")
                .setCreatedAt(new Date(System.currentTimeMillis()))
                .build();

        List<Comment> comments = new ArrayList<>();
        comments.add(comment);

        Post post = Post.builder()
                .setPostId(1)
                .setUser(user)
                .setDescription("sdvcsdvcs")
                .setImage("walhcuhc")
                .setCreatedAt(new Date(System.currentTimeMillis()))
                .setComments(comments)
                .build();

        System.out.println(post);
    }
}
