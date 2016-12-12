package org.enset.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name="users")
public class User implements Serializable {
    @Id
    private String username;
    private String password;
    private boolean actived;
    @ManyToMany
    @JoinTable(name="USERS_ROLES")
    private Collection<Role> roles;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean isActived() {
        return actived;
    }

    public void setActived(boolean actived) {
        this.actived = actived;
    }

    public User(String username, String password, boolean actived) {

        this.username = username;
        this.password = password;
        this.actived = actived;
    }

    public User() {

    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
