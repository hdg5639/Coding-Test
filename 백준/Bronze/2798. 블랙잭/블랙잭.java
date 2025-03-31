import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       int[] n = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray() ,
               cards = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
        System.out.println(search(n[1], cards));
    }
    private static long search(int target, int[] cards){
        int result = target;
        for(int card : cards){
            for(int card2 : Arrays.stream(cards)
                    .filter(card2 -> target - card > card2 && card != card2).toArray()){
                for(int card3 : Arrays.stream(cards)
                        .filter(card3 -> target - (card + card2) >= card3
                        && card != card3 && card2 != card3).toArray()){
                    int tmp = card + card2 + card3;
                    if(result > target - tmp) result = target - tmp;
                }
            }
        }
        return target - result;
    }
}