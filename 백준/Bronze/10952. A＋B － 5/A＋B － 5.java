import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> results = new ArrayList<>();

        String line;
        while ((line = br.readLine()) != null) {
            String[] cases = line.split(" ");
            if(!cases[0].equals("0") &&!cases[1].equals("0"))
                results.add(Integer.parseInt(cases[0]) + Integer.parseInt(cases[1]));
            else break;
        }

        for (int result : results) {
            System.out.println(result);
        }
    }
}