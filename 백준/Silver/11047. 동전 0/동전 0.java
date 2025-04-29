import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int[] targets = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] coins = new int[targets[0]];
        for (int i = 0; i < targets[0]; i++)
            coins[i] = Integer.parseInt(br.readLine());

        int temp = targets[1], i = targets[0]-1, result = 0;
        while (temp > 0) {
            int tmp = temp / coins[i];
            if (tmp > 0) {
                result += tmp;
                temp = temp % coins[i];
                i--;
            } else {
                i--;
            }
        }

        sb.append(result).append("\n");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}