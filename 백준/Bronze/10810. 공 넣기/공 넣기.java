import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int[] value = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[][] result = new int [value[0]][2];

        for(int i = 0; i < value[1]; i++) {
            int[] input = Arrays.stream(br.readLine()
                    .split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            for(int j = input[0]-1; j < input[1]; j++) {
                if(result[j][0] != input[2]) {
                    result[j][0] = input[2];
                    result[j][1] = 1;
                } else {
                    ++result[j][1];
                }
            }
        }

        for(int[] array : result) {
            sb.append(array[0]).append(" ");
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}
