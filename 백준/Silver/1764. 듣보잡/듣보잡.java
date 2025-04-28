import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int[] counts = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < counts[0]; i++) {
            set.add(br.readLine());
        }
        List<String> tmp = new ArrayList<>();
        for (int i = 0; i < counts[1]; i++) {
            String input = br.readLine();
            if (set.contains(input)) {
                tmp.add(input);
            }
        }
        tmp.sort(String::compareTo);
        sb.append(tmp.size()).append("\n");
        for (String s : tmp) {
            sb.append(s).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}