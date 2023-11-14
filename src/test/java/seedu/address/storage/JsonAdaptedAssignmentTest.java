package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;

public class JsonAdaptedAssignmentTest {
    private final String invalidName = "";
    private final String invalidDate = "2023-02-31";

    private final String validName = "task1";
    private final String validDescription = "description";
    private final boolean validStatus = true;
    private final String validDate1 = "2025-10-15 23:59";
    private final String validDate2 = "2022-10-15 23:59";
    private final String validTag = "CS2100";

    @Test
    public void toModel_validAssignment_doesNotThrow() {
        assertDoesNotThrow(() -> new JsonAdaptedAssignment(validName, validDescription, validStatus, validDate1,
                validDate1, List.of(new JsonAdaptedTag(validTag))).toModelType());
    }

    @Test
    public void toModel_validAssignmentDateBeforeNow_doesNotThrow() {
        assertDoesNotThrow(() -> new JsonAdaptedAssignment(validName, validDescription, validStatus, validDate2,
                validDate1, List.of(new JsonAdaptedTag(validTag))).toModelType());
    }

    @Test
    public void toModel_invalidName_throwsIllegalValueException() {
        assertThrows(IllegalValueException.class, () -> new JsonAdaptedAssignment(invalidName, validDescription,
                validStatus, validDate1, validDate1, List.of(new JsonAdaptedTag(validTag))).toModelType());
    }

    @Test
    public void toModel_invalidEndDate_throwsIllegalValueException() {
        assertThrows(IllegalValueException.class, () -> new JsonAdaptedAssignment(validName, validDescription,
                validStatus, invalidDate, validDate1, List.of(new JsonAdaptedTag(validTag))).toModelType());
    }

    @Test
    public void toModel_invalidPlannedDate_throwsIllegalValueException() {
        assertThrows(IllegalValueException.class, () -> new JsonAdaptedAssignment(validName, validDescription,
                validStatus, validDate1, invalidDate, List.of(new JsonAdaptedTag(validTag))).toModelType());
    }
}
