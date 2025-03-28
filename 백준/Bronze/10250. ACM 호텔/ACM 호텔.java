import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[][] s =  new String[n][3];
        for (int i = 0; i < n; i++) {
            s[i] = br.readLine().split(" ");
        }
        for (String[] row : s) {
            int temp = Integer.parseInt(row[2]) % Integer.parseInt(row[0]);
            System.out.println((temp == 0?Integer.parseInt(row[0]):temp) * 100 +
                    (int)Math.ceil(Double.parseDouble(row[2]) / Double.parseDouble(row[0])));
        }
    }
}
