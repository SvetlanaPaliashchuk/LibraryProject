package by.javatr.entity;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = -4899639796230566611L;

    private int id;
    private String firstName;
    private String surname;
    private String login;
    private String password;
    private Role role;

    public User() {
    }

    public User(String firstName, String surname, String login, String password, Role role) {
        this.firstName = firstName;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((surname == null) ? 0 : surname.hashCode());
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((role == null) ? 0 : 1);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        return (id == other.getId()) && ((firstName != null && firstName.equals(other.getFirstName())))
                && ((surname != null && surname.equals(other.getSurname())))
                && ((login != null && login.equals(other.getLogin())))
                && ((password != null && password.equals(other.getPassword())))
                && (role == other.getRole() || (role != null && role.equals(other.getRole())));
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [id=" + id + ", firstName=" + firstName + ", surname=" + surname + ", login=" + login + ", password="
                + password + ", role=" + role + "]";
    }


}
