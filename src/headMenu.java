import media.Media;
import util.*;
import java.util.ArrayList;
import java.util.Scanner;

public class headMenu {
    public FileIO io = new FileIO();
    public TextUI ui = new TextUI();
    public ArrayList<String> movies = new ArrayList<>();
    private User currentUser;
    private Scanner input = new Scanner(System.in);

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
            System.out.println((i + 1) + ". " + savedList.get(i).getTitle());
        }
    }

    //brugerens sete medier
    public void showWatchedMedia() {
        System.out.println("\n--- Dine sete film og serier ---");
        Arraylist<Media> watchedList = currentUser.getWatchedMedia();
        if (watchedList.isEmpty()) {
            System.out.println("Du har endnu ikke set nogen medier.");
            return;
        }
        for (int i = 0; i < watchedList.size(); i++) {
            System.out.println((i + 1) + ". " + watchedList.get(i).getTitle());
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
                System.out.println(found.getTitle() + " er nu gemt til senere.");
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
                !currentUser.getWatchedMedia().add(found);
                System.out.println("Dette medie er allerede markeret som set. ");
            }
        } else {
            System.out.println("Mediet blev ikke fundet.");
        }
    }
    // til at finde et medie i allMedia
    private Media findMediaByTitle(String title) {
        for (Media m : alMedia) {
            if (m.getTitle().equalsIgnoreCase(title)) {
                return m;
            }
        }
        return null;
    }
}


    public void searchMedia(){
        ArrayList<String> movies = loadMovies();
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

    public ArrayList<String> loadMovies(){
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
            if (movie.getCategories().contains(category)){
                categoryList.add(movie.getTitleName());
            }
        }

        for (String title :  categoryList){
            System.out.println(title);
        }
    }



}
