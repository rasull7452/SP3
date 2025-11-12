package util;
import util.FileIO;
import util.TextUI;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import media.Media;

public class User {
    String username;
    String password;

    int amountOfUsers;

    TextUI ui = new TextUI();
    FileIO io = new FileIO();
    ArrayList<String> user = new ArrayList<>();

    private ArrayList<Media> savedMedia = new ArrayList<>();
    private ArrayList<Media>watchedMedia = new ArrayList<>();

    //getter metode til headMenu
    public ArrayList<Media>getSavedMedia() {
        return savedMedia;
    }

    public ArrayList<Media> getWatchedMedia(){
        return watchedMedia;
    }

    public User(){
    }

    public void createUser(){
        String chosenUsername = ui.promptText("Choose a username");
        String chosenPassword = ui.promptText("Choose a password");

        user.add(chosenUsername);
        user.add(chosenPassword);

        String dirName1 = "Users//" + chosenUsername;

        Path path1 = Paths.get(dirName1);

        if(chosenPassword.equals(chosenUsername)){

            ui.displayMsg("Your password cannot be the same as your username");

        } else if(!Files.notExists(path1)) {

            ui.displayMsg("This user already exists, use a different username");

        } else{
            String localUserPath = "Users//" + chosenUsername + "//" + chosenUsername + ".txt";
            String dirName = "Users//" + chosenUsername;

            Path path = Paths.get(dirName);
            if(Files.notExists(path)){
                try {
                    Files.createDirectory(path);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            io.saveData(user, localUserPath, "User: " + chosenUsername);
        }
    }

    public void login(){
        String usernameInput = ui.promptText("Username: ");
        String passwordInput = ui.promptText("Password: ");

        String[] userData = io.readData(usernameInput, 2);

        if(usernameInput.equals(userData[0]) && passwordInput.equals(userData[1])){
            ui.displayMsg("You have logged in successfully");
            String loggedInUsername = usernameInput;
            String loggedInPassword = passwordInput;
        } else{
            ui.displayMsg("Login unsuccessful: You have entered the wrong details");
        }
    }
}
