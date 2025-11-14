import media.Media;
import util.*;
import media.*;

import java.io.File;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class HeadMenu {
    public static FileIO io = new FileIO();
    public static TextUI ui = new TextUI();
    // private static User user = new User();
    private static User currentUser = new User();
    private Scanner input = new Scanner(System.in);
    private static ArrayList<String> watchedMovies = new ArrayList<>();
    private static ArrayList<String> savedMovies = new ArrayList<>();
    private static ArrayList<Media> allMedia = new ArrayList<>();
    public static ArrayList<String> movies = new ArrayList<>();

    public HeadMenu() {

    }

    //Brugerens gemte medier

    public static void showSavedMedia() {
        ArrayList<String> loggedInUser = App.user.getLoggedInUser();

        File file = new File("Users//" + loggedInUser.get(0) + "//" + "saved_movies.txt");

        if(!file.exists()){

            ui.displayMsg("Du har ikke nogen gemte film");

        } else {

            savedMovies = io.readData("Users//" + loggedInUser.get(0) + "//" + "saved_movies.txt");

            ui.displayMsg("---- Saved Movies ----");
            for(String savedMovie : savedMovies){
                ui.displayMsg(savedMovie);
            }
        }
    }

    public static void showHeadMenu(){
        ArrayList<String> headMenuOptions = new ArrayList<>();

        headMenuOptions.add("Søg film");
        headMenuOptions.add("Søg film ud fra kategori");
        headMenuOptions.add("Se liste over sete film");
        headMenuOptions.add("Se liste over gemte film");

        ArrayList<String> choice = ui.promptChoice(headMenuOptions, 1, "Main Menu");

        String chosen = choice.getFirst();

        if(chosen.equals("Søg film")){
            searchMedia();
        } else if(chosen.equals("Søg film ud fra kategori")){
            searchCategory();
        }
        else if(chosen.equals("Se liste over sete film")){
            showWatchedMedia();
        }
        else if(chosen.equals("Se liste over gemte film")){
            showSavedMedia();
        }
        else {
            ui.displayMsg("Press a number between 1 and 4");
            showHeadMenu();
        }
    }

    //brugerens sete medier
    public static void showWatchedMedia() {
        ArrayList<String> loggedInUser = App.user.getLoggedInUser();

        File file = new File("Users//" + loggedInUser.get(0) + "//" + "watched_movies.txt");

        if(!file.exists()){

            ui.displayMsg("Du har ikke nogen sete film");

        } else {

            watchedMovies = io.readData("Users//" + loggedInUser.get(0) + "//" + "watched_movies.txt");

            ui.displayMsg("---- Watched Movies ----");
            for(String watchedMovie : watchedMovies){
                ui.displayMsg(watchedMovie);
            }
        }
    }

    // Et medie til brugerens gemte liste
    public static void addMediaToSaved(String title) {
        ArrayList<String> loggedInUser = App.user.getLoggedInUser();

        File file = new File("Users//" + loggedInUser.get(0) + "//" + "saved_movies.txt");

        if(!file.exists()){

            savedMovies.add(title);
            io.saveData(savedMovies,"Users//" + loggedInUser.get(0) + "//" + "saved_movies.txt",  loggedInUser.get(0) + ": Saved Movies");

        } else {

            savedMovies = io.readData("Users//" + loggedInUser.get(0) + "//" + "saved_movies.txt");
            savedMovies.add(title);

            io.saveData(savedMovies,"Users//" + loggedInUser.get(0) + "//" + "saved_movies.txt",  loggedInUser.get(0) + ": Saved Movies");

        }

        ui.displayMsg("You have added " + title + " to your saved movies");
        showHeadMenu();
    }

    public static void addMediaToWatched(String title) {
        ArrayList<String> loggedInUser = App.user.getLoggedInUser();

        File file = new File("Users//" + loggedInUser.get(0) + "//" + "watched_movies.txt");

        if(!file.exists()){

            watchedMovies.add(title);
            io.saveData(watchedMovies,"Users//" + loggedInUser.get(0) + "//" + "watched_movies.txt",  loggedInUser.get(0) + ": Watched Movies");

        } else {

            watchedMovies = io.readData("Users//" + loggedInUser.get(0) + "//" + "watched_movies.txt");
            watchedMovies.add(title);

            io.saveData(watchedMovies,"Users//" + loggedInUser.get(0) + "//" + "watched_movies.txt",  loggedInUser.get(0) + ": Watched Movies");

        }
        ui.displayMsg(title + " has been added to your watched movies list");
        showHeadMenu();
    }

    // til at finde et medie i allMedia
    private static Media findMediaByTitle(String title) {
        for (Media m : allMedia) {
            if (m.getTitleName().equalsIgnoreCase(title)) {
                return m;
            }
        }
        return null;
    }



    public static void searchMedia(){
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

    public static ArrayList<String> loadMovies(){
        ArrayList<String> movieData = io.readData("src//data//film.csv");
        ArrayList<String> movieTitles = new ArrayList<>();
        for(String m : movieData){
            String[] values =  m.split(";");
            Movie movie = new Movie(
                    values[0].trim(), Integer.parseInt(values[1].trim()), values[2].trim(), parseDoubleComma(values[3].trim()));
            allMedia.add(movie);
            movieTitles.add(values[0].trim());
        }
        ui.displayMsg(movieTitles.toString());
        return movieTitles;
    }

    public static double parseDoubleComma(String numberToParse){
        NumberFormat nf = NumberFormat.getInstance(Locale.GERMANY); // or Locale.FRANCE, etc.
        try {
            Number number = nf.parse(numberToParse);
            return number.doubleValue();
            // Output: 3.14
        } catch (ParseException e) {
            System.err.println("Cannot parse: " + numberToParse);
        }
        return 0;
    }

    public static void movieOptions(String title){
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
                addMediaToSaved(title);
            }
        } else {
            ui.displayMsg("Invalid option");
        }
    }

    public static void searchCategory(){
        loadMovies();
        String category = ui.promptText("Please enter a category: ");
        ArrayList<String> categoryList = new ArrayList<>();
        for (Media movie : allMedia){
            if (movie.getCategory().equalsIgnoreCase(category)) {
                categoryList.add(movie.getTitleName());
            }
        }

        for (String title :  categoryList){
            System.out.println(title);
        }
    }

    private static boolean checkIfSaved(String title){
        for (Media m : currentUser.getSavedMedia()){
            if (m.getTitleName().equalsIgnoreCase(title)){
                return true;
            }
        }
        return false;
    }

    private static void playTitle(String title){
        System.out.println("Playing " + title + "...");
        addMediaToWatched(title);
    }

    private static void saveTitle(String title){
        Media m = findMediaByTitle(title);
        if (m != null && !currentUser.getSavedMedia().contains(m)) {
            currentUser.getSavedMedia().add(m);
            System.out.println(title + " er nu gemt.");
        }
    }
    private static void removeTitle(String title){
        Media m = findMediaByTitle(title);
        if (m != null && currentUser.getSavedMedia().contains(m)) {
            currentUser.getSavedMedia().remove(m);
            System.out.println(title + " er nu fjernet fra gemte medier.");
        }
    }

}
