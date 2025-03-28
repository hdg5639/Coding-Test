import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int value = Integer.parseInt(br.readLine());
        if ((value % 4 == 0 && value % 100 != 0) || value % 400 == 0) {
            System.out.print("1");
        } else System.out.print("0");
    }
}