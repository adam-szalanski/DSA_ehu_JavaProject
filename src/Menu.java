import people.*;
import fileHandling.FileHandling;
import relationships.Relationship;
import relationships.Relationships;

import java.util.*;

/**
 * This class is currently the main class of the projects.
 * It creates the menu that allows user to insert People and Relationships and view them.
 */
public class Menu {
    /**
     * This method consist of calls to all functions related to the options presented in menu.
     *
     * @param args
     */
    public static void main(String[] args) {

        List<String> strings;

        int userChoice;
        String select;
        Scanner input;

        People ppl = new People();
        Relationships rels = new Relationships();

        while (true) {

            userChoice = menu();

            switch (userChoice) {
                case 1: {
                    System.out.println("Write down a file name (without file extension):");
                    input = new Scanner(System.in);
                    select = input.nextLine();

                    try {
                        strings = FileHandling.readFile(select + ".txt");
                        for (String string : strings)
                            try {
                                ppl.addPersonFromString(string);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case 2: {
                    System.out.println("Write down a file name (without file extension):");
                    input = new Scanner(System.in);
                    select = input.nextLine();

                    try {
                        strings = FileHandling.readFile(select + ".txt");
                        for (String string : strings)
                            try {
                                rels.addRelationship(string, ppl);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case 3: {
                    ppl.printAllPeople();
                    break;
                }
                case 4: {
                    rels.printAllRelationships();
                    break;
                }
                case 5: {
                    System.out.println("Write down a file name (without file extension):");
                    input = new Scanner(System.in);
                    select = input.nextLine();
                    try {
                        ppl.writeToFile(select + ".txt");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case 6: {
                    System.out.println("Write down a file name (without file extension):");
                    input = new Scanner(System.in);
                    select = input.nextLine();
                    try {
                        rels.writeToFile(select + ".txt");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case 7: {
                    System.out.println("Write down an ID of the person you want to delete:");
                    input = new Scanner(System.in);
                    select = input.nextLine();

                    ppl.removePersonById(select);
                    List<Relationship> listOfRelationshipsForDelate = rels.findRelationshipsById(select);
                    for (Relationship relationship : listOfRelationshipsForDelate) {
                        rels.deleteRelationship(relationship);
                    }

                    break;
                }
                case 8: {
                    System.out.println("To delete relationship between two people, write down their IDs:");
                    Scanner input1 = new Scanner(System.in);
                    String select1 = input1.nextLine();
                    Scanner input2 = new Scanner(System.in);
                    String select2 = input2.nextLine();

                    Relationship relationshipToDelete = rels.findRelationshipsByIDs(select1, select2);

                    rels.deleteRelationship(relationshipToDelete);

                    break;
                }
                case 9: {
                    System.out.println("To show relationship of the person, write down their lastname:");
                    Scanner input1 = new Scanner(System.in);
                    String select1 = input1.nextLine();
                    ppl.printRelationshipsByLastname(select1, rels);
                    break;
                }
                case 10: {
                    System.out.println("To show the list of people, write down their birthplace:");
                    Scanner input1 = new Scanner(System.in);
                    String select1 = input1.nextLine();
                    ppl.printPeopleByBirthplace(select1);
                    break;
                }
                case 11: {
                    System.out.println("To show the list of people, write down starting and ending year (separated by ','):");
                    Scanner input1 = new Scanner(System.in);
                    String select1 = input1.nextLine();
                    String[] splited = select1.split(",");
                    try {
                        if (splited[0] == null || splited[1] == null) {
                            System.out.println("Wrong year range provided");
                        }
                        int yearMin, yearMax;
                        yearMin = Math.min(Integer.parseInt(splited[0]), Integer.parseInt(splited[1]));
                        yearMax = Math.max(Integer.parseInt(splited[0]), Integer.parseInt(splited[1]));
                        ppl.printPeopleBornBetween(yearMin, yearMax);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Wrong year range provided");
                    }
                    break;
                }
                case 12: {
                    System.out.println("Write down a file name (without file extension):");
                    input = new Scanner(System.in);
                    select = input.nextLine();
                    try {
                        ppl.findMatchesByFile(select + ".txt");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case 13: {
                    ppl.sortBySurnameAndName();
                    ppl.printAllPeople();
                    break;
                }
                case 14: {
                    ppl.sortByBirthplaceSurnameAndName();
                    ppl.printAllPeople();
                    break;
                }
                case 15: {
                    ppl.quicksortByBirthplaceSurnameAndName();
                    ppl.printAllPeople();
                    break;
                }
                case 16: {
                    ppl.fillTheClasses();
                    ppl.sortClasses();
                    ppl.printAllClasses();
                    break;
                }
                case 17: {
                    ppl.sortPeopleByMovies();
                    break;
                }
                case 18: {
                    rels.createGraph();
                    rels.printGraph();
                    break;
                }
                case 19: {
                    System.out.println("To show the shortest path between two people write down two peoples ID (separated by ','):");
                    input = new Scanner(System.in);
                    String select1 = input.nextLine();
                    String[] splited = select1.split(",");
                    try {
                        rels.shortestPath(splited[0], splited[1]);
                    }
                    catch (Exception e){
                        System.out.println("Wrong IDs");
                    }
                    break;
                }
                case 20: {
                    System.out.println("To show the longest path between two people write down two peoples ID (separated by ','):");
                    input = new Scanner(System.in);
                    String select1 = input.nextLine();
                    String[] splited = select1.split(",");
                    try {
                        rels.longestPath(splited[0], splited[1]);
                    }
                    catch (Exception e){
                        System.out.println("Wrong IDs");
                    }
                    break;
                }
                case 21: {
                    rels.cliqueOfPeople();
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
     *
     * @return integer
     */
    public static int menu() {
        int selection;
        try {
            Scanner input = new Scanner(System.in);

            System.out.println();
            System.out.println("Choose from these choices");
            System.out.println("-------------------------\n");
            System.out.println("1 - Load �people� into the network");
            System.out.println("2 - Load �relationships�");
            System.out.println("3 - Print out people");
            System.out.println("4 - Print out relationships");
            System.out.println("5 - Save to file people");
            System.out.println("6 - Save to file relationships");
            System.out.println("7 - Delete person and relations related to it");
            System.out.println("8 - Delete relationship");
            System.out.println("9 - Show relationships for lastname");
            System.out.println("10 - Show people by birthplace");
            System.out.println("11 - Show people born between years");
            System.out.println("12 - Find potential matches by file with IDs");
            System.out.println("13 - Sort people by surname and then name");
            System.out.println("14 - Sort people by birthplace, surname and then name");
            System.out.println("15 - Quicksort people by birthdate, surname and name");
            System.out.println("16 - Show classes of profiles");
            System.out.println("17 - Sort people by most common movies sets");
            System.out.println("18 - Create Graph");
            System.out.println("19 - Find shortest path in graph for two friends");
            System.out.println("20 - Find longest path in graph for two friends");
            System.out.println("21 - Show cliques of friends");
            System.out.println("0 - Quit");

            selection = input.nextInt();
            return selection;
        } catch (InputMismatchException e) {
            System.out.println("Bad input");
        }
        return 0;
    }
}