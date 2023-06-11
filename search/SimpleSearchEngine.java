package search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class SimpleSearchEngine {
    ArrayList<String> peopleList;
    HashMap<String, LinkedList> searchIndex;
    int currentSize;

    public SimpleSearchEngine() {
        this.peopleList = new ArrayList<>();
        this.searchIndex = new HashMap<>();
        currentSize = 0;
    }

    public void addToList(String person) {
        for (var word: person.split("\\s+")){
//            System.out.print(word + " | ");
            if (searchIndex.containsKey(word.toLowerCase())){
                LinkedList list = searchIndex.get(word.toLowerCase());
                list.add(currentSize);
                searchIndex.put(word.toLowerCase(), list);
            } else {
                LinkedList<Integer> list = new LinkedList<>();
                list.add(currentSize);
                searchIndex.put(word.toLowerCase(), list);
            }
        }
        this.peopleList.add(person);
        this.currentSize++;
//        System.out.println();
//        searchIndex.forEach((val, key) -> System.out.println("SE: " + key + " " + val));
    }

    ArrayList<String> matchingPeople(String query) {
//        System.out.println("Searching for " + query);
//        searchIndex.forEach((key, val) -> System.out.println("SE: " + key + " " + val));
        query = query.strip().toLowerCase();
        ArrayList<String> matching = new ArrayList<>();
        //use index
        LinkedList<Integer> indexes = searchIndex.get(query.toLowerCase());
        if (indexes != null)  indexes.forEach((i) -> matching.add(peopleList.get(i)) );

//        for (String person : peopleList) {
//            if (person.toLowerCase().contains(query)) {
//                matching.add(person);
//            }
//        }
        return matching;
    }

    public void printMatchingPeople(String query) {
        ArrayList<String> matching = this.matchingPeople(query);
        if (matching.isEmpty()) {
            System.out.println("No matching people found.");
        } else {
            System.out.println(matching.size() +  " persons found:");
            for (String person : matching) {
                System.out.println(person);
            }
        }
    }

    public void printAll() {
        System.out.println("=== List of people ===");
        peopleList.forEach(person->System.out.println(person));
//        for (String person : peopleList) {
//            System.out.println(person);
//        }
    }
}
