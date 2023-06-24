package by.sheva.security;

import java.util.Objects;

public class SessionPrincipalUser {

    private int id;

    private String username;

    private String name;

    private String email;

    private String photo;

    public SessionPrincipalUser() {
    }

    public static SessionPrincipalBuilder builder() {

        return new SessionPrincipalUser().new SessionPrincipalBuilder();
    }

    public class SessionPrincipalBuilder {

        public SessionPrincipalBuilder() {
        }

        public SessionPrincipalBuilder setId(int id) {
            SessionPrincipalUser.this.id = id;
            return this;
        }

        public SessionPrincipalBuilder setUsername(String username) {
            SessionPrincipalUser.this.username = username;
            return this;
        }

        public SessionPrincipalBuilder setName(String name) {
            SessionPrincipalUser.this.name = name;
            return this;
        }

        public SessionPrincipalBuilder setEmail(String email) {
            SessionPrincipalUser.this.email = email;
            return this;
        }

        public SessionPrincipalBuilder setPhoto(String photo) {
            SessionPrincipalUser.this.photo = photo;
            return this;
        }

        public SessionPrincipalUser build() {
            return SessionPrincipalUser.this;
        }


    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoto() {
        return photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionPrincipalUser that = (SessionPrincipalUser) o;
        return id == that.id && Objects.equals(username, that.username) && Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(photo, that.photo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, name, email, photo);
    }

    @Override
    public String toString() {
        return "SessionPrincipalUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
