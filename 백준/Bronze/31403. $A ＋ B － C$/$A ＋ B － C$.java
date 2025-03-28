import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = new String[3];
        for(int i = 0; i < 3; i++){
            line[i] = br.readLine();
        }
        System.out.println(Integer.parseInt(line[0]) +
                Integer.parseInt(line[1]) -
                Integer.parseInt(line[2]));
        System.out.println(Integer.parseInt(line[0] + line[1]) -
                Integer.parseInt(line[2]));
    }
}