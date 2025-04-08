import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        Set<Integer> set = new HashSet<>();
        for (String s : br.readLine().split(" ")) {
            set.add(Integer.parseInt(s));
        }

        int m = Integer.parseInt(br.readLine());
        String[] checks = br.readLine().split(" ");

        for (String s : checks) {
            int check = Integer.parseInt(s);
            bw.write(set.contains(check) ? "1\n" : "0\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}