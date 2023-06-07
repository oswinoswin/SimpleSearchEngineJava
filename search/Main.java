package search;
import java.util.ArrayList;
import java.util.Scanner;

class SimpleSE{
    ArrayList<String> peopleList;

    public SimpleSE() {
        this.peopleList = new ArrayList<>();
    }

    public void addToList(String person){
        this.peopleList.add(person);
    }

    ArrayList<String> matchingPeople(String query){
        query = query.strip().toLowerCase();
        ArrayList<String> matching = new ArrayList<>();
        for (String person: peopleList){
            if(person.toLowerCase().contains(query)){
                matching.add(person);
            }
        }
        return matching;
    }

    public void printMatchingPeople(String query){
        ArrayList<String> matching = this.matchingPeople(query);
        if (matching.isEmpty()){
            System.out.println("No matching people found");
        }else{
            System.out.println("Found people:");
            for (String person: matching){
                System.out.println(person);
            }
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SimpleSE simpleSE = new SimpleSE();
        System.out.println("Enter the number of people:");
        int N = scanner.nextInt();
        System.out.println("Enter all the people:");
        for (int i=0; i<=N; i++){
            String person = scanner.nextLine().trim();
            simpleSE.addToList(person);
        }
        System.out.println("Enter the number of search queries:");
        int M = scanner.nextInt();
        for(int i=0; i<M; i++){
            System.out.println("Enter data to search people:");
            String query = scanner.next();
            simpleSE.printMatchingPeople(query);
        }

    }
    public static String searchForWord(String line, String searchedWord){
        line = line.trim().strip();
        searchedWord = searchedWord.strip();

        String[] words = line.split(" ");
        int counter = 0;
        for (String word: words){
            word = word.strip();

            if (word.isEmpty()) continue;

            counter++;

            if (word.equals(searchedWord)){
                return "" + counter;
            }
        }
        return "Not found";
    }
}
