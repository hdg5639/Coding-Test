import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       int[] n = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray() ,
               cards = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
        System.out.println(search(n[1], cards));
    }
    private static int search(int target, int[] cards) {
        int max = 0;
        int n = cards.length;

        for(int i = 0; i < n - 2; i++) {
            for(int j = i + 1; j < n - 1; j++) {
                for(int k = j + 1; k < n; k++) {
                    int sum = cards[i] + cards[j] + cards[k];
                    if(sum <= target && sum > max) {
                        max = sum;
                    }
                }
            }
        }
        return max;
    }
}