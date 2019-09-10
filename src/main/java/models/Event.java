package models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    //protected String date;
    private Date date;
    private boolean isEvent;

    /**
     * Event constructor
     * @param description String variable of Event object containing description
     * @param date Date variable of Event object containing date
     */
    public Event(String description, Date date) {
        super(description);
        this.isEvent = true;
        this.date = date;
        this.type = "E";
    }

    /**
     * Get date of Event object
     * @return Date containing date
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Get type of Event object
     * @return String containing type "E"
     */
    @Override
    public String getType() {
        return "E";
    }

//    public void setDate(String date) {
//        this.date = date;
//    }

}
