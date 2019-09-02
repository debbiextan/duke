package models;

import java.util.Date;

public class Deadline extends Task {
    //protected String date;
    private Date date;
    private boolean isDeadline;

    public Deadline(String description, Date date) {
        super(description);
        this.isDeadline = true;
        this.date = date;
        this.type = "D";
    }

    public Date getDate() {
        return this.date;
    }

//    public void setDate(String date) {
//        this.date = date;
//    }

    @Override
    public String getType() {
        return "D";
    }

//    public void printFormattedDate(Date date) {
//        SimpleDateFormat ft = new SimpleDateFormat("MMM dd");
//        System.out.println("(by: " + ft.format(date) + ")");
//    }
}
