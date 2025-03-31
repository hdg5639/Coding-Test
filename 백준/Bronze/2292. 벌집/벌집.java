import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       long num = Integer.parseInt(br.readLine());
        System.out.println(beeAlgorithm(num));
    }

    private static long beeAlgorithm(long num){
        long count = 1;
        long tmp = 1;
        while(!(num<=tmp)){
            tmp = tmp + count*6;
            count++;
        }
        return count;
    }
}