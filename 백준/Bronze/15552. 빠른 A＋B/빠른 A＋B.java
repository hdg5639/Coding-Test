import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        int[] s = IntStream.range(0, count) // 스트림으로 이걸 할 수 있구만
                .map(i -> {
                    try {
                        String[] temp = br.readLine().split(" ");
                        return Integer.parseInt(temp[0]) + Integer.parseInt(temp[1]);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toArray();
        StringBuilder sb = new StringBuilder();
        Arrays.stream(s).forEach(x -> sb.append(x).append('\n'));
        System.out.print(sb);
    }
}