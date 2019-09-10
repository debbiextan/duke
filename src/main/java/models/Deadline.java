package models;

import java.util.Date;

public class Deadline extends Task {
    //protected String date;
    private Date date;
    private boolean isDeadline;

    /**
     * Deadline constructor
     * @param description String variable containing Deadline object description
     * @param date Date variable containing Deadline object date
     */
    public Deadline(String description, Date date) {
        super(description);
        this.isDeadline = true;
        this.date = date;
        this.type = "D";
    }

    /**
     * Get date of Deadline object
     * @return Date variable containing Deadline object date
     */
    public Date getDate() {
        return this.date;
    }

//    public void setDate(String date) {
//        this.date = date;
//    }

    /**
     * Get type of Deadline object
     * @return String variable containing Deadline object type "D"
     */
    @Override
    public String getType() {
        return "D";
    }

//    public void printFormattedDate(Date date) {
//        SimpleDateFormat ft = new SimpleDateFormat("MMM dd");
//        System.out.println("(by: " + ft.format(date) + ")");
//    }
}
