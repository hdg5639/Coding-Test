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
		Scanner sc = new Scanner(System.in);

		int T;
		T = sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int n = sc.nextInt();

			int[][] matrix = new int[n][n];
			int[][] matrix90 = new int[n][n];
			int[][] matrix180 = new int[n][n];
			int[][] matrix270 = new int[n][n];

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					matrix[i][j] = sc.nextInt();
				}
			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					// 시계 방향 90도
					matrix90[i][j] = matrix[n - 1 - j][i];

					// 시계 방향 180도
					matrix180[i][j] = matrix[n - 1 - i][n - 1 - j];

					// 시계 방향 270도
					matrix270[i][j] = matrix[j][n - 1 - i];
				}
			}

			System.out.println("#" + test_case);

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					System.out.print(matrix90[i][j]);
				}

				System.out.print(" ");

				for (int j = 0; j < n; j++) {
					System.out.print(matrix180[i][j]);
				}

				System.out.print(" ");

				for (int j = 0; j < n; j++) {
					System.out.print(matrix270[i][j]);
				}

				System.out.println();
			}
		}

		sc.close();
	}
}