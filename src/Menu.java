import people.*;
import fileHandling.FileHandling;
import relationships.Relationship;
import relationships.Relationships;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * This class is currently the main class of the projects.
 * It creates the menu that allows user to insert People and Relationships and view them.
 */
public class Menu {
        /**
         * This method consist of calls to all functions related to the options presented in menu.
         * @param args
         */
	public static void main(String[] args) {

        List<String> strings;
        List<Person> pplList;
        List<Relationship> relList;


        int userChoice;
        String select;
        Scanner input;

        People ppl = new People();
        Relationships rels = new Relationships();

        while(true){

                userChoice = menu();

                switch (userChoice){
                        case 1: {
                                System.out.println("Write down a file name (without file extention)");
                                input = new Scanner(System.in);
                                select = input.nextLine();

                                try {
                                        strings = FileHandling.readFile(select + ".txt");
                                        for(String string : strings)
                                                try {
                                                        ppl.addPersonFromString(string);
                                                }
                                                catch (Exception e){
                                                        e.printStackTrace();
                                                }

                                        break;
                                }
                                catch (Exception e){
                                        e.printStackTrace();
                                }
                                break;
                        }
                        case 2: {
                                System.out.println("Write down a file name (without file extention)");
                                input = new Scanner(System.in);
                                select = input.nextLine();

                                try {
                                        strings = FileHandling.readFile(select + ".txt");
                                        for(String string : strings)
                                                try {
                                                        rels.addRelationship(string, ppl);
                                                }
                                                catch (Exception e){
                                                        e.printStackTrace();
                                                }

                                        break;
                                }
                                catch (Exception e){
                                        e.printStackTrace();
                                }
                                break;
                        }
                        case 3: {
                                pplList = ppl.getPeople();
                                for(Person person: pplList)
                                        System.out.println(person.toString());
                                break;
                        }
                        case 4: {
                                relList = rels.getRelations();
                                for(Relationship relationship: relList)
                                        System.out.println(relationship.toString());
                                break;
                        }
                        case 5: {
                                System.out.println("Write down a file name (without file extention)");
                                input = new Scanner(System.in);
                                select = input.nextLine();
                                try {
                                        ppl.writeToFile(select+".txt");
                                }catch (Exception e){
                                        e.printStackTrace();
                                }
                                break;
                        }
                        case 6: {
                                System.out.println("Write down a file name (without file extention)");
                                input = new Scanner(System.in);
                                select = input.nextLine();
                                try {
                                        rels.writeToFile(select+".txt");
                                }catch (Exception e){
                                        e.printStackTrace();
                                }
                                break;
                        }
                        case 0: {
                                return;
                        }
                }

        }

}

        /**
         * This method prints menu options of our programming project and reads input from the user.
         * @return integer
         */
	public static int menu() {

        int selection;
        try {
                Scanner input = new Scanner(System.in);

                System.out.println("");
                System.out.println("Choose from these choices");
                System.out.println("-------------------------\n");
                System.out.println("1 - Load ‘people’ into the network");
                System.out.println("2 - Load ‘relationships’");
                System.out.println("3 - Print out people");
                System.out.println("4 - Print out relationships");
                System.out.println("5 - Save to file people");
                System.out.println("6 - Save to file relationships");
                System.out.println("0 - Quit");

                selection = input.nextInt();
                return selection;
        }
        catch(InputMismatchException e){
                System.out.println("Bad input");
        }
            return 0;
    }


}