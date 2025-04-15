import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int[] numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        sb.append(func(numbers[0], numbers[1])).append('\n');
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static int func (int a, int b) {
        return (a+b)*(a-b);
    }
}