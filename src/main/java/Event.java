import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    //protected String date;
    protected Date date;
    protected boolean isEvent;

    public Event(String description, Date date) {
        super(description);
        this.isEvent = true;
        this.date = date;
        this.type = "E";
    }

    public Date getDate() {
        return this.date;
    }

//    public void setDate(String date) {
//        this.date = date;
//    }

    @Override
    public String getType() {
        return "E";
    }
}
