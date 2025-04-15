import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int count = Integer.parseInt(br.readLine());
        for (int i = 0; i < count; i++) {
            String[] input = br.readLine().split("");
            sb.append(input[0]).append(input[input.length - 1]).append('\n');
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}