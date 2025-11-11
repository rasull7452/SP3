import util.FileIO;
import util.TextUI;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class App {

    TextUI ui = new TextUI();
    FileIO io = new FileIO();

    public App(){

    }

    public void startApp(){
        User user = new User();
        ArrayList<String> loginOrCreateUser = new ArrayList<>();

        String dirName = "Users";

        Path path = Paths.get(dirName);
        if(Files.notExists(path)){
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        loginOrCreateUser.add("Login");
        loginOrCreateUser.add("Create User");


        if(ui.promptChoice(loginOrCreateUser, 1, "Do you want to login or create a user?").equals(loginOrCreateUser.get(0))){
            user.login();
        }

    }
}
