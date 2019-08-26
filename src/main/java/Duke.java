import java.security.Key;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private ArrayList<Task> list = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n\tWhat can I do for you?");

        Duke duke = new Duke();
        Scanner sc = new Scanner(System.in);
        String input;

        while (sc.hasNextLine()) {
            input = sc.nextLine();
            String[] KeywordCheck = input.split("/");
            String[] instruction = KeywordCheck[0].split("\\s+");

            switch (instruction[0]) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    System.exit(0);

                case "list":
                    // print list of Tasks + types
                    duke.listTask();
                    break;

                case "done":
                    try {
                        //int num = Integer.parseInt(instruction[1]) - 1;
                        duke.setTaskDone(input);
                    }
                    catch (DukeExceptions errMsg) {
                        System.out.println(errMsg.toString());
                    }
                    break;

                case "todo":
                    try {
                        duke.addTask(input);
                    } catch (DukeExceptions errMsg) {
                        System.out.println(errMsg.toString());
                    }
                    break;

                case "deadline":
                    try {
                        duke.addDeadline(input);
                    } catch (DukeExceptions errMsg) {
                        System.out.println(errMsg.toString());
                    }
                    break;

                case "event":
                    try {
                        duke.addEvent(input);
                    } catch (DukeExceptions errMsg) {
                        System.out.println(errMsg.toString());
                    }
                    break;
                default:
                    System.out.println("I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    public void setTaskDone(String input) throws DukeExceptions {
        String[] KeywordCheck = input.split("\\s+");
        int i = Integer.parseInt(KeywordCheck[1]);
        i -= 1;
        if (input.equals("done")){
            throw new DukeExceptions("Please indicate Task Number you want to have marked Done.");
        }
        else if (i > list.size() || i < 0) {
            throw new DukeExceptions("This Task Number does not exist.");
        }
        list.get(i).setDone();
        System.out.println("Nice! I've marked this task as done: ");
        printAccordingTaskType(i);
        //System.out.println("[" + list.get(i).getStatusIcon() + "] " + list.get(i).getDescription());
    }

    public void addTask(String input) throws DukeExceptions {
        if (input.equals("todo")) {
            throw new DukeExceptions("The description of a todo cannot be empty.");
        }
        // process input into description
        Task t = new Task(input.substring(5));
        list.add(t);
        System.out.print("Got it. I've added this task: ");
        System.out.println("[T][✗] " + t.getDescription());
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public void addDeadline(String input) throws DukeExceptions {
        if (input.equals("deadline")) {
            throw new DukeExceptions("The description of a deadline cannot be empty.");
        }
        else if (!input.contains("/by")) {
            throw new DukeExceptions("The deadline must be specified for a Deadline Task.");
        }
        // process input into description and date
        String[] KeywordCheck = input.split("/");
        Deadline d = new Deadline(KeywordCheck[0].substring(9), KeywordCheck[1].substring(3));
        list.add(d);
        System.out.print("Got it. I've added this task: ");
        System.out.println("[D][" + d.getStatusIcon() + "] " + d.getDescription() + "(by: " + d.getDate() + ")");
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public void addEvent(String input) throws DukeExceptions {
        if (input.equals("event")) {
            throw new DukeExceptions("The description of a event cannot be empty.");
        }
        else if (!input.contains("/at")) {
            throw new DukeExceptions("The date/time must be specified for an Event Task.");
        }
        // process input into description and date
        String[] KeywordCheck = input.split("/");
        Event e = new Event(KeywordCheck[0].substring(6), KeywordCheck[1].substring(3));
        list.add(e);
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
