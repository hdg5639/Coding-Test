import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String[] list = line.split(" ");
        System.out.print(Integer.parseInt(list[0])*Integer.parseInt(list[1]));
    }
}