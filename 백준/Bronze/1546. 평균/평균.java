import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       int n = Integer.parseInt(br.readLine());
       int[] score = Arrays.stream(br.readLine().split(" "))
               .mapToInt(Integer::parseInt).sorted().toArray();
       double max = Arrays.stream(score).max().orElse(0), result = 0;
       
       for (int i = 0; i < n; i++) 
           result += score[i]/max*100;
       
        System.out.println(result/n);
    }
}