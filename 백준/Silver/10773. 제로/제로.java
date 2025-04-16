import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Stack<Integer> stack = new Stack<>();
        int count = Integer.parseInt(br.readLine()), tmp = 0;
        for (int i = 0; i < count; i++) {
            int n = Integer.parseInt(br.readLine());
            if (n != 0)
                stack.push(n);
            else stack.pop();
        }
        for (int n : stack)
            tmp += n;
        bw.write(tmp + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
