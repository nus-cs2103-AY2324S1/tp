package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.OP2_REPORT;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.Deadline;
import seedu.address.model.task.Description;
import seedu.address.model.task.Note;
import seedu.address.model.task.Priority;
import seedu.address.storage.exceptions.json.IllegalJsonValueException;

public class JsonAdaptedTaskTest {
    private static final String INVALID_DESCRIPTION = "Do OP2 // Slides";
    private static final String INVALID_NOTE = "clarify with /tutor";
    private static final String INVALID_MEMBER = "/friend";
    private static final String VALID_DESCRIPTION = OP2_REPORT.getDescription().toString();
    private static final String VALID_NOTE = OP2_REPORT.getNote().toString();
    private static final Deadline TEST_DEADLINE = Deadline.now();
    private static final Priority TEST_PRIORITY = Priority.NONE;
    private static final List<JsonAdaptedMember> VALID_MEMBERS = OP2_REPORT.getMembers().stream()
            .map(JsonAdaptedMember::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validTaskDetails_returnsTask() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(OP2_REPORT);
        assertEquals(OP2_REPORT, task.toModelType());
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(INVALID_DESCRIPTION, false, VALID_NOTE, TEST_DEADLINE,
                        TEST_PRIORITY, VALID_MEMBERS);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalJsonValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(null, false, VALID_NOTE, TEST_DEADLINE,
                TEST_PRIORITY, VALID_MEMBERS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalJsonValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidMembers_throwsIllegalValueException() {
        List<JsonAdaptedMember> invalidMembers = new ArrayList<>(VALID_MEMBERS);
        invalidMembers.add(new JsonAdaptedMember(INVALID_MEMBER));
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_DESCRIPTION, false, VALID_NOTE,
                TEST_DEADLINE, TEST_PRIORITY, invalidMembers);
        assertThrows(IllegalJsonValueException.class, task::toModelType);
    }

    @Test
    public void toModelType_invalidNote_throwsIllegalValueException() {
        JsonAdaptedTask task =
               new JsonAdaptedTask(VALID_DESCRIPTION, false, INVALID_NOTE, TEST_DEADLINE,
                       TEST_PRIORITY, VALID_MEMBERS);
        String expectedMessage = Note.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalJsonValueException.class, expectedMessage, task::toModelType);
    }
}

