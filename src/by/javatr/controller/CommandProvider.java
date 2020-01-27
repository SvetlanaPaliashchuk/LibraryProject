package by.javatr.controller;

import by.javatr.controller.command.Command;
import by.javatr.controller.command.CommandName;
import by.javatr.controller.command.impl.*;
import by.javatr.controller.command.impl.book.*;
import by.javatr.controller.command.impl.user.*;

import java.util.HashMap;
import java.util.Map;

final class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();

    CommandProvider(){
        repository.put(CommandName.SIGN_IN, new SignIn());
        repository.put(CommandName.REGISTER, new Register());
        repository.put(CommandName.ADD_BOOK, new AddBook());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
        repository.put(CommandName.DELETE_ALL_USERS, new DeleteAllUsers());
        repository.put(CommandName.DELETE_USER, new DeleteUser());
        repository.put(CommandName.GET_ALL_USERS, new GetAllUsers());
        repository.put(CommandName.GET_USER_BY_LOGIN, new GetUserByLogin());
        repository.put(CommandName.DELETE_BOOK, new DeleteBook());
        repository.put(CommandName.GET_ALL_BOOKS, new GetAllBooks());
        repository.put(CommandName.GET_BOOK_BY_TITLE, new GetBookByTitle());
        repository.put(CommandName.GET_BOOKS_BY_AUTHOR, new GetBooksByAuthor());
    }

    Command getCommand(String name){
        CommandName commandName;
        Command command;


        try{
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        }
        catch (IllegalArgumentException | NullPointerException e){
            command = repository.get(CommandName.WRONG_REQUEST);
        }

        return command;
    }
}
