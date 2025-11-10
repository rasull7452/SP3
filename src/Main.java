public class Main {
    public static void main(String[] args){
        App app = new App();
        app.startApp();

        User u = new User();
        u.createUser();
        u.login();
    }
}
