import util.FileIO;
import util.TextUI;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class App {

    static TextUI ui = new TextUI();
    FileIO io = new FileIO();

    public App() {

    }

    public static void startApp() {
        ArrayList<String> loginOrCreateUser = new ArrayList<>();
        User user = new User();

        String dirName = "Users";

        Path path = Paths.get(dirName);
        if (Files.notExists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        loginOrCreateUser.add("Login");
        loginOrCreateUser.add("Create User");

        while (true){
            boolean loginSuccesful = false;
            ArrayList<String> choice;
            while(true){
                try{
                    choice = ui.promptChoice(loginOrCreateUser, 1, "Do you want to login or create a user?");
                    break;
                }
                catch (Exception e){
                    ui.displayMsg("Invalid input");
                }
            }
            String chosen = choice.get(0);
            switch(chosen){
                case "Login":
                    loginSuccesful = user.login();
                    break;
                case "Create User":
                    loginSuccesful = user.createUser();
                    break;
                default:
                    ui.displayMsg("Invalid choice!");
            }
            if (loginSuccesful){
                headMenu.startMenu();
                break;
            }
        }
    }
}
