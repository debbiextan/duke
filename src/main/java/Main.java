import java.text.ParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n\tWhat can I do for you?");

        Duke duke = new Duke();
        duke.load();
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
                    catch (DukeException errMsg) {
                        System.out.println(errMsg.toString());
                    }
                    break;

                case "todo":
                    try {
                        duke.addTask(input);
                    } catch (DukeException errMsg) {
                        System.out.println(errMsg.toString());
                    }
                    break;

                case "deadline":
                    try {
                        duke.addDeadline(input);
                    } catch (DukeException errMsg) {
                        System.out.println(errMsg.toString());
                    }
                    break;

                case "event":
                    try {
                        duke.addEvent(input);
                    } catch (DukeException errMsg) {
                        System.out.println(errMsg.toString());
                    }
                    break;
                default:
                    System.out.println("I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}
