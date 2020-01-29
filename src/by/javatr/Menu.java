package by.javatr;

import by.javatr.controller.Controller;
import by.javatr.scanner.DataScanner;
import by.javatr.service.exception.ServiceException;

public class Menu {
    private Controller controller = new Controller();
    private String info;


    public void start() {
        String login = signInOrRegister();
            if (isAdmin(login))
                adminMenu();
            else readerMenu();
    }

    private boolean isAdmin(String str) {
        String result = "";
        try {
            result = controller.executeTask("get_user_by_login " + str);
            if (result.contains("role=ADMIN")) return true;
        } catch (ServiceException e) {
            return false;
        }
        return false;
    }

    private String signInOrRegister() {
        System.out.println("Choose the option:\n1 - sing in\n2 - register");
        int number = DataScanner.enterPositiveIntFromConsole();
        String login = "";
        switch (number) {
            case 1:
                signIn();
                login = info.substring(0, info.indexOf(" "));
                break;
            case 2:
                register();
                String[] parts = info.split(" ");
                login = parts[2];
                break;
        }
        return login;
    }

    private void signIn() {
        System.out.println("Please enter login and password in the following form:" +
                "\nlogin(space)password");
        info = DataScanner.enterLineFromConsole();
        try {
            System.out.println(controller.executeTask("sign_in " + info));
        } catch (ServiceException e) {
            System.out.println("Error during login procedure. Please try again");
            start();
        }
    }

    private void register() {
        System.out.println("Please enter registration info in the following form:" +
                "\nname(space)surname(space)login(space)password(space)role");
        info = DataScanner.enterLineFromConsole();
        try {
            System.out.println(controller.executeTask("register " + info));
        } catch (ServiceException e) {
            System.out.println("Error during registration procedure. Please try again");
            start();
        }
    }

    private void adminMenu() {
        System.out.println("admin menu");
    }


    private void readerMenu() {
        System.out.println("reader menu");

    }
}
