package objects;

import java.util.Objects;

/**
 * This class is used to describe a user
 */

public class User {
    private String mail;
    private String pwd;

    public User(String mail, String pwd) {
        this.mail = mail;
        this.pwd = pwd;
    }

    public String getMail() {
        return mail;
    }

    public String getPwd() {
        return pwd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(mail, user.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mail);
    }

    @Override
    public String toString() {
        return "User{" +
                "mail='" + mail + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
