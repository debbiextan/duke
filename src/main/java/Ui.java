import models.Deadline;
import models.Event;
import models.Task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Ui {
    // Deals with user interactions
    public Ui() {
        //Constructor
    }

    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n\tWhat can I do for you?");
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0); // ends program
    }

    public void listTasks(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < tasks.size(); i++) {
            printAccordingTaskType(i, tasks.get(i));
        }
    }

    public void printAccordingTaskType(int i, Task task) {
        int label = i+1;
        if (task.getType().equals("T")) {
            System.out.println(label + ". [" + task.getType() + "][" + task.getStatusIcon() + "] " + task.getDescription());
        }
        else if (task.getType().equals("D")) {
            Deadline d = (Deadline) task;
            String date = printDateToString(d.getDate());
            System.out.println(label + ". [" + d.getType() + "][" + d.getStatusIcon() + "] " + d.getDescription() + "(by: " + date + ")");
        }
        else if (task.getType().equals("E")) {
            Event e = (Event) task;
            String date = printDateToString(e.getDate());
            System.out.println(label + ". [" + e.getType() + "][" + e.getStatusIcon() + "] " + e.getDescription() + "(at: " + date + ")");
        }
    }

    public String printDateToString(Date date) {
        // Format to Print: 2nd of December 2019, 6pm
        DateFormat df = new SimpleDateFormat("d 'of' MMMM yyyy, ha");
        String formatted = df.format(date);
        ArrayList<String> dated = new ArrayList<String>(Arrays.asList(formatted.split("\\s+")));
        String suffix = getDateSuffix(dated.get(0));

        dated.add(1, suffix);
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (String s : dated) {
            sb.append(s);
            if (count > 0 && count < dated.size()-1) {
                sb.append(" ");
            }
            count++;
        }

        return sb.toString();
    }

    public String getDateSuffix(String num) {
        int n = Integer.parseInt(num);
        if (n == 11 && n <= 13) {
            return "th";
        }
        switch (n % 10) {
            case 1:  return "st";
            case 2:  return "nd";
            case 3:  return "rd";
            default: return "th";
        }
    }

}
