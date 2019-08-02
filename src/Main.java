import java.util.ArrayList;

public class Main {
    private static double testWord(String fileName, Map<String, Integer> wordMap) {
        double startTime = System.nanoTime();

        System.out.println("Pride and Prejudice");
        ArrayList<String> words = new ArrayList<>();

        if (FileOperation.readFile("src/article/pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

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

        double endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        BSTMap<String, Integer> bstMap = new BSTMap<>();
        double time1 = testWord("src/article/pride-and-prejudice.txt", bstMap);
        System.out.println("bstMap: " + time1 + "s");

        System.out.println();

        LinkedListMap<String, Integer> linkedListMap = new LinkedListMap<>();
        double time2 = testWord("src/article/pride-and-prejudice.txt", linkedListMap);
        System.out.println("linkedListMap: " + time2 + "s");

        System.out.println("Pride and Prejudice");
        ArrayList<String> words = new ArrayList<>();


        System.out.println("============================");
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
