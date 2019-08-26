import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    protected Settings settings = new Settings();
    private ArrayList<Task> list = new ArrayList<>();

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
        }
        else if (!input.contains("/by")) {
            throw new DukeException("The deadline must be specified for a Deadline Task.");
        }
        // process input into description and date
        String[] KeywordCheck = input.split("/");
        Deadline d = new Deadline(KeywordCheck[0].substring(9), KeywordCheck[1].substring(3));
        list.add(d);
        settings.save(list);
        System.out.print("Got it. I've added this task: ");
        System.out.println("[D][" + d.getStatusIcon() + "] " + d.getDescription() + "(by: " + d.getDate() + ")");
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public void addEvent(String input) throws DukeException {
        if (input.equals("event")) {
            throw new DukeException("The description of a event cannot be empty.");
        }
        else if (!input.contains("/at")) {
            throw new DukeException("The date/time must be specified for an Event Task.");
        }
        // process input into description and date
        String[] KeywordCheck = input.split("/");
        Event e = new Event(KeywordCheck[0].substring(6), KeywordCheck[1].substring(3));
        list.add(e);
        settings.save(list);
        System.out.print("Got it. I've added this task: ");
        System.out.println("[E][" + e.getStatusIcon() + "] " + e.getDescription() + "(at: " + e.getDate() + ")");
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
            System.out.println(label + ". [" + d.getType() + "][" + d.getStatusIcon() + "] " + d.getDescription() + "(by: " + d.getDate() + ")");
        }
        else if (list.get(i).getType().equals("E")) {
            Event e = (Event) list.get(i);
            System.out.println(label + ". [" + e.getType() + "][" + e.getStatusIcon() + "] " + e.getDescription() + "(at: " + e.getDate() + ")");
        }
    }

    public Duke() {
        // constructor
    }
}
