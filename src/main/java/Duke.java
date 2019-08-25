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

        while (true) {
            input = sc.nextLine();
            if (input.isEmpty()) {
                System.out.println("Please do not leave blanks!");
            }
            else if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if (input.equals("list")) {
                // print list of Tasks + types
                duke.listTask();
            }
            else {
                String[] KeywordCheck = input.split("/");
                String[] instruction = KeywordCheck[0].split("\\s+");
                if (KeywordCheck.length > 1) {
                    String[] date = KeywordCheck[1].split("\\s+");
                    if (instruction[0].equals("deadline") && date[0].equals("by")) {
                        // add new Deadline
                        duke.addDeadline(KeywordCheck[0], KeywordCheck[1]);
                    } else if (instruction[0].equals("event") && date[0].equals("at")) {
                        // add new Event
                        duke.addEvent(KeywordCheck[0], KeywordCheck[1]);
                    }
                }
                else {
                    if (instruction[0].equals("done")) {
                        int num = Integer.parseInt(instruction[1]) - 1;
                        duke.setTaskDone(num);
                    }
                    else if (instruction[0].equals("todo")) {
                        duke.addTask(input);
                    }
                    else {
                        System.out.println("Invalid Input!");
                    }
                }
            }
        }
    }

    private void setTaskDone(int i) {
        list.get(i).setDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("[" + list.get(i).getStatusIcon() + "] " + list.get(i).getDescription());
    }

    private void addTask(String input) {
        // process input into description
        Task t = new Task(input.substring(5));
        list.add(t);
        System.out.print("Got it. I've added this task: ");
        System.out.println("[T][✗] " + t.getDescription());
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    private void addDeadline(String instruction, String date) {
        // process input into description and date
        Deadline d = new Deadline(instruction.substring(9), date.substring(3));
        list.add(d);
        System.out.print("Got it. I've added this task: ");
        System.out.println("[D][" + d.getStatusIcon() + "] " + d.getDescription());
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    private void addEvent(String instruction, String date) {
        // process input into description and date
        Event e = new Event(instruction.substring(6), date.substring(3));
        list.add(e);
        System.out.print("Got it. I've added this task: ");
        System.out.println("[E][" + e.getStatusIcon() + "] " + e.getDescription());
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    private void listTask() {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < list.size(); i++) {
            int label = i + 1;
            System.out.println(label + ". [" + list.get(i).getType() + "][" + list.get(i).getStatusIcon() + "] " + list.get(i).getDescription());
        }
    }

    public Duke() {
        // constructor
    }
}
