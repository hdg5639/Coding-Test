import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String n = br.readLine();
        int tmp = Integer.parseInt(n);
        for(int i = 0; i < tmp; i++){
            if(i + Arrays.stream(
                    Arrays.stream((i + "")
                            .split(""))
                            .mapToInt(Integer::parseInt)
                            .toArray())
                    .sum() == tmp) {
                System.out.println(i);
                return;
            }
        }
        System.out.println(0);
    }
}