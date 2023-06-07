package search;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine().strip();
        String searchedWord = scanner.next();
        System.out.println(searchForWord(line, searchedWord));
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
