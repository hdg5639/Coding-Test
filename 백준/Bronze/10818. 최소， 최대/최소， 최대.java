import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(br.readLine());
        List<Integer> list = new ArrayList<>();
        String[] line = br.readLine().split(" ");
        for(int i = 0; i < len; i++)
            list.add(Integer.parseInt(line[i]));
        System.out.printf("%d %d", list.stream()
                                .mapToInt(Integer::intValue)
                                .min()
                                .orElseThrow(),
                        list.stream()
                                .mapToInt(Integer::intValue)
                                .max()
                                .orElseThrow());
    }
}