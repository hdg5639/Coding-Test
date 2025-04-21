import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int count = Integer.parseInt(br.readLine());
        int[] arr = new int[count];
        for (int i = 0; i < count; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        bw.write(avg(arr) + "\n" + median(arr) + "\n" + mode(arr) + "\n" + range(arr) + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
    private static long avg(int[] arr) {
        return Math.round((double)Arrays.stream(arr).sum() / arr.length);
    }
    private static int median(int[] arr) {
        Arrays.sort(arr);
        return arr[arr.length / 2];
    }
    private static int mode(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int j : arr) {
            map.put(j, map.getOrDefault(j, 0) + 1);
        }
        int result = Collections.max(map.values());
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == result)
                list.add(entry.getKey());
        }
        Collections.sort(list);

        return list.size()==1?list.get(0):list.get(1);
    }
    private static int range(int[] arr) {
        Arrays.sort(arr);
        return arr[arr.length-1] - arr[0];
    }
}