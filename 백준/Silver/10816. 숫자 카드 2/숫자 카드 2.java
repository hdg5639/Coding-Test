import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        br.readLine();
        int[] n = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : n) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        br.readLine();
        int[] m = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int num : m) {
            sb.append(map.getOrDefault(num, 0)).append(" ");
        }
        bw.write(sb.toString() + '\n');
        bw.flush();
        bw.close();
        br.close();
    }
}