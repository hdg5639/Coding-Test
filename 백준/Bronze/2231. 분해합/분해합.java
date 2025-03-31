import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int getSum(int num) {
        int sum = num;
        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int start = Math.max(1, n - 54);
        for (int i = start; i < n; i++) {
            if (getSum(i) == n) {
                System.out.println(i);
                return;
            }
        }
        System.out.println(0);
    }
}
