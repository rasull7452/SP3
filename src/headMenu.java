import util.*;
import java.util.ArrayList;

public class headMenu {
    public FileIO io = new FileIO();
    public TextUI ui = new TextUI();
    public ArrayList<String> movies = new ArrayList<>();

    public headMenu(){

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
                showOptions(title);
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
        System.out.println("*** Movie Options ***");
        System.out.println("1. Play movie");
        boolean saved = checkIfSaved(title);
        if (!saved) {
            System.out.println("2. Add to saved");
        }
        else {
            System.out.println("2. Remove from saved");
        }
        System.out.println("0. Go back");

        int decision = ui.promptNumeric();

        if (decision == 0) {
            System.out.println("Going back");
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
            System.out.println("Invalid option");
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
