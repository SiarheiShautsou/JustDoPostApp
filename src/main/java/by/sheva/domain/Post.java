package by.sheva.domain;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Post {

    private Integer postId;
    private User user;
    private String image;
    private Date createdAt;
    private String description;
    private List<Comment> comments;

    public Post() {
    }

    public static PostBuilder builder(){
        return new Post().new PostBuilder();
    }
    public class PostBuilder {

        public PostBuilder setPostId(Integer postId) {
            Post.this.postId = postId;
            return this;
        }

        public PostBuilder setUser(User user) {
            Post.this.user = user;
            return this;
        }

        public PostBuilder setImage(String image) {
            Post.this.image = image;
            return this;
        }

        public PostBuilder setCreatedAt(Date createdAt) {
            Post.this.createdAt = createdAt;
            return this;
        }

        public PostBuilder setDescription(String description) {
            Post.this.description = description;
            return this;
        }

        public PostBuilder setComments(List<Comment> comments) {
            Post.this.comments = comments;
            return this;
        }

        public Post build(){
            return Post.this;
        }
    }


    public Integer getPostId() {
        return postId;
    }

    public User getUser() {
        return user;
    }

    public String getImage() {
        return image;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getDescription() {
        return description;
    }

    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(postId, post.postId) && Objects.equals(user, post.user) && Objects.equals(image, post.image) && Objects.equals(createdAt, post.createdAt) && Objects.equals(description, post.description) && Objects.equals(comments, post.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, user, image, createdAt, description, comments);
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", user=" + user +
                ", image='" + image + '\'' +
                ", createdAt=" + createdAt +
                ", description='" + description + '\'' +
                ", comments=" + comments +
                '}';
    }
}
