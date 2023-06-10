package search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class SearchMenu {
    Scanner scanner;
    SimpleSearchEngine simpleSearchEngine;

    String options = "=== Menu ===\n" +
            "1. Find a person\n" +
            "2. Print all people\n" +
            "0. Exit";

    public SearchMenu() {
        scanner = new Scanner(System.in);
        simpleSearchEngine = new SimpleSearchEngine();
    }

    public SearchMenu(String filename){
        scanner = new Scanner(System.in);
        File file = new File(filename);

        simpleSearchEngine = new SimpleSearchEngine();
        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNext()) {
                String person = fileScanner.nextLine().trim();
                simpleSearchEngine.addToList(person);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found: " + filename);
        }
    }

    public void populateSE(){
        System.out.println("Enter the number of people:");
        int N = scanner.nextInt();
        scanner.nextLine(); //clear scanner
        System.out.println("Enter all the people:");
        for (int i=0; i<N; i++){
            String person = scanner.nextLine().trim();
            simpleSearchEngine.addToList(person);
        }
    }

    public void showMenu(){

        boolean exit = false;
        while (!exit) {
            System.out.println(options);
            String input = scanner.next();
            switch (input){
                case "1": {
                    findPerson();
                    break;
                }
                case "2": {
                    printAll();
                    break;
                }
                case "0": {
                    System.out.println("Bye!");
                    exit = true;
                    break;
                }
                default:
                    System.out.println("Incorrect option! Try again.");
                    break;
            }

        }
    }

    public void findPerson() {
        System.out.println("Enter a name or email to search all suitable people.");
        String person = scanner.next();
        simpleSearchEngine.printMatchingPeople(person);

    }

    public void printAll() {
        simpleSearchEngine.printAll();
    }


}

