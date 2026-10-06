import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int[] arr = new int[N];
            int idx = 0;
            while(st.hasMoreTokens()) {
                arr[idx++] = Integer.parseInt(st.nextToken());
            }
            Deque<int[]> queue = new ArrayDeque<>();
            int sum = 0, time = 0;
            idx = 0;
            while(idx < N || sum != 0) {
                time++;
                if(!queue.isEmpty() && time - queue.peek()[1] >= W) {
                    if(idx == N) {
                        time = queue.peekLast()[1] + W;
                        break;
                    }
                    sum -= queue.poll()[0];
                }
                if(idx < N) {
                    if(sum + arr[idx] <= L) {
                        sum += arr[idx];
                        queue.offer(new int[] {arr[idx++], time});
                        if(sum > L) time = queue.peek()[1] - 1;
                    }
                }
            }
            bw.append("#" + test_case + " " + time + "\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }
}
