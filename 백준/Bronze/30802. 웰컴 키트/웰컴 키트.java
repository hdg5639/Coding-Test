import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        int[] size = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray(),
                bundle = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int temp = 0;
        for(int s : size){
            temp += (int)Math.ceil((double)s/(double)bundle[0]);
        }
        System.out.println(temp);
        System.out.println(count/bundle[1] + " " + count%bundle[1]);
        br.close();
    }
}