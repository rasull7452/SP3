import java.util.ArrayList;
import java.util.Scanner;

public class HeadMenu {
    private User currentUser;
    private ArrayList<Media> allMedia; // Fyldes fra en fil i starten (fx i App-klassen)
    private Scanner input = new Scanner(System.in);

    public HeadMenu(User currentUser, ArrayList<Media> allMedia) {
        this.currentUser = currentUser;
        this.allMedia = allMedia;
    }

    // Viser brugerens gemte medier
    public void showSavedMedia() {
        System.out.println("\n--- Dine gemte film og serier ---");
        ArrayList<Media> savedList = currentUser.getSavedMedia();

        if (savedList.isEmpty()) {
            System.out.println("Du har ingen gemte medier endnu.");
            return;
        }

        for (int i = 0; i < savedList.size(); i++) {
            System.out.println((i + 1) + ". " + savedList.get(i));
        }
    }

    // Viser brugerens sete medier
    public void showWatchedMedia() {
        System.out.println("\n--- Dine sete film og serier ---");
        ArrayList<Media> watchedList = currentUser.getWatchedMedia();

        if (watchedList.isEmpty()) {
            System.out.println("Du har endnu ikke set nogen medier.");
            return;
        }

        for (int i = 0; i < watchedList.size(); i++) {
            System.out.println((i + 1) + ". " + watchedList.get(i));
        }
    }

    // Tilføjer et medie til brugerens gemte liste
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

    // Tilføjer et medie til brugerens sete liste
    public void addMediaToWatched() {
        System.out.println("\nIndtast titlen på mediet, du har set: ");
        String title = input.nextLine();

        Media found = findMediaByTitle(title);
        if (found != null) {
            if (!currentUser.getWatchedMedia().contains(found)) {
                currentUser.getWatchedMedia().add(found);
                System.out.println(found.getTitle() + " er nu markeret som set.");
            } else {
                System.out.println("Dette medie er allerede markeret som set.");
            }
        } else {
            System.out.println("Mediet blev ikke fundet.");
        }
    }

    // Hjælpemetode til at finde et medie i allMedia
    private Media findMediaByTitle(String title) {
        for (Media m : allMedia) {
            if (m.getTitle().equalsIgnoreCase(title)) {
                return m;
            }
        }
        return null;
    }
}
