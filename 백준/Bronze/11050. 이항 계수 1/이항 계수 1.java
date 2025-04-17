import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] n = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int result = 1;
        for (int i = 1; i <= n[1]; i++) { // 이거 생각해낸 사람은 천재가 틀림없음
            result *= (n[0] - i + 1); 
            result /= i;
        }

;       bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}