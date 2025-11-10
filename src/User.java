import util.FileIO;
import util.TextUI;

import java.util.ArrayList;

public class User {
    String username;
    String password;

    int amountOfUsers;

    TextUI ui = new TextUI();
    FileIO io = new FileIO();
    ArrayList<String> user = new ArrayList<>();

    public User(){
    }

    public void createUser(){
        String chosenUsername = ui.promptText("Choose a username");
        String chosenPassword = ui.promptText("Choose a password");

        user.add(chosenUsername);
        user.add(chosenPassword);

        if(!chosenPassword.equals(chosenUsername)){
            io.saveData(user, chosenUsername, "User: " + chosenUsername);
        } else {
            ui.displayMsg("Your password cannot be the same as your username, try again");
            createUser();
        }
    }

    public void login(){
        String usernameInput = ui.promptText("Username: ");
        String passwordInput = ui.promptText("Password: ");

        String[] userData = io.readData(usernameInput, 2);

        if(usernameInput.equals(userData[0]) && passwordInput.equals(userData[1])){
            ui.displayMsg("You have logged in successfully");
        } else{
            ui.displayMsg("Login unsuccessful: You have entered the wrong details");
        }
    }
}
