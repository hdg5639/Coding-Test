import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        long sum = Long.parseLong(line[0])+Long.parseLong(line[1])+Long.parseLong(line[2]);
        System.out.println(sum);
    }
}