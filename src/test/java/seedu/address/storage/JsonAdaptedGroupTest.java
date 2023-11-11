package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGroups.CS2100;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupRemark;

public class JsonAdaptedGroupTest {
    private static final String INVALID_NAME = "C$2100";

    private static final String VALID_NAME = CS2100.getGroupName();

    private static final List<JsonAdaptedTime> VALID_TIMEINTERVAL = CS2100.getTime().toStream()
            .map(JsonAdaptedTime::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validGroupDetails_returnsGroup() throws Exception {
        JsonAdaptedGroup cs2100 = new JsonAdaptedGroup(CS2100);
        assertEquals(CS2100, cs2100.toModelType());
    }

    @Test
    public void toModelType_invalidGroupName_throwsIllegalValueException() {
        JsonAdaptedGroup group =
                new JsonAdaptedGroup(INVALID_NAME, "Exam on friday", null);
        String expectedMessage = Group.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, group::toModelType);
    }

    @Test
    public void toModelType_nullGroupName_throwsIllegalValueException() {
        JsonAdaptedGroup group =
                new JsonAdaptedGroup(null, "Exam on friday", null);
        String expectedMessage = String.format(JsonAdaptedGroup.MISSING_FIELD_MESSAGE_FORMAT,
                Group.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, group::toModelType);
    }

    @Test
    public void toModelType_invalidGroupRemark_throwsIllegalValueException() {
        JsonAdaptedGroup group =
                new JsonAdaptedGroup(VALID_NAME, null, null);
        String expectedMessage = String.format(JsonAdaptedGroup.MISSING_FIELD_MESSAGE_FORMAT,
                GroupRemark.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, group::toModelType);
    }

}

