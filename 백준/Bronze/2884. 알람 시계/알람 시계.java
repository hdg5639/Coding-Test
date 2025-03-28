import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int h = Integer.parseInt(line[0]), m = Integer.parseInt(line[1]);
        if(m - 45 < 0){
            if(h == 0) h = 23;
            else h--;
            m = 60 + (m - 45);
        } else {
            m = m - 45;
        }
        System.out.printf("%d %d\n", h, m);
    }
}