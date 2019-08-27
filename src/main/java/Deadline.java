import java.util.Date;

public class Deadline extends Task {
    //protected String date;
    protected Date date;
    protected boolean isDeadline;

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
