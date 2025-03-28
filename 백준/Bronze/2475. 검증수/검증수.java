import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String[] words = line.split(" ");
        int numbers = 0;
        for (String word : words) {
            numbers += (int) Math.pow(Integer.parseInt(word), 2);
        }
        System.out.println(numbers%10);
    }
}