import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n\tWhat can I do for you?");

        Scanner sc = new Scanner(System.in);
        Task[] list = new Task[100];
        String input;
        int n = 0; // list size

        while (true) {
            input = sc.nextLine();
            String[] DoneCheck = input.split("\\s+");
            if (DoneCheck[0].equals("done")) {
                int num = Integer.parseInt(DoneCheck[1]) - 1;
                list[num].markAsDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("[" + list[num].getStatusIcon() + "] " + list[num].description);
            }
            else {

                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                }
                else if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list: ");
                    // print numbered list
                    for (int i = 0; i < n; i++) {
                        int label = i + 1;
                        System.out.println(label + ". [" + list[i].getStatusIcon() + "] " + list[i].description);
                        //System.out.println(label + ". [" + list[0][i] + "]" + list[1][i]);
                    }
                }
                else {
                    Task t = new Task(input);
                    list[n] = t;
                    System.out.println("added: " + list[n].description);
                    n++;
                }
            }
        }
    }

    public String UpdateDone(String icon){
        if (icon.equals("✗")) {
            icon = "✓";
        }
        return icon;
    }
}
