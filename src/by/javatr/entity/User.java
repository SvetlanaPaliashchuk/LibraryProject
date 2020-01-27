package by.javatr.entity;

import java.io.Serializable;

public class User implements Serializable {
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


    public static User initializeUser(String str) {
        User user = new User();
        if (str != null) {
            String[] parts = str.split(" ");
            for (String part : parts) {
                if (part != null) {
                    if (part.contains("id=")) {
                        String id = part.substring(part.indexOf("=") + 1, part.length() - 1);
                        user.setId(Integer.parseInt(id));
                    }
                    if (part.contains("firstName=")) {
                        String name = part.substring(part.indexOf("=") + 1, part.length() - 1);
                        user.setFirstName(name);
                    }
                    if (part.contains("surname=")) {
                        String surname = part.substring(part.indexOf("=") + 1, part.length() - 1);
                        user.setSurname(surname);
                    }
                    if (part.contains("login=")) {
                        String login = part.substring(part.indexOf("=") + 1, part.length() - 1);
                        user.setLogin(login);
                    }
                    if (part.contains("password=")) {
                        String password = part.substring(part.indexOf("=") + 1, part.length() - 1);
                        user.setPassword(password);
                    }
                    if (part.contains("role=")) {
                        String role = part.substring(part.indexOf("=") + 1, part.length() - 1);
                        if (role.equals(Role.READER.toString()))
                            user.setRole(Role.READER);
                        else user.setRole(Role.ADMIN);
                    }
                }
            }
        }
        return user;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
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
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [id=" + id + ", firstName=" + firstName + ", surname=" + surname + ", login=" + login + ", password="
                + password + ", role="  + role + "]";
    }


}
