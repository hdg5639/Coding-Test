import java.util.*;
import java.io.*;

public class Main {
    static int[] dr = {0, 1, 0, -1}, dc = {1, 0, -1, 0};
    static class Node {
        int row, col;
        int time;

        Node(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken()), W = Integer.parseInt(st.nextToken());
        char[][] matrix = new char[H][W];
        int sr = 0, sc = 0, tr = 0, tc = 0;
        for(int row = 0; row < H; row++) {
            char[] input = br.readLine().toCharArray();
            for(int col = 0; col < W; col++) {
                if(input[col] == 'S') {
                    sr = row; sc = col;
                }
                if(input[col] == 'T') {
                    tr = row; tc = col;
                }
                matrix[row][col] = input[col];
            }
        }

        boolean[][][] visited = new boolean[2][H][W];
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(sr, sc, 0));
        visited[0][sr][sc] = true;
        int minTime = -1;
        while(!queue.isEmpty()) {
            Node curr = queue.poll();
            int currRow = curr.row;
            int currCol = curr.col;
            int currTime = curr.time;
            int nextTime = currTime + 1;
            int nextParity = nextTime % 2;

            if(currRow == tr && currCol == tc) {
                minTime = currTime;
                break;
            }

            if(matrix[currRow][currCol] == '+' && !visited[nextParity][currRow][currCol]) {
                visited[nextParity][currRow][currCol] = true;
                queue.offer(new Node(currRow, currCol, nextTime));
            }

            for(int idx = 0; idx < 4; idx++) {
                int nextRow = currRow + dr[idx];
                int nextCol = currCol + dc[idx];
                if(nextRow < 0 || nextCol < 0 || nextRow >= H || nextCol >= W) continue;
                if(matrix[nextRow][nextCol] == '#') continue;
                if(matrix[nextRow][nextCol] == '0' || matrix[nextRow][nextCol] == '1') {
                    if(nextParity != matrix[nextRow][nextCol] - '0') continue;
                }
                if(visited[nextParity][nextRow][nextCol]) continue;

                visited[nextParity][nextRow][nextCol] = true;
                queue.offer(new Node(nextRow, nextCol, nextTime));
            }
        }

        System.out.println(minTime);
    }
}