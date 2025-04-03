import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
       int count = Integer.parseInt(br.readLine());
       int[] value = new int[count];

       for (int i = 0; i < count; i++)
           value[i] = Integer.parseInt(br.readLine());

       Arrays.sort(value);

       for(int v : value)
           bw.write(v + "\n");

       bw.flush();
       br.close();
    }
}