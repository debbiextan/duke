import jdk.internal.org.objectweb.asm.tree.IntInsnNode;
import sun.jvm.hotspot.utilities.ObjectReader;

import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Duke {
    protected Settings settings = new Settings();
    private ArrayList<Task> list = new ArrayList<>();

    public Date formatStringToDate(String date) {
        Date formatted;
        try {
            DateFormat ft = new SimpleDateFormat ("d/MM/yyyy HHmm");
            formatted = ft.parse(date);
            return formatted;
        }
        catch (ParseException e) {
            System.out.println("Invalid date format!");
        }
        return null;
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

    public String formatDateToString(Date date) {
        // Format to Print: 2nd of December 2019, 6pm
        DateFormat df = new SimpleDateFormat ("d/MM/yyyy HHmm");
        String formatted = df.format(date);

        return formatted;
    }

    private String getDateSuffix(String num) {
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

    public void load() {
        list = settings.load();
    }

    public void setTaskDone(String input) throws DukeException {
        String[] KeywordCheck = input.split("\\s+");
        int i = Integer.parseInt(KeywordCheck[1]);
        i -= 1;
        if (input.equals("done")){
            throw new DukeException("Please indicate Task Number you want to have marked Done.");
        }
        else if (i > list.size() || i < 0) {
            throw new DukeException("This Task Number does not exist.");
        }
        list.get(i).setDone(true);
        settings.save(list);
        System.out.println("Nice! I've marked this task as done: ");
        printAccordingTaskType(i);
    }

    public void addTask(String input) throws DukeException {
        if (input.equals("todo")) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        // process input into description
        Task t = new Task(input.substring(5));
        list.add(t);
        settings.save(list);
        System.out.print("Got it. I've added this task: ");
        System.out.println("[T][✗] " + t.getDescription());
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public void addDeadline(String input) throws DukeException {
        if (input.equals("deadline")) {
            throw new DukeException("The description of a deadline cannot be empty.");
        } else if (!input.contains("/by")) {
            throw new DukeException("The deadline must be specified for a Deadline Task.");
        }
        // process input into description and date
        String[] KeywordCheck = input.split("/");
        String dateString = String.join("/", Arrays.copyOfRange(KeywordCheck, 1, KeywordCheck.length));
        Date date = formatStringToDate(dateString.substring(3));
        System.out.println(date);
        Deadline d = new Deadline(KeywordCheck[0].substring(9), date);
        list.add(d);
        settings.save(list);
        System.out.print("Got it. I've added this task: ");
        System.out.println("[D][" + d.getStatusIcon() + "] " + d.getDescription() + "(by: " + printDateToString(d.getDate()) + ")");
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public void addEvent(String input) throws DukeException {
        if (input.equals("event")) {
            throw new DukeException("The description of a event cannot be empty.");
        } else if (!input.contains("/at")) {
            throw new DukeException("The date/time must be specified for an Event Task.");
        }
        // process input into description and date
        String[] KeywordCheck = input.split("/");
        Date date = formatStringToDate(KeywordCheck[1].substring(3));
        Event e = new Event(KeywordCheck[0].substring(6), date);
        list.add(e);
        settings.save(list);
        System.out.print("Got it. I've added this task: ");
        System.out.println("[E][" + e.getStatusIcon() + "] " + e.getDescription() + "(at: " + printDateToString(e.getDate()) + ")");
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public void listTask() {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < list.size(); i++) {
            printAccordingTaskType(i);
        }
    }

    public void printAccordingTaskType(int i) {
        int label = i+1;
        if (list.get(i).getType().equals("T")) {
            System.out.println(label + ". [" + list.get(i).getType() + "][" + list.get(i).getStatusIcon() + "] " + list.get(i).getDescription());
        }
        else if (list.get(i).getType().equals("D")) {
            Deadline d = (Deadline) list.get(i);
            String date = printDateToString(d.getDate());
            System.out.println(label + ". [" + d.getType() + "][" + d.getStatusIcon() + "] " + d.getDescription() + "(by: " + date + ")");
        }
        else if (list.get(i).getType().equals("E")) {
            Event e = (Event) list.get(i);
            String date = printDateToString(e.getDate());
            System.out.println(label + ". [" + e.getType() + "][" + e.getStatusIcon() + "] " + e.getDescription() + "(at: " + date + ")");
        }
    }

    public Duke() {
        // constructor
    }
}
