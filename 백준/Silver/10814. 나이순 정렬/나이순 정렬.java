import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int count = Integer.parseInt(br.readLine()), check = 0;
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String[] input = br.readLine().split(" ");
            people.add(new Person(Integer.parseInt(input[0]), input[1], i));
        }

        people.sort(Comparator
                .comparingInt((Person p) -> p.age)
                .thenComparing(p -> p.count));

        StringBuilder builder = new StringBuilder();
        for (Person p : people)
            builder.append(p.age).append(" ").append(p.name).append("\n");

        bw.write(builder.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static class Person {
        int age;
        String name;
        int count;

        public Person(int age, String name, int count){
            this.age = age;
            this.name = name;
            this.count = count;
        }
    }
}