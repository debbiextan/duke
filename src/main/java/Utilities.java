import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Utilities {

    public static Date formatStringToDate(String date) {
        Date formatted;
        try {
            DateFormat ft = new SimpleDateFormat("d/MM/yyyy HHmm");
            formatted = ft.parse(date);
            return formatted;
        }
        catch (ParseException e) {
            System.out.println("Invalid date format!");
        }
        return null;
    }

    public static String formatDateToString(Date date) {
        // Format to Print: 2nd of December 2019, 6pm
        DateFormat df = new SimpleDateFormat ("d/MM/yyyy HHmm");
        String formatted = df.format(date);

        return formatted;
    }
}
