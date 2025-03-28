import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]), x = Integer.parseInt(line[1]);
        line = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            System.out.print(x>Integer.parseInt(line[i])?line[i]+" ":"");
        }
    }
}