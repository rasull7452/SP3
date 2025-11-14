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
    public static User user = new User();

    public App() {

    }

    public static void startApp() {
        ArrayList<String> loginOrCreateUser = new ArrayList<>();
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

        ArrayList<String> choice = ui.promptChoice(loginOrCreateUser, 1, "Do you want to login or create a user?");

        String chosen = choice.getFirst();

        if(chosen.equals("Login")){
            user.login();
        } else if(chosen.equals("Create User")){
            user.createUser();
        } else {
            ui.displayMsg("Press 1 or 2");
            startApp();
        }
    }
}
