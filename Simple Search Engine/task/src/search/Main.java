package search;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

abstract class SearchStrategy {

    protected List<String> lines = new ArrayList<>();
    protected Map<String, List<Integer>> invertedIndex = new TreeMap<>();
    protected String[] queryTokens = new String[]{};

    public abstract void search();

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public void setInvertedIndex(Map<String, List<Integer>> invertedIndex) {
        this.invertedIndex = invertedIndex;
    }


    public void setQueryTokens(String[] queryTokens) {
        this.queryTokens = queryTokens;
    }
}

class AnySearch extends SearchStrategy {

    public void search() {
        Set<Integer> n = new HashSet<>();
        for (String token : queryTokens) {
            List<Integer> integerList = invertedIndex.get(token.toLowerCase());
            if (integerList != null) {
                n.addAll(integerList);
            }
        }
        if (n.size() == 0) {
            System.out.println("No matching people found.");
        } else {
            for (Integer line : n) {
                System.out.println(lines.get(line));
            }
        }
        System.out.println();
    }
}

class AllSearch extends SearchStrategy {
    public void search() {
        Set<Integer> n = new HashSet<>();
        for (int i = 0; i < queryTokens.length; i++) {
            List<Integer> integerList = invertedIndex.get(queryTokens[i].toLowerCase());
            if (i == 0 || integerList == null) {
                if (integerList == null) {
                    integerList = new ArrayList<>() {
                    };
                }
                n.addAll(integerList);
            } else
                n.retainAll(integerList);
        }
        if (n.size() == 0) {
            System.out.println("No matching people found.");
        } else {
            for (Integer line : n) {
                System.out.println(lines.get(line));
            }
        }
        System.out.println();
    }
}

class NoneSearch extends SearchStrategy {
    public void search() {
        Set<Integer> n = new HashSet<>();
        invertedIndex.forEach((k, e) -> n.addAll(e));
        for (String token : queryTokens) {
            List<Integer> integerList = invertedIndex.get(token.toLowerCase());
            if (integerList != null) {
                integerList.forEach(n::remove);
            }
        }
        if (n.size() == 0) {
            System.out.println("No matching people found.");
        } else {
            for (Integer line : n) {
                System.out.println(lines.get(line));
            }
        }
        System.out.println();
    }
}

public class Main {

    static final String ANY = "ANY";
    static final String ALL = "ALL";
    static final String NONE = "NONE";

    static List<String> lines = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static Map<String, List<Integer>> invertedIndex = new TreeMap<>();

    static SearchStrategy searchStrategy;

    static void readFromFile(String fileName) {
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();

            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void buildInvertedIndex() {
        int v = 0;
        for (String line : lines) {
            line = line.toLowerCase();
            StringTokenizer st = new StringTokenizer(line);
            while (st.hasMoreTokens()) {
                String k = st.nextToken();
                if (invertedIndex.containsKey(k)) {
                    List<Integer> ll = invertedIndex.get(k);
                    if (ll != null) {
                        ll.add(v);
                        invertedIndex.put(k, ll);
                    }
                } else {
                    List<Integer> ll = new ArrayList<>(v);
                    ll.add(v);
                    invertedIndex.put(k, ll);
                }
            }
            v = v + 1;
        }
    }

    static void readLines() {
        Scanner s = new Scanner(System.in);
        while (s.hasNextLine()) {
            String l = s.nextLine();
            if (Objects.equals(l, "")) {
                break;
            } else {
                lines.add(l);
            }
        }
    }

    static void printAllPeople() {
        System.out.println("=== List of people ===");
        for (String line : lines) {
            System.out.println(line);
        }
    }

    static String[] askQuery() {
        System.out.println("Enter a name or email to search all suitable people.");
        Scanner s = new Scanner(System.in);
        String query = s.nextLine();
        return query.split(" ");
    }

    public static void main(String[] args) {
        boolean readFile = false;
        for (int i = 0; i < args.length; i++) {
            if (Objects.equals(args[i], "--data")) {
                String fileName = i + 1 < args.length ? args[i + 1] : null;
                if (fileName != null) {
                    readFromFile(fileName);
                    readFile = true;
                    break;
                }
            }
        }

        if (!readFile) {
            readLines();
        }

        buildInvertedIndex();

        int menu;
        do {
            System.out.println();
            System.out.println("=== Menu ===");
            System.out.println("1. Find a person");
            System.out.println("2. Print all people");
            System.out.println("0. Exit");
            String input = scanner.nextLine();
            menu = Integer.parseInt(input.replaceAll("[^0-9]", ""));
            System.out.println();


            switch (menu) {
                case 0 -> System.out.println("Bye!");
                case 1 -> {
                    System.out.println("Select a matching strategy: ALL, ANY, NONE");
                    String type = scanner.nextLine();
                    switch (type) {
                        case ANY -> searchStrategy = new AnySearch();
                        case ALL -> searchStrategy = new AllSearch();
                        case NONE -> searchStrategy = new NoneSearch();
                        default -> searchStrategy = null;
                    }
                    if (searchStrategy != null) {
                        searchStrategy.setLines(lines);
                        searchStrategy.setInvertedIndex(invertedIndex);
                        searchStrategy.setQueryTokens(askQuery());
                        searchStrategy.search();
                    } else {
                        System.out.println("No valid search strategy: " + type);
                    }
                }
                case 2 -> printAllPeople();
                default -> System.out.println("Incorrect option! Try again.");
            }
        }
        while (menu != 0);
    }
}
