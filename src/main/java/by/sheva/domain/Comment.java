package by.sheva.domain;

import java.util.Date;
import java.util.Objects;

public class Comment {

    private Integer commentId;
    private String message;
    private Post post;
    private User user;
    private Date createdAt;

    public Comment() {
    }

    public static CommentBuilder builder(){
        return new Comment().new CommentBuilder();
    }
    class CommentBuilder {
        public CommentBuilder setCommentId(Integer commentId) {
            Comment.this.commentId = commentId;
            return this;
        }

        public CommentBuilder setMessage(String message) {
            Comment.this.message = message;
            return this;
        }

        public CommentBuilder setPost(Post post) {
            Comment.this.post = post;
            return this;
        }

        public CommentBuilder setUser(User user) {
            Comment.this.user = user;
            return this;
        }

        public CommentBuilder setCreatedAt(Date createdAt) {
            Comment.this.createdAt = createdAt;
            return this;
        }

        public Comment build(){
            return Comment.this;
        }
    }

    public Integer getCommentId() {
        return commentId;
    }

    public String getMessage() {
        return message;
    }

    public Post getPost() {
        return post;
    }

    public User getUser() {
        return user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(commentId, comment.commentId) && Objects.equals(message, comment.message) && Objects.equals(post, comment.post) && Objects.equals(user, comment.user) && Objects.equals(createdAt, comment.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, message, post, user, createdAt);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", message='" + message + '\'' +
                ", post=" + post +
                ", user=" + user +
                ", createdAt=" + createdAt +
                '}';
    }
}
