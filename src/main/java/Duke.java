import models.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Duke {
    protected Storage storage = new Storage();
    //private ArrayList<Task> list = new ArrayList<>();
    private Ui ui = new Ui();
    private TaskList taskList;

    public void printTasksByKeyword(String keyword) throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task t : taskList.getTasks()) {
            if (t.getDescription().contains(keyword)) {
                tasks.add(t);
            }
        }
        if (tasks.isEmpty()) {
            throw new DukeException("No results with the keyword '" + keyword + "' was found.");
        }
        for (Task t: tasks){
            ui.printAccordingTaskType(taskList.getTasks().indexOf(t), t);
        }
    }

    public void deleteTask(String input) throws DukeException {
        String[] KeywordCheck = input.split("\\s+");
        int i = Integer.parseInt(KeywordCheck[1]);
        i -= 1; // reset to start at 0
        if (input.equals("delete")){
            throw new DukeException("Please indicate models.Task Number you want to delete.");
        }
        else if (i > taskList.getTasks().size() || i < 0) {
            throw new DukeException("This models.Task Number does not exist.");
        }
        ui.printAccordingTaskType(i, taskList.getTasks().get(i));
        taskList.deleteTask(i);
        storage.save(taskList.getTasks());
        System.out.println("Now you have " + taskList.getTasks().size() + " tasks in the list.");
    }

    public void setTaskDone(String input) throws DukeException {
        String[] KeywordCheck = input.split("\\s+");
        int i = Integer.parseInt(KeywordCheck[1]);
        i -= 1;
        if (input.equals("done")){
            throw new DukeException("Please indicate models.Task Number you want to have marked Done.");
        }
        else if (i > taskList.getTasks().size() || i < 0) {
            throw new DukeException("This models.Task Number does not exist.");
        }
        taskList.setTaskDone(i);
        storage.save(taskList.getTasks());
        System.out.println("Nice! I've marked this task as done: ");
        ui.printAccordingTaskType(i, taskList.getTasks().get(i));
    }

    public void addTask(String input) throws DukeException {
        if (input.equals("todo")) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        // process input into description
        Task t = new Task(input.substring(5));
        taskList.addTask(t);
        storage.save(taskList.getTasks());
        System.out.print("Got it. I've added this task: ");
        System.out.println("[T][\u2718] " + t.getDescription());
        System.out.println("Now you have " + taskList.getTasks().size() + " tasks in the list.");
    }

    public void addDeadline(String input) throws DukeException {
        if (input.equals("deadline")) {
            throw new DukeException("The description of a deadline cannot be empty.");
        } else if (!input.contains("/by")) {
            throw new DukeException("The deadline must be specified for a models.Deadline models.Task.");
        }
        // process input into description and date
        String[] KeywordCheck = input.split("/");
        String dateString = String.join("/", Arrays.copyOfRange(KeywordCheck, 1, KeywordCheck.length));
        Date date = Utilities.formatStringToDate(dateString.substring(3));
        //System.out.println(date);
        Deadline d = new Deadline(KeywordCheck[0].substring(9), date);
        taskList.addTask(d);
        storage.save(taskList.getTasks());
        System.out.print("Got it. I've added this task: ");
        System.out.println("[D][" + d.getStatusIcon() + "] " + d.getDescription() + "(by: " + ui.printDateToString(d.getDate()) + ")");
        System.out.println("Now you have " + taskList.getTasks().size() + " tasks in the list.");
    }

    public void addEvent(String input) throws DukeException {
        if (input.equals("event")) {
            throw new DukeException("The description of a event cannot be empty.");
        } else if (!input.contains("/at")) {
            throw new DukeException("The date/time must be specified for an models.Event models.Task.");
        }
        // process input into description and date
        String[] KeywordCheck = input.split("/");
        String dateString = String.join("/", Arrays.copyOfRange(KeywordCheck, 1, KeywordCheck.length));
        Date date = Utilities.formatStringToDate(dateString.substring(3));
        Event e = new Event(KeywordCheck[0].substring(6), date);
        taskList.addTask(e);
        storage.save(taskList.getTasks());
        System.out.print("Got it. I've added this task: ");
        System.out.println("[E][" + e.getStatusIcon() + "] " + e.getDescription() + "(at: " + ui.printDateToString(e.getDate()) + ")");
        System.out.println("Now you have " + taskList.getTasks().size() + " tasks in the list.");
    }

    public void run(){
        ui.greet();

        Scanner sc = new Scanner(System.in);
        String input;

        while (sc.hasNextLine()) {
            input = sc.nextLine();
            String[] KeywordCheck = input.split("/");
            String[] instruction = KeywordCheck[0].split("\\s+");

            switch (instruction[0]) {
                case "bye":
                    ui.exit();

                case "list":
                    ui.listTasks(taskList.getTasks());
                    break;

                case "done":
                    try {
                        setTaskDone(input);
                    }
                    catch (DukeException errMsg) {
                        System.out.println(errMsg.toString());
                    }
                    break;

                case "todo":
                    try {
                        addTask(input);
                    } catch (DukeException errMsg) {
                        System.out.println(errMsg.toString());
                    }
                    break;

                case "deadline":
                    try {
                        addDeadline(input);
                    } catch (DukeException errMsg) {
                        System.out.println(errMsg.toString());
                    }
                    break;

                case "event":
                    try {
                        addEvent(input);

                    } catch (DukeException errMsg) {
                        System.out.println(errMsg.toString());
                    }
                    break;
                case "delete":
                    try {
                        System.out.println("Noted. I've removed this task: ");
                        deleteTask(input);
                    }
                    catch (DukeException errMsg) {
                        System.out.println(errMsg.toString());
                    }
                    break;
                case "find":
                    try {
                        System.out.println("Here are the matching tasks in your list: ");
                        String keyword = input.substring(5);
                        printTasksByKeyword(keyword);
                    }
                    catch (DukeException errMsg) {
                        System.out.println(errMsg.toString());
                    }
                    break;
                default:
                    System.out.println("I'm sorry, but I don't know what that means :-(");
            }
        }
        sc.close();
    }

    public Duke() {
        // constructor
        ui = new Ui();
        storage = new Storage();
        taskList = new TaskList(storage.load());
    }

    public Duke(String filePath) {
        // constructor
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.load());
    }
}
