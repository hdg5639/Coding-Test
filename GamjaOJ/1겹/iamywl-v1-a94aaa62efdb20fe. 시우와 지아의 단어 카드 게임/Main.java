import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());
            
            Deque<Character> deque = new ArrayDeque<>();
            st = new StringTokenizer(br.readLine());
            deque.offer(st.nextToken().charAt(0));
            
            for(int i = 0; i < N - 1; i++) {
                char c = st.nextToken().charAt(0);
                if(c - deque.peek() <= 0) deque.offerFirst(c);
                else deque.offer(c);
            }

            bw.append("#" + test_case + " ");
            while(!deque.isEmpty())
                bw.append(deque.poll());
            bw.append("\n");
        }
        
        bw.flush();
        br.close();
        bw.close();
    }
}
