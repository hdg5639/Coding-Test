import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split("");
        int[] arr = new int[26];
        Arrays.fill(arr, -1);
        for (int i = 0; i < line.length; i++) {
            char word = line[i].charAt(0);
            if (arr[word - 'a'] == -1)
                arr[word - 'a'] = i;
        }
        for(int num : arr)
            System.out.print(num + " ");
    }
}