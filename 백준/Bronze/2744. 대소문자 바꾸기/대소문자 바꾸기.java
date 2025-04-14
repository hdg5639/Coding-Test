import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        String[] input = br.readLine().split("");
        for (String s : input) {
            sb.append(Character.isUpperCase(s.charAt(0))?Character.toLowerCase(s.charAt(0)):Character.toUpperCase(s.charAt(0)));
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}