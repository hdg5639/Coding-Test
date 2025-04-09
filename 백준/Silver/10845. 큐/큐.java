import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        Queue<Integer> queue = new LinkedList<>();
        List<String[]> data = new ArrayList<>();
        for(int i = 0; i < count; i++)
            data.add(br.readLine().split(" "));
        String last = "-1";
        for (int i = 0; i < count; i++) {
            if(queue.isEmpty()) last = "-1";
            switch (data.get(i)[0]) {
                case "push":
                    queue.add(Integer.parseInt(data.get(i)[1]));
                    last = data.get(i)[1];
                    break;
                case "pop":
                    System.out.println(queue.isEmpty()?-1:queue.poll());
                    break;
                case "front":
                    System.out.println(queue.isEmpty()?-1:queue.element());
                    break;
                case "back":
                    System.out.println(last);
                    break;
                case "size":
                    System.out.println(queue.size());
                    break;
                case "empty":
                    System.out.println(queue.isEmpty()?1:0);
                    break;
            }
        }
        br.close();
    }
}