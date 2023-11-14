package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.fields.Outcome;

public class JsonAdaptedInternshipTaskTest {
    private final String validName = "OA";
    private final String invalidName = "";
    private final String validDate1 = "2025-10-15 23:59";
    private final String validDate2 = "2022-10-15 23:59";
    private final String invalidDate = "2023-02-31";
    private final Outcome validOutcome = Outcome.ACCEPTED;
    private final boolean validStatus = true;
    private final JsonAdaptedInternshipRole internshipRole =
            new JsonAdaptedInternshipRole("Google", "Backend Engineer", "Summer 2024",
                    "Description", new BigDecimal(1000.00), validOutcome, "Singapore",
                    List.of(new JsonAdaptedTag("tag")));
    private final String validTag = "tag";
    private final String invalidTag = "tag tag";


    @Test
    public void toModel_validInternship_doesNotThrow() {
        assertDoesNotThrow(() -> new JsonAdaptedInternshipTask(validName, validDate1, validOutcome, validStatus,
                internshipRole, List.of(new JsonAdaptedTag(validTag))).toModelType());
    }

    @Test
    public void toModel_validTaskDateBeforeNow_doesNotThrow() {
        assertDoesNotThrow(() -> new JsonAdaptedInternshipTask(validName, validDate2, validOutcome, validStatus,
                internshipRole, List.of(new JsonAdaptedTag(validTag))).toModelType());
    }

    @Test
    public void toModel_invalidName_throwsIllegalValueException() {
        assertThrows(IllegalValueException.class, () ->
                new JsonAdaptedInternshipTask(invalidName, validDate1, validOutcome, validStatus,
                internshipRole, List.of(new JsonAdaptedTag(validTag))).toModelType());
    }

    @Test
    public void toModel_invalidEndDate_throwsIllegalValueException() {
        assertThrows(IllegalValueException.class, () ->
                new JsonAdaptedInternshipTask(invalidName, invalidDate, validOutcome, validStatus,
                internshipRole, List.of(new JsonAdaptedTag(validTag))).toModelType());
    }

    @Test
    public void toModel_invalidTag_throwsIllegalValueException() {
        assertThrows(IllegalValueException.class, () ->
                new JsonAdaptedInternshipTask(invalidName, invalidDate, validOutcome, validStatus,
                internshipRole, List.of(new JsonAdaptedTag(invalidTag))).toModelType());
    }
}
