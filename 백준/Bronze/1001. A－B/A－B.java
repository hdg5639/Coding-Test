import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String[] words = line.split(" ");
        System.out.println(Integer.parseInt(words[0])-Integer.parseInt(words[1]));
    }
}