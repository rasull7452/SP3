import util.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Array;
import java.util.ArrayList;
import media.Media;

public class User {
    String username;
    String password;

    ArrayList<String> loggedInUser = new ArrayList<>();

    int amountOfUsers;

    TextUI ui = new TextUI();
    FileIO io = new FileIO();
    ArrayList<String> user = new ArrayList<>();
    ArrayList<String> savedMovies = new ArrayList<>();
    ArrayList<String> watchedMovies = new ArrayList<>();

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

    public boolean createUser(){
        String chosenUsername = ui.promptText("Choose a username");
        String chosenPassword = ui.promptText("Choose a password");

        user.add(chosenUsername);
        user.add(chosenPassword);

        String dirName1 = "Users//" + chosenUsername;

        Path path1 = Paths.get(dirName1);

        if(chosenPassword.equals(chosenUsername)){

            ui.displayMsg("Your password cannot be the same as your username");
            createUser();

        } else if(!Files.notExists(path1)) {

            ui.displayMsg("This user already exists, use a different username");
            createUser();

        } else{
            String localUserPath = "Users//" + chosenUsername + "//";
            String dirName = "Users//" + chosenUsername;

            Path path = Paths.get(dirName);
            if(Files.notExists(path)){
                try {
                    Files.createDirectory(path);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            io.saveData(user, localUserPath + "user.txt", "User: " + chosenUsername);
            io.saveData(savedMovies, localUserPath + "saved_movies.txt", chosenUsername + ": Saved Movies");
            io.saveData(watchedMovies, localUserPath + "watched_movies.txt", chosenUsername + ": Watched Movies");
        }
        ui.displayMsg("Login");
        return login();
    }

    public boolean login(){
        String usernameInput = ui.promptText("Username: ");
        String passwordInput = ui.promptText("Password: ");

        String[] userData = io.readData("Users//" + usernameInput + "//" + "user.txt", 2);

        if(usernameInput.equals(userData[0]) && passwordInput.equals(userData[1])){
            ui.displayMsg("You are logged in as " + usernameInput);
            loggedInUser.add(usernameInput);
            loggedInUser.add(passwordInput);
            return true;

        } else{
            ui.displayMsg("Login unsuccessful: You have entered the wrong details");
            return false;
        }
    }

    public ArrayList<String> getLoggedInUser() {
        return loggedInUser;
    }

    public ArrayList<String> getSavedMovies() {
        return savedMovies;
    }

    public ArrayList<String> getWatchedMovies() {
        return watchedMovies;
    }
}
