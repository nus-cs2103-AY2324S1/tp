package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.animal.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final Task FEED = new Task("Feed Animal");
    public static final Task FED = new Task("Feed Animal").updateTask(true);

    public static final Task WALK = new Task("Walk Animal");
    public static final Task WALKED = new Task("Walk Animal").updateTask(true);

    public static final Task MEDICATE = new Task("Medicate Animal");

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(FEED, WALK, MEDICATE));
    }
}
