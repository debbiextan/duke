import java.io.*;
import java.util.ArrayList;

public class Settings {

    final String FILEPATH = "./duke.csv";

    public Settings() {
        // constructor
    }

    public void save(ArrayList<Task> tasks) {
        File file = null;
        PrintWriter writer = null;
        try {
            file = new File(FILEPATH);
            if (file.createNewFile()) {
                System.out.printf("Save file created at %s.\n", FILEPATH);
            }
            writer = new PrintWriter(file);
            for (Task t : tasks) {
                System.out.println(t.isDone);
            }
            for (Task t : tasks) {
                String type = t.getType();
                if (type.equals("T")) {
                    writer.printf("%s,%s,%s\n", t.type, t.isDone, t.description);
                } else if (type.equals("D")) {
                    Deadline d = (Deadline) t;
                    writer.printf("%s,%s,%s,%s\n", d.type, d.isDone, d.description, d.date);
                } else if (type.equals("E")) {
                    Event e = (Event) t;
                    writer.printf("%s,%s,%s,%s\n", e.type, e.isDone, e.description, e.date);
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
                        Deadline d = new Deadline(list[2], list[3]);
                        d.setDone(done);
                        tasks.add(d);
                    }
                    else if (list[0].equals("E")) {
                        // Event Task
                        Event e = new Event(list[2], list[3]);
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
