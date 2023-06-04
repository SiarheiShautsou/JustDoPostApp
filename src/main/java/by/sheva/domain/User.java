package by.sheva.domain;

import java.util.Objects;

public class User {

    private Integer userId ;
    private String userName;
    private String password;
    private String name;
    private String photo;
    private String email;

    public User() {
    }

    public static UserBuilder builder(){
        return new User().new UserBuilder();
    }

    public class UserBuilder {

        public UserBuilder() {
        }

        public UserBuilder setUserId(Integer userId) {
            User.this.userId = userId;
            return this;
        }

        public UserBuilder setUserName(String userName) {
            User.this.userName = userName;
            return this;
        }

        public UserBuilder setPassword(String password) {
            User.this.password = password;
            return this;
        }

        public UserBuilder setName(String name) {
            User.this.name = name;
            return this;
        }

        public UserBuilder setPhoto(String photo) {
            User.this.photo = photo;
            return this;
        }

        public UserBuilder setEmail(String email) {
            User.this.email = email;
            return this;
        }

        public User build(){
            return User.this;
        }

    }

    public Integer getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getPhoto() {
        return photo;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(userName, user.userName) && Objects.equals(password, user.password) && Objects.equals(name, user.name) && Objects.equals(photo, user.photo) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, password, name, photo, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", photo='" + photo + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
