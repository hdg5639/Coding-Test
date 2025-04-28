import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        Set<Long> set = new HashSet<>();
        int count = Integer.parseInt(br.readLine());
        for (int i = 0; i < count; i++) {
            String[] input = br.readLine().split(" ");
            switch(input[0]){
                case "add":
                    set.add(Long.parseLong(input[1]));
                    break;
                case "remove":
                    set.remove(Long.parseLong(input[1]));
                    break;
                case "check":
                    sb.append(set.contains(Long.parseLong(input[1])) ? "1\n" : "0\n");
                    break;
                case "toggle":
                    if (set.contains(Long.parseLong(input[1]))) {
                        set.remove(Long.parseLong(input[1]));
                    } else {
                        set.add(Long.parseLong(input[1]));
                    }
                    break;
                case "all":
                    for (long j = 1L; j <= 20; j++) {
                        set.add(j);
                    }
                    break;
                case "empty":
                    set.clear();
                    break;
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}