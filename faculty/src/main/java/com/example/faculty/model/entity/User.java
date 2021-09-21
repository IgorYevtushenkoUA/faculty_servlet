package com.example.faculty.model.entity;

import com.example.faculty.dao.Identified;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

    private Integer id;
    private String firstName;
    private String secondName;
    private String lastName;
    private String email;
    private String password;
    private int roleId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getRoleId() == user.getRoleId() && getId().equals(user.getId()) && getFirstName().equals(user.getFirstName()) && getSecondName().equals(user.getSecondName()) && getLastName().equals(user.getLastName()) && getEmail().equals(user.getEmail()) && getPassword().equals(user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getSecondName(), getLastName(), getEmail(), getPassword(), getRoleId());
    }

    public User() {
    }

    public User(User user){
        this.id=user.getId();
        this.firstName = user.getFirstName();
        this.secondName = user.getSecondName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.setRoleId(user.getRoleId());
    }

    public User(String firstName, String secondName, String lastName, String email, String password, int roleId) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.setRoleId(roleId);
    }

    public User(Integer id, String firstName, String secondName, String lastName, String email, String password, int roleId) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.setRoleId(roleId);
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roleId=" + roleId +
                '}';
    }
}
