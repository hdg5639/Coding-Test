import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(card(Integer.parseInt(br.readLine()))) + '\n');
        bw.flush();
        bw.close();
        br.close();
    }

    private static int card(int num) {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= num; i++) {
            q.add(i);
        }
        while (q.size() > 1) {
            q.poll();
            q.add(q.poll());
        }
        return Objects.requireNonNull(q.peek());
    }
}