import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] size = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        String[][] board = new String[size[0]][size[1]];
        for (int i = 0; i < size[0]; i++) {
            board[i] = br.readLine().split("");
        }
        int min = 64;
        for (int row = 0; row <= size[0]-8; row++) {
            for (int col = 0; col <= size[1]-8; col++) {
                String[][] tmp = new String[size[0]][size[1]];
                for (int i = row; i < row+8; i++) {
                    tmp[i - row] = Arrays.copyOfRange(board[i], col, col+8);
                }
                min = Math.min(min, getTmp(tmp));
                if (min == 0)
                    break;
            }
            if (min == 0)
                break;
        }
        bw.write(min + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int getTmp(String[][] board) {
        int result = 64;
        String[] s = {"B", "W"};
        for (String start : s) {
            int tmp = 0;
            for (int mapRow = 0; mapRow < 8; mapRow++) {
                for (int mapCol = 0; mapCol < 8; mapCol++) {
                    if (mapRow % 2 == 0) {
                        if (board[mapRow][mapCol].equals(start)) {
                            if (mapCol % 2 != 0) tmp++;
                        } else if (mapCol % 2 == 0) tmp++;
                    } else {
                        if (!board[mapRow][mapCol].equals(start)) {
                            if (mapCol % 2 != 0) tmp++;
                        } else if (mapCol % 2 == 0) tmp++;
                    }
                }
            }
            if (result > tmp) result = tmp;
        }
        return result;
    }
}
