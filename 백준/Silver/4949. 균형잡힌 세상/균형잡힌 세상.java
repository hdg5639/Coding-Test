import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        for (String[] line = br.readLine().split("");
             Objects.requireNonNull(line).length!=1 && !line[0].equals(".");
             line = br.readLine().split("")) {
            line = Arrays.stream(line)
                    .filter(s -> s.equals("(") || s.equals(")") ||
                            s.equals("[") || s.equals("]") || s.equals(".")).toArray(String[]::new);
            int tmp = 0;
            Stack<String> stack = new Stack<>();
            boolean flag = true;
            for (String word : line) {
                if (tmp < 0 || !flag) {
                    flag = false;
                    break;
                }
                switch (word) {
                    case "(" :
                        tmp++;
                        stack.push(word);
                        break;
                    case "[" :
                        tmp += 2;
                        stack.push(word);
                        break;
                    case ")" :
                        tmp--;
                        if (stack.isEmpty() || !stack.peek().equals("(")) {
                            flag = false;
                            break;
                        } else stack.pop();
                        break;
                    case "]" :
                        tmp -= 2;
                        if (stack.isEmpty() || !stack.peek().equals("[")) {
                            flag = false;
                            break;
                        } else stack.pop();
                        break;
                }
            }
            if (tmp != 0 || !flag || !stack.isEmpty()) {
                sb.append("no").append("\n");
            } else sb.append("yes").append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
