import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int count = Integer.parseInt(br.readLine());
        List<String> words = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String word = br.readLine();
            if (!words.contains(word))
                words.add(word);
        }
        words.sort(Comparator.comparing(String::length)
                .thenComparing(a -> a));
        StringBuilder sb = new StringBuilder();
        words.forEach(word -> sb.append(word).append("\n"));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
