import java.io.*;
import java.nio.Buffer;
import java.util.*;
import java.util.stream.Collectors;

public class WordCount {
    private static String rootPath = "/home/xing/learn/con/data";
    private static String KEY = "Cattolica";
    private static Integer VALUE = 1182;
    private Map<String, Integer> counts;

    public WordCount() {
        counts = new HashMap<>();
    }

    private List<File> getFiles(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            System.err.printf("%s doesn't exist.", path);
            return new ArrayList<>();
        }
        File[] files = dir.listFiles();
        return Arrays.asList(files);
    }

    /**
     * Parse a page to a list of word.
     * @param page
     * @return
     */
    private List<String> preprocess(File page) {
        List<String> words = new ArrayList<>();
        if (page.exists()) {
            try {
                Scanner scanner = new Scanner(new InputStreamReader(new BufferedInputStream(new FileInputStream(page))));
                while(scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    words.addAll(Arrays.asList(line.split("[^a-zA-Z]")));
                }
            } catch (FileNotFoundException e) {
            }
        } else {
            System.out.printf("%s doesn't exist.", page.getAbsolutePath());
        }
        return words.stream().filter(w -> !w.isEmpty()).collect(Collectors.toList());
    }

    private void countAPage(List<String> page) {
        for (String w: page) {
            if (!counts.containsKey(w)) {
                counts.put(w, 0);
            }
            counts.put(w, counts.get(w) + 1);
        }
    }

    private void countPages(List<List<String>> pages) {
        for(List<String> page: pages) {
            countAPage(page);
        }
    }

    public static void main(String[] args) {
        WordCount wc = new WordCount();
        List<File> files = wc.getFiles(rootPath);
        List<List<String>> pageWords = files.stream().map(f -> wc.preprocess(f)).collect(Collectors.toList());
        Long start = System.nanoTime();
        wc.countPages(pageWords);
        Long end = System.nanoTime();
        System.out.printf("%d Got %d/%d for %s\n", (end - start) / 100000, wc.counts.get(KEY), VALUE, KEY);
    }
}
