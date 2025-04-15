import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] n = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int tmp = n[0];
        for (int num : n) {
            if (Math.abs(tmp - num) > 1) {
                bw.write("mixed\n");
                bw.flush();
                br.close();
                bw.close();
                return;
            }
            tmp = num;
        }
        if (n[0]== n.length)
            bw.write("descending\n");
        else
            bw.write("ascending\n");
        bw.flush();
        br.close();
        bw.close();
    }
}