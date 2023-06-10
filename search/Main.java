package search;
public class Main {
    public static void main(String[] args) {
        SearchMenu menu;
        if (args.length == 2 && "--data".equals(args[0])){
            menu = new SearchMenu(args[1]);
        } else {
            menu = new SearchMenu();
            menu.populateSE();
        }
        menu.showMenu();
        SimpleSearchEngine simpleSE = new SimpleSearchEngine();
        simpleSE.printAll();
    }

}
