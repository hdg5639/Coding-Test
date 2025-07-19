import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < input[0]; i++){
            List<String> temp = Arrays.stream(br.readLine().split(" ")).map(String::trim).collect(Collectors.toList());
            map.put(temp.get(0), temp.get(1));
        }
        for(int i = 0; i < input[1]; i++){
            sb.append(map.get(br.readLine())).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}