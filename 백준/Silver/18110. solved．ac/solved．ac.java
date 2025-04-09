import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        int exclude = (int)Math.round(count * 0.15);

        int[] arr = new int[count];
        for (int i = 0; i < count; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int sum = 0;
        for (int i = exclude; i < count - exclude; i++) {
            sum += arr[i];
        }

        int trimmedCount = count - 2 * exclude;
        System.out.println(Math.round((double)sum / trimmedCount));
    }
}
