import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        List<int[]> people = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            people.add(new int[]{Integer.parseInt(input[0]), Integer.parseInt(input[1])});
        }
        for (int[] p : people) {
            int count = 1;
            for (int i = 0; i < n; i++) {
                int[] tmp = people.get(i);
                if (people.get(i) != p) {
                    if(tmp[0] > p[0] && tmp[1] > p[1])
                        count++;
                }
            }
            bw.write(count + " ");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}