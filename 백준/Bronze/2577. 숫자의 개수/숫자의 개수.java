import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> words = new ArrayList<>();
        for (int i = 0; i < 3; i++) words.add(Integer.parseInt(br.readLine()));
        int mult = 1;
        for (int word : words) mult *= word;
        String[] split = String.valueOf(mult).split("");
        int[] results = new int[10];
        for (String word : split) results[Integer.parseInt(word)]++;
        for (int result : results) System.out.println(result);
    }
}