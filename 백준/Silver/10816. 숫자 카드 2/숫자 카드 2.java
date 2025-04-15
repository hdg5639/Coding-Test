import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : Arrays.stream(Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()).toArray()) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else map.put(num, 1);
        }
        int m = Integer.parseInt(br.readLine());
        for (int num : Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()) {
            if (map.containsKey(num)) {
                sb.append(map.get(num)).append(" ");
            } else sb.append(0).append(" ");
        }
        bw.write(sb.toString() + '\n');
        bw.flush();
        bw.close();
        br.close();
    }
}