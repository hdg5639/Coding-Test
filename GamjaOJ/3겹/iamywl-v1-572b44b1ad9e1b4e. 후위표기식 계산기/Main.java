import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++) {
            int L = Integer.parseInt(br.readLine());
            char[] input = br.readLine().toCharArray();
            Deque<Integer> stack = new ArrayDeque<>();
            for(char c : input) {
                if (c != '+' && c != '*')
                    stack.push(c - '0');
                else {
                    switch(c) {
                        case '+':
                            stack.push(stack.pop() + stack.pop());
                            break;
                        case '*':
                            stack.push(stack.pop() * stack.pop());
                            break;
                    }
                }
            }
            bw.append("#" + test_case + " " + stack.peek() + "\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }
}
