import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int max = 0, count = 0;
        for(int i = 0; i < 9; i++){
            int n = Integer.parseInt(br.readLine());
            if(max < n) {
                max = n;
                count = i+1;
            }
        }
        System.out.print(max + "\n" + count);
    }
}