import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int count = Integer.parseInt(br.readLine());
        List<int[]> arr = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            arr.add(Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray());
        }
        arr.sort(Comparator.comparingInt((int[] a) -> a[0])
                .thenComparing((int[] a) -> a[1]));
        StringBuilder sb = new StringBuilder();
        for (int[] integers : arr)
            sb.append(integers[0]).append(" ").append(integers[1]).append("\n");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}