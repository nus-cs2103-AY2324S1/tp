package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedGroup.INVALID_NUMBER_MESSAGE;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGroups.GROUP1;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tutorial.Tutorial;

public class JsonAdaptedGroupTest {

    private static final int INVALID_NUMBER = -1;
    private static final JsonAdaptedTutorial INVALID_TUTORIAL = new JsonAdaptedTutorial("1");

    private static final int VALID_NUMBER = GROUP1.getNumber();
    private static final JsonAdaptedTutorial VALID_TUTORIAL = new JsonAdaptedTutorial(GROUP1.getTutorial());
    private static final List<JsonAdaptedPerson> VALID_MEMBERS = GROUP1.getMembers().stream()
            .map(JsonAdaptedPerson::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedTask> VALID_TASKS = GROUP1.getTasks().getTasks().stream()
            .map(JsonAdaptedTask::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validGroupDetails_returnsGroup() throws Exception {
        JsonAdaptedGroup group = new JsonAdaptedGroup(GROUP1);
        assertEquals(GROUP1, group.toModelType());
    }

    @Test
    public void toModelType_invalidNumber_throwsIllegalValueException() {
        JsonAdaptedGroup group = new JsonAdaptedGroup(INVALID_NUMBER,
                VALID_TUTORIAL, VALID_MEMBERS, VALID_TASKS);
        String expectedMessage = INVALID_NUMBER_MESSAGE;
        assertThrows(IllegalValueException.class, expectedMessage, group::toModelType);
    }

    @Test
    public void toModelType_nullTutorial_throwsIllegalValueException() {
        JsonAdaptedGroup group = new JsonAdaptedGroup(VALID_NUMBER, null, VALID_MEMBERS, VALID_TASKS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Tutorial.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, group::toModelType);
    }

    @Test
    public void toModelType_invalidTutorial_throwsIllegalValueException() {
        JsonAdaptedGroup group = new JsonAdaptedGroup(VALID_NUMBER, INVALID_TUTORIAL, VALID_MEMBERS, VALID_TASKS);
        String expectedMessage = Tutorial.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, group::toModelType);
    }

}
