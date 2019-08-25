import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    protected String date;
    protected boolean isEvent;

    public Event(String description, String date) {
        super(description);
        this.isEvent = true;
        this.date = getDate();
        this.type = "E";
    }

    public String getDate() {
        return this.date;
    }

    @Override
    public String getType() {
        return "E";
    }

//    public void printFormattedDate(Date startDateTime, Date endDateTime) {
//        SimpleDateFormat ft = new SimpleDateFormat ("MMM dd h");
//        SimpleDateFormat ft2 = new SimpleDateFormat("ha");
//
//        System.out.println("(at: " + ft.format(startDateTime) + "-" + ft2.format(endDateTime) + ")");
//    }
}
