import util.User;
import media.Media;
import util.*;
import java.util.ArrayList;
import java.util.Scanner;

public class headMenu {
    public FileIO io = new FileIO();
    public TextUI ui = new TextUI();
    public ArrayList<Media> movies = new ArrayList<>();
    private User currentUser;
    private Scanner input = new Scanner(System.in);
    private ArrayList<Media> allMedia = new ArrayList<>();

    public headMenu(User currentUser) {
        this.currentUser = currentUser;

    }

    //Brugerens gemte medier

    public void showSavedMedia() {
        System.out.println("\n--- Dine gemte film og serier ---");
        ArrayList<Media> savedList = currentUser.getSavedMedia();

        if (savedList.isEmpty()) {
            System.out.println("Du har ingen gemte medier endnu.");
            return;
        }
        for (int i = 0; i < savedList.size(); i++) {
            System.out.println((i + 1) + ". " + savedList.get(i).getTitleName());
        }
    }

    //brugerens sete medier
    public void showWatchedMedia() {
        System.out.println("\n--- Dine sete film og serier ---");
        ArrayList<Media> watchedList = currentUser.getWatchedMedia();
        if (watchedList.isEmpty()) {
            System.out.println("Du har endnu ikke set nogen medier.");
            return;
        }
        for (int i = 0; i < watchedList.size(); i++) {
            System.out.println((i + 1) + ". " + watchedList.get(i).getTitleName());
        }
    }

    // Et medie til brugerens gemte liste
    public void addMediaToSaved() {
        System.out.println("\nIndtast titlen på mediet, du vil gemme: ");
        String title = input.nextLine();
        Media found = findMediaByTitle(title);
        if (found != null) {
            if (!currentUser.getSavedMedia().contains(found)) {
                currentUser.getSavedMedia().add(found);
                System.out.println(found.getTitleName() + " er nu gemt til senere.");
            } else {
                System.out.println("Dette medie er allerede gemt.");
            }
        } else {
            System.out.println("Mediet blev ikke fundet.");
        }
    }

    public void addMediaToWatched() {
        System.out.println("\nIndtast titlen på mediet, du har set: ");
        String title = input.nextLine();
        Media found = findMediaByTitle(title);
        if (found != null) {
            if (!currentUser.getWatchedMedia().contains(found)) {
                currentUser.getWatchedMedia().add(found);
                System.out.println("Dette medie er allerede markeret som set. ");
            }
        } else {
            System.out.println("Mediet blev ikke fundet.");
        }
    }
    // til at finde et medie i allMedia
    private Media findMediaByTitle(String title) {
        for (Media m : allMedia) {
            if (m.getTitleName().equalsIgnoreCase(title)) {
                return m;
            }
        }
        return null;
    }



    public void searchMedia(){
        ArrayList<Media> movies = loadMovies();
        boolean titleFound = false;
        while(!titleFound){
            String title = ui.promptText("Please enter a title: ");
            if(title.isEmpty() || title.equals("0")){
                break;
            }
            else if (movies.contains(title)){
                movieOptions(title);
                titleFound = true;
            }
            else {
                System.out.println("Not a valid title");
            }
        }
    }

    public ArrayList<String> loadMoviepublic; ArrayList<Media> loadMovies(){
        return movies;
    }

    public void movieOptions(String title){
        ui.displayMsg("*** Movie Options ***");
        ui.displayMsg("1. Play movie");
        boolean saved = checkIfSaved(title);
        if (!saved) {
            ui.displayMsg("2. Add to saved");
        }
        else {
            ui.displayMsg("2. Remove from saved");
        }
        ui.displayMsg("0. Go back");

        int decision = ui.promptNumeric("Enter an integer: ");

        if (decision == 0) {
            ui.displayMsg("Going back");
        } else if  (decision == 1) {
            playTitle(title);
        } else if (decision == 2) {
            if (saved) {
                removeTitle(title);
            }
            else {
                saveTitle(title);
            }
        } else {
            ui.displayMsg("Invalid option");
        }
    }

    public void searchCategory(){
        loadMovies();
        String category = ui.promptText("Please enter a category: ");
        ArrayList<String> categoryList = new ArrayList<>();
        for (Media movie : movies){
            if (movie.getCategory().equalsIgnoreCase(category)) {
                categoryList.add(movie.getTitleName());
            }
        }

        for (String title :  categoryList){
            System.out.println(title);
        }
    }

    private boolean checkIfSaved(String title){
        for (Media m : currentUser.getSavedMedia()){
            if (m.getTitleName().equalsIgnoreCase(title)){
                return true;
            }
        }
        return false;
    }

    private void playTitle(String title){
        System.out.println("Playing " + title + "...");
    }
    private void saveTitle(String title){
        Media m = findMediaByTitle(title);
        if (m != null && !currentUser.getSavedMedia().contains(m)) {
            currentUser.getSavedMedia().add(m);
            System.out.println(title + " er nu gemt.");
        }
    }
    private void removeTitle(String title){
        Media m = findMediaByTitle(title);
        if (m != null && currentUser.getSavedMedia().contains(m)) {
            currentUser.getSavedMedia().remove(m);
            System.out.println(title + " er nu fjernet fra gemte medier.");
        }
    }

}
