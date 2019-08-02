import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");
        ArrayList<String> words = new ArrayList<>();

        if (FileOperation.readFile("src/article/pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            LinkedListMap<String, Integer> wordMap = new LinkedListMap<>();
            for (String word : words) {
                if (wordMap.contains(word))
                    wordMap.set(word, wordMap.get(word) + 1);
                else
                    wordMap.add(word, 1);
            }

            System.out.println("Total different words: " + wordMap.getSize());
            System.out.println("Frequency of PRIDE: " + wordMap.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + wordMap.get("prejudice"));
        }
    }
}
