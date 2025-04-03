import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = new String[3];
        for(int i = 0; i < 3; i ++) input[i] = br.readLine();

        boolean found = false;
        int tmp = 0, result;
        for (int i = 0; i < input.length; i++) {
            if  (input[i].chars().allMatch(Character::isDigit)){
                tmp = i;
                found = true;
            }
        }

        result = 2 - tmp + Integer.parseInt(input[tmp]) + 1;
        System.out.println(found?result%3==0?result%5==0?"FizzBuzz":"Fizz":result%5==0?"Buzz":result:"FizzBuzz");

        br.close();
    }
}