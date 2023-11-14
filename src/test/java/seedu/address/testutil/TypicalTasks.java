package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.FullTaskList;
import seedu.address.model.lessons.Task;
import seedu.address.model.lessons.TaskList;


/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final Task TASK_1 = new Task("Task 1");
    public static final Task TASK_2 = new Task("Task 2");
    public static final Task TASK_3 = new Task("Task 3");
    public static final Task TASK_4 = new Task("Task 4");
    public static final Task TASK_5 = new Task("Task 5");
    public static final Task TASK_6 = new Task("Task 6");
    public static final Task LONG_TASK_NAME = new Task("This is a long "
            + "task name meant for testing the application hahaha.");

    private TypicalTasks() {} // prevents instantiation


    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(TASK_1, TASK_2, TASK_3, LONG_TASK_NAME));
    }

    /**
     * Returns a {@code TaskList} with all the typical tasks.
     */
    public static TaskList getTypicalTaskList() {
        TaskList tl = new TaskList();
        for (Task task : getTypicalTasks()) {
            tl.add(task);
        }
        return tl;
    }

    /**
     * Returns a {@code FullTaskList} with all the typical lessons.
     */
    public static FullTaskList getTypicalFullTaskList() {
        FullTaskList ftl = new FullTaskList();
        ftl.setFullTaskList(TypicalLessons.getTypicalScheduleList());
        return ftl;
    }
}
