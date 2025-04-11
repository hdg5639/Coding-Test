import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int count = Integer.parseInt(br.readLine());
        List<int[]> points = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int[] s = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            points.add(s);
        }
        points.sort(Comparator.comparingInt((int[] a) -> a[1])
                .thenComparingInt(a -> a[0]));
        StringBuilder sb = new StringBuilder();
        points.forEach(point -> sb.append(point[0]).append(" ").append(point[1]).append("\n"));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
