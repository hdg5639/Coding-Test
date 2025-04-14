import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int weight = Integer.parseInt(br.readLine()), count = 0;
        if (weight % 3 == 0 || weight > 7) {
            int tmp = weight;
            while ( tmp % 5 != 0) {
                if (tmp < 0) {
                    sb.append(-1);
                    bw.write(sb.toString());
                    bw.flush();
                    bw.close();
                    br.close();
                    return;
                }
                tmp -= 3;
                count++;
            }
            count += tmp/5;

        } else if (weight % 5 == 0) {
            count += weight / 5;
        } else count = -1;
        sb.append(count);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}