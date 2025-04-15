import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        long[] numbers = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        sb.append(func(numbers[0], numbers[1])).append('\n');
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static long func (long a, long b) {
        return (a+b)*(a-b);
    }
}