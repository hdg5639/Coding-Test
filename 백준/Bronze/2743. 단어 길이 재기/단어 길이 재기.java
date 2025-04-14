import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(Arrays.stream(br.readLine().split("")).toArray(String[]::new).length));
        bw.flush();
        bw.close();
        br.close();
    }
}