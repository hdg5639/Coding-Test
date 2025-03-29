import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Main {
    public static void main(String[] args) throws IOException {
        Date today = new Date();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        System.out.println(formatter.format(today));
    }
}