import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            bw.write(line + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}