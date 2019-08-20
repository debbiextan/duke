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
                // print list of Tasks
                duke.listTask();
            }
            else {
                String[] DoneCheck = input.split("\\s+");
                if (DoneCheck[0].equals("done")) {
                    int num = Integer.parseInt(DoneCheck[1]) - 1;
                    duke.setTaskDone(num);
                } else {
                    // add new Task
                    Task t = new Task(input);
                    duke.addTask(t);
                }
            }
        }
    }

    private void setTaskDone(int i) {
        list.get(i).setDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("[" + list.get(i).getStatusIcon() + "] " + list.get(i).getDescription());
    }

    private void addTask(Task t) {
        list.add(t);
        System.out.println("added: " + t.getDescription());
    }

    private void listTask() {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < list.size(); i++) {
            int label = i + 1;
            System.out.println(label + ". [" + list.get(i).getStatusIcon() + "] " + list.get(i).getDescription());
        }
    }

    public Duke() {
        // constructor
    }
}
