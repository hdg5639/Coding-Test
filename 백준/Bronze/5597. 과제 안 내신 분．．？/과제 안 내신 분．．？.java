import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 28; i++)
            list.add(Integer.parseInt(br.readLine()));

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 30; i++)
            if (!list.contains(i))
                sb.append(i).append("\n");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
