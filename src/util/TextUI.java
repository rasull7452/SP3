package util;

import java.util.ArrayList;
import java.util.Scanner;

public class TextUI {


    private static Scanner sc = new Scanner(System.in);

    public ArrayList<String> promptChoice( ArrayList<String> options, int limit, String msg){
        displayMsg(msg);
        displayList(options, "");
        ArrayList<String> choices = new ArrayList<>();
        while(choices.size() < limit){

            int choice = 0;
            try { //tester choice og kører hvis det er fint
                choice = promptNumeric("");
            } catch (Exception e) { //får choice til at køre igen hvis input er valid invalid

                choice = promptNumeric("");
            }
            if (choice >= 1 && choice <= options.size()) {
                choices.add(options.get(choice - 1));
            } else {
                displayMsg("Please enter a number between 1 and " + options.size());
            }
        }
        return choices;
    }

    public void displayList(ArrayList<String>list, String msg) {
        for (int i = 0; i < list.size();i++) {
            System.out.println(i+1+". "+list.get(i));
        }
    }

    public void displayMsg(String msg){
        System.out.println(msg);

    }
    public int promptNumeric(String msg){
        displayMsg(msg);                       //Stille brugeren et spørgsmål
        String input = sc.nextLine();                  //Give brugere et sted at placere sit svar og vente på svaret
        int numInput = Integer.parseInt(input);        //Konvertere svaret til et tal
        return numInput;
    }

    public String promptText(String msg){
        displayMsg(msg);         //Stille brugeren et spørgsmål
        String input = sc.nextLine();          //Give brugere et sted at placere sit svar og vente på svaret

        return input;
    }

    public boolean promptBinary(String msg){
        displayMsg(msg);
        String input = sc.nextLine();
        if(input.equalsIgnoreCase("Y")){
            return true;
        }
        else if(input.equalsIgnoreCase("N")){
            return false;
        }
        else{
            return promptBinary(msg);
        }
    }
}
