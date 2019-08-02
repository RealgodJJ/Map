import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");
        ArrayList<String> words = new ArrayList<>();

        if (FileOperation.readFile("src/article/pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            BSTMap<String, Integer> wordMap = new BSTMap<>();
            for (String word : words) {
                if (wordMap.contains(word))
                    wordMap.set(word, wordMap.get(word) + 1);
                else
                    wordMap.add(word, 1);
            }

            int num = 0;
            if (wordMap.contains("prejudice")) {
                num = wordMap.remove("prejudice");
            }

            System.out.println("Total different words: " + wordMap.getSize());
            System.out.println("Frequency of PRIDE: " + wordMap.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + num);
            System.out.println("Frequency of PREJUDICE: " + wordMap.get("prejudice"));
        }
    }
}
