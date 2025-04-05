import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()), check = 0;
        int result = 0;
        while(check < n) {
            result++;
            if(Integer.toString(result).contains("666"))
                check++;
        }
        System.out.println(result);
    }
}