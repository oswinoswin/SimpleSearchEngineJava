package search;

import java.util.ArrayList;

public class SimpleSearchEngine {
    ArrayList<String> peopleList;

    public SimpleSearchEngine() {
        this.peopleList = new ArrayList<>();
    }

    public void addToList(String person) {
        this.peopleList.add(person);
    }

    ArrayList<String> matchingPeople(String query) {
        query = query.strip().toLowerCase();
        ArrayList<String> matching = new ArrayList<>();
        for (String person : peopleList) {
            if (person.toLowerCase().contains(query)) {
                matching.add(person);
            }
        }
        return matching;
    }

    public void printMatchingPeople(String query) {
        ArrayList<String> matching = this.matchingPeople(query);
        if (matching.isEmpty()) {
            System.out.println("No matching people found");
        } else {
            System.out.println("Found people:");
            for (String person : matching) {
                System.out.println(person);
            }
        }
    }

    public void printAll() {
        System.out.println("=== List of people ===");
        for (String person : peopleList) {
            System.out.println(person);
        }
    }
}
