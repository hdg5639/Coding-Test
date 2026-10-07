import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            LinkedList<Integer> dq = new LinkedList<>();
            for (int i = 1; i <= N; i++) dq.offer(i);

            st = new StringTokenizer(br.readLine());
            int answer = 0;
            for (int k = 0; k < M; k++) {
                int target = Integer.parseInt(st.nextToken());
                int idx = dq.indexOf(target);
                int size = dq.size();

                if (idx <= size - idx) {
                    for (int i = 0; i < idx; i++) dq.offerLast(dq.pollFirst());
                    answer += idx;
                } else {
                    for (int i = 0; i < size - idx; i++) dq.offerFirst(dq.pollLast());
                    answer += size - idx;
                }
                dq.pollFirst();
            }
            sb.append('#').append(tc).append(' ').append(answer).append('\n');
        }
        System.out.print(sb);
    }
}