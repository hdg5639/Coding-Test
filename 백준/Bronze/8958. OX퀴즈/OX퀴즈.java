import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] count = new int[n];
        for(int i = 0; i < n; i++){
            String[] cmd = br.readLine().split("");
            int tmp = 0;
            for (String s : cmd) {
                count[i] += s.equals("O") ? ++tmp : tmp!=0 ? tmp=0 : 0;
            }
        }
        for(int num : count)
            System.out.println(num);
    }
}