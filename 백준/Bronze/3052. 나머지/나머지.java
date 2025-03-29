import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < 10; i++)
            list.add(Integer.parseInt(br.readLine()));
        list = list.stream().map(i -> i%42).collect(Collectors.toList());
        List<Integer> counts =  new ArrayList<>(Collections.nCopies(42, 0));
        counts = counts.stream().map(i -> 0).collect(Collectors.toList());
        for(int num : list){
            counts.set(num, counts.get(num) + 1);
        }
        System.out.println(counts.stream().filter(i -> i != 0).count());
    }
}