import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] n = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        bw.write(func(n[0], n[1]).toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static StringBuilder func(int person, int number) {
        StringBuilder sb = new StringBuilder();
        List<Integer> flag = new ArrayList<>();
        for (int i = 1; i <= person; i++) {
            flag.add(i);
        }
        int tmp = number - 1;
        sb.append("<").append(flag.remove(tmp));
        for (int i = 0; i < person - 1; i++) {
            tmp += number-1;
            while (tmp >= flag.size())
                tmp -= flag.size();
            sb.append(", ").append(flag.remove(tmp));
        }
        return sb.append(">");
    }
}