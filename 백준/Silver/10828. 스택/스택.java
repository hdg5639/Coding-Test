import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        List<String[]> data = new ArrayList<>();
        for(int i = 0; i < count; i++)
            data.add(br.readLine().split(" "));
        for (int i = 0; i < count; i++) {
            switch (data.get(i)[0]) {
                case "push":
                    stack.push(Integer.parseInt(data.get(i)[1]));
                    break;
                case "pop":
                    System.out.println(stack.isEmpty()?-1:stack.pop());
                    break;
                case "top":
                    System.out.println(stack.isEmpty()?-1:stack.peek());
                    break;
                case "size":
                    System.out.println(stack.size());
                    break;
                case "empty":
                    System.out.println(stack.isEmpty()?1:0);
                    break;
            }
        }
        br.close();
    }
}