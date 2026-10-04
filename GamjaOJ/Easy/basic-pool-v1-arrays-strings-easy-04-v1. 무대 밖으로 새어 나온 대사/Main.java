import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        char[] input = br.readLine().toCharArray();
        boolean flag = true;
        for(char c : input) {
            switch(c) {
                case '|':
                    flag = flag ? false : true;
                    break;
                default:
                    if(flag) sb.append(c);
            }
        }
        if(sb.length() == 0)
            sb.append('-');
        System.out.println(sb);
        br.close();
    }
}
