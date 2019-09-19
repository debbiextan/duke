import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import models.DukeException;
import models.TaskList;

class DukeTest {
    private String instruction = "";
    private Duke duke = new Duke();
    private TaskList list;

    @Test
    public void deleteTask() throws DukeException {
        instruction = "delete 1";
        String original = list.getTasks().get(0).getDescription();
        duke.deleteTask(instruction);
        String after = list.getTasks().get(0).getDescription();
        // assertEquals(a,b) == a.equals(b)
        assertEquals(original, after);
    }

    @Test
    public void setTaskDone() throws DukeException {
        instruction = "done 1";
        duke.setTaskDone(instruction);
        assertTrue(list.getTasks().get(0).getDone());
    }

    @Test
    public void addTask() throws DukeException {
        instruction = "todo read book";
        duke.addTask(instruction);
        int i = list.getTasks().size()-1;
        assertEquals(list.getTasks().get(i).getDescription(), "read book");
    }

    @Test
    public void addDeadline() throws DukeException {
        instruction = "deadline return book /by 2/12/2019 1800";
        duke.addDeadline(instruction);
        int i = list.getTasks().size()-1;
        assertEquals(list.getTasks().get(i).getDescription(), "return book");
    }

    @Test
    public void addEvent() throws DukeException {
        instruction = "event concert /at 1/09/2019 1600";
        duke.addEvent(instruction);
        int i = list.getTasks().size()-1;
        assertEquals(list.getTasks().get(i).getDescription(), "concert");
    }
}