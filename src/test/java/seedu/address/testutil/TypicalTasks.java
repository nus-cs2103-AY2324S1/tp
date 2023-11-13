package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_DG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_UG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEMBER_DG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEMBER_UG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_DG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_UG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.TaskWise;
import seedu.address.model.task.Priority;
import seedu.address.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {
    public static final Task FIX_BUG = new TaskBuilder()
            .withDescription("Fix bug").withStatus(false).withNote("Note").withPriority(Priority.NONE).build();
    public static final Task OP2_REPORT = new TaskBuilder()
            .withDescription("Do OP2 report").withStatus(true).withNote("").withPriority(Priority.NONE).build();
    public static final Task OP2_PRESENTATION = new TaskBuilder()
            .withDescription("Do OP2 presentation").withStatus(false).withNote("").withPriority(Priority.LOW).build();
    public static final Task UG = new TaskBuilder()
            .withDescription("Do user guide").withStatus(false).withNote("").withPriority(Priority.MEDIUM).build();
    public static final Task DG = new TaskBuilder()
            .withDescription("Do developer guide").withStatus(false).withNote("Note").withPriority(Priority.HIGH)
            .build();
    public static final Task OP2_MEETING = new TaskBuilder()
            .withDescription("Meeting to discuss OP2").withStatus(false).withNote("")
            .withPriority(Priority.NONE).build();
    public static final Task OP2_REHEARSAL = new TaskBuilder()
            .withDescription("Rehearse OP2 presentation").withStatus(false).withNote("")
            .withPriority(Priority.NONE).build();


    // Manually added
    public static final Task TASK_ONE = new TaskBuilder().withDescription("task one").build();
    public static final Task TASK_TWO = new TaskBuilder().withDescription("task two").build();

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final Task TASK_DG = new TaskBuilder()
            .withDescription(VALID_DESC_DG).withStatus(false).withNote(VALID_NOTE_DG)
            .withPriority(Priority.HIGH).withMembers(VALID_MEMBER_DG).build();
    public static final Task TASK_UG = new TaskBuilder()
            .withDescription(VALID_DESC_UG).withStatus(false).withNote(VALID_NOTE_UG)
            .withPriority(Priority.MEDIUM).withMembers(VALID_MEMBER_DG, VALID_MEMBER_UG).build();

    public static final Task TEST = new TaskBuilder()
            .withDescription("test").build();
    public static final String KEYWORD_MATCHING_MEIER = "OP2"; // A keyword that matches MEIER

    private TypicalTasks() {
    } // prevents instantiation

    /**
     * Returns an {@code TaskWise} with all the typical tasks.
     */
    public static TaskWise getTypicalTaskWise() {
        TaskWise tw = new TaskWise();
        for (Task task : getTypicalTasks()) {
            tw.addTask(task);
        }
        return tw;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(FIX_BUG, OP2_REPORT, OP2_PRESENTATION, UG, DG,
                OP2_MEETING, OP2_REHEARSAL));
    }
}
