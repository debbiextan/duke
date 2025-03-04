import models.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    //private ArrayList<Task> list = new ArrayList<>();
    private Ui ui;
    private TaskList taskList;

    /**
     * Determines type of task by keyword used
     * @param keyword first word of input by user
     * @throws DukeException if no further instruction is given after keyword
     */
    private void printTasksByKeyword(String keyword) throws DukeException {
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

    /**
     * Processes user input and deletes task indicated through task number
     * @param input full instruction given by user
     * @throws DukeException if task number is invalid or does not exist
     */
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

    /**
     * Processes user input and marks Task as complete indicated through task number
     * @param input full instruction given by user
     * @throws DukeException if task number is invalid or does not exist
     */
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

    /**
     * Processes input and adds a new Todo task
     * @param input full instruction given by user
     * @throws DukeException if task description is empty
     */
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

    /**
     * Processes input and adds a new deadline task with date
     * @param input full instruction given by user
     * @throws DukeException if task description is empty or date is not indicated
     */
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

    /**
     * Processes input and adds a new event task with date
     * @param input full instruction given by user
     * @throws DukeException if task description is empty or a date is not indicated
     */
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

    /**
     * Decides what instruction to run based on user input read by Scanner
     */
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

    /**
     * Duke constructor
     */
    public Duke() {
        // constructor
        ui = new Ui();
        storage = new Storage();
        taskList = new TaskList(storage.load());
    }

    /**
     * Override method for Duke constructor
     * @param filePath if .csv load file is exists
     */
    public Duke(String filePath) {
        // constructor
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.load());
    }
}
