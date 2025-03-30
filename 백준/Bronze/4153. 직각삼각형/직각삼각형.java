import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<int[]> s = new ArrayList<>();
        while(true){
            String[] temp = br.readLine().split(" ");
            if (temp[0].equals("0") && temp[1].equals("0") && temp[2].equals("0")) break;
            s.add(Arrays.stream(temp).mapToInt(Integer::parseInt).sorted().toArray());
        }
        s.stream()
                .takeWhile(i -> i[0] != 0 || i[1] != 0 || i[2] != 0) // value 값이 모두 0이 아닐 때
                .forEach(i -> {
                    if (Math.pow(i[2], 2) == Math.pow(i[0], 2) + Math.pow(i[1], 2))
                        System.out.println("right");
                    else
                        System.out.println("wrong");
                });
        br.close();
    }
}