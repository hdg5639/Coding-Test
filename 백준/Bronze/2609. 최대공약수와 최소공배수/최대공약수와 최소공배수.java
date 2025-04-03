import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       int[] numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(gcd(numbers[0], numbers[1]));
        System.out.println(lmc(numbers[0], numbers[1]));
    }

    private static int gcd(int a, int b) {
        while(b!=0){
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
    private static int lmc(int a, int b) {
        return a * b / gcd(a, b);
    }
}