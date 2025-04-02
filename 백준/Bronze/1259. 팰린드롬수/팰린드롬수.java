import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       List<long[]> nums = new ArrayList<>();
       while (true){
           nums.add(Arrays.stream(Arrays.stream(br.readLine().split(""))
                            .mapToLong(Long::parseLong).toArray())
                            .toArray());
           if ( nums.get(nums.size()-1)[0] == 0) {
               nums.remove(nums.size()-1);
               break;
           }
       }
       nums.forEach(num -> {
           boolean check = true;
           for(int i = 0; i <= num.length / 2; i++) {
               if (num[i] != num[num.length - i - 1]) {
                   check = false;
                   break;
               }
           }
           System.out.println(check?"yes":"no");
       });
    }
}