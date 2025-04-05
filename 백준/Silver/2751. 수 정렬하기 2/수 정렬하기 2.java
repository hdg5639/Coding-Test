import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        int[] numbers = new int[count];
        for (int i = 0; i < count; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(numbers);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < count; i++) {
            result.append(numbers[i]).append("\n");
        }
        System.out.println(result);
        br.close();
    }
}