package nl.han.oose.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String user;

    @Column
    private String password;

    public User() { }

    public User(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getUser() {
        return user;
    }
    public String getPassword() {
        return password;
    }
    public int getId() {
        return id;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
