package com.mariia.syne.splitwise.model;

import javax.persistence.*;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_users;

    private String first_name;

    private String last_name;

    private String login;

    private String password;

    private String role;

    @ManyToOne
    @JoinColumn(name = "id_group")
    private Groups id_group;

    public Users() {
    }

    public Users(String first_name, String last_name, String login, String password, String role, Groups id_group) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.login = login;
        this.password = password;
        this.role = role;
        this.id_group = id_group;
    }

    public Users(Integer id_users, String first_name, String last_name, String login, String password, String role, Groups id_group) {
        this.id_users = id_users;
        this.first_name = first_name;
        this.last_name = last_name;
        this.login = login;
        this.password = password;
        this.role = role;
        this.id_group = id_group;
    }


    public void setId_user(Integer users) {
        this.id_users = users;
    }

    public Integer getId_user() {
        return id_users;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Groups getId_group() {
        return id_group;
    }

    public void setId_group(Groups id_group) {
        this.id_group = id_group;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id_user=" + id_users +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", id_group='" + id_group + '\'' +
                '}';
    }
}
