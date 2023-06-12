package search;

import java.io.IOException;
import java.util.*;

public class SimpleSearchEngine {
    ArrayList<String> peopleList;
    HashMap<String, LinkedHashSet> searchIndex;
    int currentSize;

    SearchStrategy searchStrategy;

    private interface SearchStrategy{
        ArrayList<String> find(String query);
    }

    public void setSearchStrategy(String strategyStr) throws IOException {
        switch (strategyStr){
            case "ANY": {
                this.searchStrategy = new SearchStrategyANY();
                break;
            }
            case "ALL": {
                this.searchStrategy = new SearchStrategyALL();
                break;
            } case "NONE": {
                this.searchStrategy = new SearchStrategyNONE();
                break;
            }
            default: {
                throw  new IOException("No such strategy!");
            }

        }
    }
    class SearchStrategyANY implements SearchStrategy {

        @Override
        public ArrayList<String> find(String query) {
            query = query.strip().toLowerCase();
            LinkedHashSet<Integer> indexes = new LinkedHashSet<>();
            ArrayList<String> matching = new ArrayList<>();
            for (var word: query.split("\\s+")){
                word = word.toLowerCase();
                if (searchIndex.containsKey(word))
                    indexes.addAll(searchIndex.get(word));
            }
            if (indexes != null)  indexes.forEach((i) -> matching.add(peopleList.get(i)) );
            return matching;
        }
    }

    class SearchStrategyALL implements SearchStrategy {

        @Override
        public ArrayList<String> find(String query) {
            query = query.strip().toLowerCase();
            LinkedHashSet<Integer> indexes = new LinkedHashSet<>();
            for (int i =0; i<currentSize; i++){
                indexes.add(i);
            }
            ArrayList<String> matching = new ArrayList<>();
            for (var word: query.split("\\s+")){
                word = word.toLowerCase();
                if (searchIndex.containsKey(word))
                    indexes.retainAll(searchIndex.get(word));
            }
            if (indexes != null)  indexes.forEach((i) -> matching.add(peopleList.get(i)) );
            return matching;
        }

    }

    class SearchStrategyNONE implements SearchStrategy {

        @Override
        public ArrayList<String> find(String query) {
            query = query.strip().toLowerCase();
            LinkedHashSet<Integer> indexes = new LinkedHashSet<>();
            for (int i =0; i<currentSize; i++){
                indexes.add(i);
            }
            ArrayList<String> matching = new ArrayList<>();
            for (var word: query.split("\\s+")){
                word = word.toLowerCase();
                if (searchIndex.containsKey(word))
                    indexes.removeAll(searchIndex.get(word));
            }
            if (indexes != null)  indexes.forEach((i) -> matching.add(peopleList.get(i)) );
            return matching;
        }
    }
    public SimpleSearchEngine() {
        this.peopleList = new ArrayList<>();
        this.searchIndex = new HashMap<>();
        currentSize = 0;
    }

    public void addToList(String person) {
        for (var word: person.split("\\s+")){
            if (searchIndex.containsKey(word.toLowerCase())){
                LinkedHashSet set = searchIndex.get(word.toLowerCase());
                set.add(currentSize);
                searchIndex.put(word.toLowerCase(), set);
            } else {
                LinkedHashSet<Integer> set = new LinkedHashSet<>();
                set.add(currentSize);
                searchIndex.put(word.toLowerCase(), set);
            }
        }
        this.peopleList.add(person);
        this.currentSize++;
    }

    ArrayList<String> matchingPeople(String query) {
        query = query.strip().toLowerCase();
        ArrayList<String> matching = new ArrayList<>();
        LinkedHashSet <Integer> indexes = searchIndex.get(query.toLowerCase());
        if (indexes != null)  indexes.forEach((i) -> matching.add(peopleList.get(i)) );
        return matching;
    }

    public void printMatchingPeople(String query) {
//        ArrayList<String> matching = this.matchingPeople(query);
        ArrayList<String> matching = this.searchStrategy.find(query);
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
    }
}
