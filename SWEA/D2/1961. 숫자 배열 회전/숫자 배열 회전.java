/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;                            
// double b = 1.0;               
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T;
        T=Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            bw.write("#"+test_case);
            bw.newLine();

            int n = Integer.parseInt(br.readLine());
            int[][] matrix = new int[n][n];
            inputMatrix(matrix, br);

            writeMatrix(cacheMatrix(matrix), bw);
        }
        bw.flush();
        br.close();
        bw.close();
    }

    private static void inputMatrix(int[][] matrix, BufferedReader br) throws IOException {
        for (int i = 0; i < matrix.length; i++) {
            int[] row = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            matrix[i]=row;
        }
    }

    // 회당 90도 회전
    private static int[][] rotateMatrix(int[][] matrix) {
        // 복사
        int[][] temp = Arrays.stream(matrix).map(int[]::clone).toArray(int[][]::new);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = matrix.length-1; j >= 0; j--) {
                matrix[i][j] = temp[matrix.length-j-1][i];
            }
        }
        return matrix;
    }

    private static List<int[][]> cacheMatrix(int[][] matrix) {
        List<int[][]> cache = new ArrayList<>();
        while(cache.size() < 3) {
            cache.add(Arrays.stream(rotateMatrix(matrix)).map(int[]::clone).toArray(int[][]::new));
        }
        return cache;
    }

    private static void writeMatrix(List<int[][]> cache, BufferedWriter bw) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cache.get(0).length; i++) {
            for (int[][] matrix : cache) {
                sb.append(Arrays.stream(matrix[i])
                                .mapToObj(String::valueOf)
                                .collect(Collectors.joining("")))
                        .append(" ");
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
    }
}