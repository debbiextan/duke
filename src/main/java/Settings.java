import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class Settings {

    final String FILEPATH = "./duke.csv";

    public Settings() {
        // constructor
    }

    public void save(ArrayList<Task> tasks) {
        File file = null;
        PrintWriter writer = null;
        Duke duke = new Duke();
        try {
            file = new File(FILEPATH);
            if (file.createNewFile()) {
                System.out.printf("Save file created at %s.\n", FILEPATH);
            }
            writer = new PrintWriter(file);
            for (Task t : tasks) {
                String type = t.getType();
                 if (t instanceof Deadline) {
                    Deadline d = (Deadline) t;
                    String date = duke.formatDateToString(d.date);
                    writer.printf("%s,%s,%s,%s\n", d.type, d.isDone, d.description, date);
                } else if (t instanceof Event) {
                    Event e = (Event) t;
                    String date = duke.formatDateToString(e.date);
                    writer.printf("%s,%s,%s,%s\n", e.type, e.isDone, e.description, date);
                }
                else {
                    writer.printf("%s,%s,%s\n", t.type, t.isDone, t.description);
                }
            }
            writer.close();
        }
        catch (FileNotFoundException fnfe) {
            System.out.printf("File at %s does not exist.\n", FILEPATH);
        } catch (IOException e) {
            System.out.printf("Error writing to File at %s.\n", FILEPATH);
        }
    }

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            File file = new File(FILEPATH);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            Duke duke = new Duke();

            while ((line = br.readLine()) != null) {
                String[] list = line.split(",");
                if (list.length < 3 || list.length > 4) {
                    System.out.println("Invalid CSV! Exiting...");
                    System.exit(0);
                }
                else {
                    Task t = new Task(list[2]);
                    boolean done = Boolean.parseBoolean(list[1]);
                    if (list[0].equals("T")) {
                        // To Do Task
                        t.setDone(done);
                        tasks.add(t);
                    }
                    else if (list[0].equals("D")) {
                        // Deadline Task
                        Date date = duke.formatStringToDate(list[3]);
                        Deadline d = new Deadline(list[2], date);
                        d.setDone(done);
                        tasks.add(d);
                    }
                    else if (list[0].equals("E")) {
                        // Event Task
                        Date date = duke.formatStringToDate(list[3]);
                        Event e = new Event(list[2], date);
                        e.setDone(done);
                        tasks.add(e);
                    }
                }
            }
        }
        catch (IOException e) {
            System.out.printf("Unable to read File at %s\n", FILEPATH);
        }
        return tasks;
    }
}
