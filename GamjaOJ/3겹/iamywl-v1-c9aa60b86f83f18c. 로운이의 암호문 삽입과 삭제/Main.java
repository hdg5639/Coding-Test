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
            st = new StringTokenizer(br.readLine());
            LinkedList<Integer> list = new LinkedList<>();
            while(st.hasMoreTokens()) list.add(Integer.parseInt(st.nextToken()));
            int M = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            int idx, cnt;
            for(int i = 0; i < M; i++) {
                switch(st.nextToken()) {
                    case "I":
                        idx = Integer.parseInt(st.nextToken());
                        cnt = Integer.parseInt(st.nextToken());
                        for(int c = 0; c < cnt; c++)
                            list.add(idx++, Integer.parseInt(st.nextToken()));
                        break;
                    case "D":
                        idx = Integer.parseInt(st.nextToken());
                        cnt = Integer.parseInt(st.nextToken());
                        for(int c = 0; c < cnt; c++)
                            list.remove(idx);
                        break;
                    case "A":
                        cnt = Integer.parseInt(st.nextToken());
                        for(int c = 0; c < cnt; c++)
                            list.addLast(Integer.parseInt(st.nextToken()));
                        break;
                }
            }
            
            bw.append("#" + test_case);
            for(idx = 0; idx < 10; idx++) {
                if(idx == list.size()) break;
                bw.append(" " + list.get(idx));
            }
            bw.append("\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }
}
