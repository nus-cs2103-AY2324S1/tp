package seedu.address.testutil;

import seedu.address.model.group.tasks.Task;
import seedu.address.model.group.tasks.TaskModule;
import seedu.address.model.group.tasks.TaskStatus;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final Task CS2101_OP1_UPLOAD = new Task("Upload video of OP1.", TaskStatus.DONE,
        TaskModule.CS2101, "TODO", "");
    public static final Task CS2101_PEER_REVIEW_OP2 = new Task("Complete peer review for OP2.", TaskStatus.NOT_DONE,
        TaskModule.CS2101, "TODO", "");
    public static final Task CS2101_SUBMIT_SLIDES_OP2 = new Task("Submit slides for OP2.", TaskStatus.NOT_DONE,
        TaskModule.CS2101, "DEADLINE", "29/10/2023 2359");
    public static final Task CS2101_COMPLETE_PEER_REVIEW = new Task("Complete peer review.", TaskStatus.NOT_DONE,
        TaskModule.CS2101, "DEADLINE", "02/11/2023 2359");
    public static final Task CS2101_RESEARCH_SCQA = new Task("Research on  SCQA framework.", TaskStatus.DONE,
        TaskModule.CS2101, "TODO", "");
    public static final Task CS2101_PLAN_OP2 = new Task("Plan for OP2.", TaskStatus.NOT_DONE,
        TaskModule.CS2101, "DEADLINE", "24/10/2023 2359");
    public static final Task CS2101_SUBMIT_UG = new Task("Submit UG.", TaskStatus.NOT_DONE,
        TaskModule.CS2101, "DEADLINE", "11/11/2023 2359");

    public static final Task CS2103T_MID_SEM_REVIEW = new Task("Submit mid semester review.", TaskStatus.NOT_DONE,
        TaskModule.CS2103T, "TODO", "");
    public static final Task CS2103T_ADD_DEMO_SCREENSHOTS = new Task("Update screenshots", TaskStatus.NOT_DONE,
        TaskModule.CS2103T, "DEADLINE", "20/11/2023 2359");
    public static final Task CS2103T_RELEASE_JAR = new Task("Release v1.3.trial jar file.", TaskStatus.DONE,
        TaskModule.CS2103T, "DEADLINE", "27/10/2023 2359");
    public static final Task CS2103T_WRAP_UP_MILESTONE = new Task("Wrap up milestone 1.3.", TaskStatus.NOT_DONE,
        TaskModule.CS2103T, "DEADLINE", "03/11/2023 2359");
    public static final Task CS2103T_FINALISE_TP = new Task("Finalise TP.", TaskStatus.DONE,
        TaskModule.CS2103T, "DEADLINE", "17/11/2023 2359");
    public static final Task CS2103T_UPDATE_DG = new Task("Update DG for each feature.", TaskStatus.NOT_DONE,
        TaskModule.CS2103T, "TODO", "");

    // Private constructor to prevent instantiation
    private TypicalTasks() {}
}
