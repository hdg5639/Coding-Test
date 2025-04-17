import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력: 체스판 사이즈
        StringTokenizer st = new StringTokenizer(br.readLine());
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());

        // 입력: 체스판 상태
        char[][] board = new char[row][col];
        for (int i = 0; i < row; i++) {
            board[i] = br.readLine().toCharArray();
        }

        int minChange = Integer.MAX_VALUE;

        // 8x8 체스판 범위 순회
        for (int i = 0; i <= row - 8; i++) {
            for (int j = 0; j <= col - 8; j++) {
                minChange = Math.min(minChange, countRepaint(board, i, j));
                if (minChange == 0) break; // 더 이상 계산할 필요 없음
            }
        }

        System.out.println(minChange);
    }

    // 8x8 영역에서 다시 칠해야 할 최소 횟수 계산
    private static int countRepaint(char[][] board, int startRow, int startCol) {
        int repaintB = 0; // 시작이 'B'인 경우
        int repaintW = 0; // 시작이 'W'인 경우

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                char current = board[startRow + i][startCol + j];
                // 기대되는 색 결정
                char expectedB = ((i + j) % 2 == 0) ? 'B' : 'W';
                char expectedW = ((i + j) % 2 == 0) ? 'W' : 'B';

                if (current != expectedB) repaintB++;
                if (current != expectedW) repaintW++;
            }
        }

        return Math.min(repaintB, repaintW);
    }
}
