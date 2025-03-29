import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int tmp = 0;
        for(String s : line) {
            if (tmp != 0) {
                if (tmp + 1 != Integer.parseInt(s) && tmp - 1 != Integer.parseInt(s)) {
                    System.out.println("mixed");
                    return;
                }
            }
            tmp = Integer.parseInt(s);
        }
        if(tmp == 8) System.out.println("ascending");
        else System.out.println("descending");
    }
}