import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        List<List<String>> vps = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            vps.add(Arrays.stream(br.readLine().split("")).collect(Collectors.toList()));

        }

        vps.forEach(i -> {
            boolean isValid = true;
            int check = 0;

            for (String ch : i) {
                if (ch.equals("(")) check++;
                else check--;

                if (check < 0) { // ')'가 '('보다 많아지면 잘못된 문자열
                    isValid = false;
                    break;
                }
            }

            if (check != 0) isValid = false;
            try {
                bw.write(isValid ? "YES\n" : "NO\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        bw.flush();
        bw.close();
        br.close();
    }
}