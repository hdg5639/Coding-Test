import java.io.*;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        Map<String, Double> map = new java.util.HashMap<>(Map.of(
                "A+", 4.3, "A0", 4.0, "A-", 3.7,
                "B+", 3.3, "B0", 3.0, "B-", 2.7,
                "C+", 2.3, "C0", 2.0, "C-", 1.7,
                "D+", 1.3
        ));
        map.put("D0", 1.0);
        map.put("D-", 0.7);
        map.put("F", 0.0);
        sb.append(map.get(line));

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}