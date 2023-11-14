package seedu.address.model.mapping;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalObjects.TYPICAL_MAPPING_MISC_INFORMATION;

import org.junit.jupiter.api.Test;

public class MappingMiscInformationTest {

    private static final String SECOND_VALID_MAPPING_MISC_INFORMATION = "Late night";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MappingMiscInformation(null));
    }

    @Test
    public void isValidMappingMiscInformation() {
        // null mappingMiscInformation
        assertThrows(NullPointerException.class, () -> MappingMiscInformation.isValidMappingMiscInformation(null));

        // invalid mappingMiscInformation
        assertFalse(MappingMiscInformation.isValidMappingMiscInformation("")); // empty string
        assertFalse(MappingMiscInformation.isValidMappingMiscInformation(" ")); // spaces only

        // valid mappingMiscInformation
        assertTrue(MappingMiscInformation
                .isValidMappingMiscInformation(TYPICAL_MAPPING_MISC_INFORMATION)); // starts with alphabet
    }

    @Test
    public void equals() {
        MappingMiscInformation mappingMiscInformation = new MappingMiscInformation(TYPICAL_MAPPING_MISC_INFORMATION);

        // same values -> returns true
        assertTrue(mappingMiscInformation.equals(new MappingMiscInformation(TYPICAL_MAPPING_MISC_INFORMATION)));

        // same object -> returns true
        assertTrue(mappingMiscInformation.equals(mappingMiscInformation));

        // null -> returns false
        assertFalse(mappingMiscInformation.equals(null));

        // different types -> returns false
        assertFalse(mappingMiscInformation.equals(5.0f));

        // different values -> returns false
        assertFalse(mappingMiscInformation.equals(new MappingMiscInformation(SECOND_VALID_MAPPING_MISC_INFORMATION)));
    }
}
