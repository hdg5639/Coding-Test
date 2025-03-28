import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int line = Integer.parseInt(br.readLine());
        int[] list = new int[line];
        for (int i = 0; i < line; i++) {
            String[] cases = br.readLine().split(" ");
            list[i] = Integer.parseInt(cases[0]) + Integer.parseInt(cases[1]);
        }
        for ( int i :  list) {
            System.out.println(i);
        }
    }
}