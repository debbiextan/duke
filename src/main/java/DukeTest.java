import static org.junit.jupiter.api.Assertions.*;
import models.DukeException;
import models.Task;
import models.TaskList;
import org.junit.jupiter.api.Test;

class DukeTest {
    private String instruction = "";
    private Duke duke = new Duke();
    private TaskList list;

    @Test
    void deleteTask() throws DukeException {
        instruction = "delete 1";
        String original = list.getTasks().get(0).getDescription();
        duke.deleteTask(instruction);
        String after = list.getTasks().get(0).getDescription();
        // assertEquals(a,b) == a.equals(b)
        assertEquals(original, after);
    }

    @Test
    void setTaskDone() throws DukeException {
        instruction = "done 1";
        duke.setTaskDone(instruction);
        assertTrue(list.getTasks().get(0).getDone());
    }

    @Test
    void addTask() throws DukeException {
        instruction = "todo read book";
        duke.addTask(instruction);
        int i = list.getTasks().size()-1;
        assertEquals(list.getTasks().get(i).getDescription(), "read book");
    }

    @Test
    void addDeadline() throws DukeException {
        instruction = "deadline return book /by 2/12/2019 1800";
        duke.addDeadline(instruction);
        int i = list.getTasks().size()-1;
        assertEquals(list.getTasks().get(i).getDescription(), "return book");
    }

    @Test
    void addEvent() throws DukeException {
        instruction = "event concert /at 1/09/2019 1600";
        duke.addEvent(instruction);
        int i = list.getTasks().size()-1;
        assertEquals(list.getTasks().get(i).getDescription(), "concert");
    }
}