package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.fields.Outcome;

public class JsonAdaptedInternshipRoleTest {
    private final String validName = "Google";
    private final String invalidName = "";
    private final String validRole = "Backend Engineer";
    private final String invalidRole = "";
    private final String validCycle = "Summer 2024";
    private final BigDecimal validPay = new BigDecimal(1000.00);
    private final Outcome validOutcome = Outcome.ACCEPTED;
    private final String validLocation = "singapore";
    private final String validTag = "tag";
    private final String invalidTag = "tag tag";
    private final String validDescription = "Important internship!!";


    @Test
    public void toModel_validInternship_doesNotThrow() {
        assertDoesNotThrow(() -> new JsonAdaptedInternshipRole(validName, validRole, validCycle, validDescription,
                validPay, validOutcome, validLocation, List.of(new JsonAdaptedTag(validTag))).toModelType());
    }

    @Test
    public void toModel_invalidName_throwsIllegalValueException() {
        assertThrows(IllegalValueException.class, () -> new JsonAdaptedInternshipRole(invalidName, validRole,
                validCycle, validDescription, validPay, validOutcome, validLocation,
                List.of(new JsonAdaptedTag(validTag))).toModelType());
    }

    @Test
    public void toModel_invalidRole_throwsIllegalValueException() {
        assertThrows(IllegalValueException.class, () -> new JsonAdaptedInternshipRole(validName, invalidRole,
                validCycle, validDescription, validPay, validOutcome, validLocation,
                List.of(new JsonAdaptedTag(validTag))).toModelType());
    }

    @Test
    public void toModel_invalidTag_throwsIllegalValueException() {
        assertThrows(IllegalValueException.class, () -> new JsonAdaptedInternshipRole(validName, validRole,
                validCycle, validDescription, validPay, validOutcome, validLocation,
                List.of(new JsonAdaptedTag(invalidTag))).toModelType());
    }

}
