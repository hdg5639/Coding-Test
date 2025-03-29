import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        List<String[]> cmd = new ArrayList<>();
        for (int i = 0; i < count; i++) 
            cmd.add(br.readLine().split(" "));
        
        for (int i = 0; i < count; i++){
            for (String tmp : cmd.get(i)[1].split(""))
                for (int j = 0; j < Integer.parseInt(cmd.get(i)[0]); j++)
                    System.out.print(tmp);
            System.out.println();
        }
    }
}