import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String target = br.readLine();
        int count = 0, remain = str.length();
        boolean flag = true;
        while(str.length() >= target.length()) {
            if(str.contains(target)) {
                str = str.replaceFirst(target, "");
                count++;
                remain -= target.length();
                continue;
            } else {
                break;
            }
        }
        
        System.out.println(count + " " + remain);
    }
}
