package by.javatr.view;

import by.javatr.controller.Controller;
import by.javatr.entity.User;
import by.javatr.scanner.DataScanner;

public class Menu {
    private Controller controller = new Controller();
    public void start(){
        System.out.println("Choose the option:\n1 - sing in\n2 - register");
        int number = DataScanner.enterPositiveIntFromConsole();
        String info;
        switch(number){
            case 1:
                System.out.println("Please enter login and password in the following form:\nlogin(space)password");
                info = DataScanner.enterLineFromConsole();
                System.out.println(controller.executeTask("sign_in " + info));
                break;
            case 2:
                System.out.println("Please enter registration info in the following form:\nname(space)surname(space)login(space)password(space)role");
                info = DataScanner.enterLineFromConsole();
                System.out.println(controller.executeTask("register " + info));
                break;
        }

    }
}
