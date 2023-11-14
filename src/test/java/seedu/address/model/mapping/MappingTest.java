package seedu.address.model.mapping;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalObjects.CS2030S_TO_ZURICH_COMP3000;
import static seedu.address.testutil.TypicalObjects.CS2040S_TO_NTU_S0402SC;
import static seedu.address.testutil.TypicalObjects.TYPICAL_LOCAL_COURSE_CODE;
import static seedu.address.testutil.TypicalObjects.TYPICAL_MAPPING_MISC_INFORMATION;
import static seedu.address.testutil.TypicalObjects.TYPICAL_PARTNER_COURSE_CODE;
import static seedu.address.testutil.TypicalObjects.TYPICAL_UNIVERSITY_NAME;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.MappingBuilder;

public class MappingTest {
    @Test
    public void isSameMapping() {
        // same object -> returns true
        assertTrue(CS2040S_TO_NTU_S0402SC.isSameMapping(CS2040S_TO_NTU_S0402SC));

        // null -> returns false
        assertFalse(CS2040S_TO_NTU_S0402SC.isSameMapping(null));

        // same localCode, partnerCode and universityName, all other attributes different -> returns true
        Mapping editedMapping = new MappingBuilder(CS2040S_TO_NTU_S0402SC)
                .withMappingMiscInformation(TYPICAL_MAPPING_MISC_INFORMATION).build();
        assertTrue(CS2040S_TO_NTU_S0402SC.isSameMapping(editedMapping));

        // different partnerCode, all other attributes same -> returns false
        editedMapping = new MappingBuilder(CS2040S_TO_NTU_S0402SC)
                .withPartnerCode(TYPICAL_PARTNER_COURSE_CODE).build();
        assertFalse(CS2040S_TO_NTU_S0402SC.isSameMapping(editedMapping));

        // different localCode, all other attributes same -> returns false
        editedMapping = new MappingBuilder(CS2040S_TO_NTU_S0402SC)
                .withLocalCode(TYPICAL_LOCAL_COURSE_CODE).build();
        assertFalse(CS2040S_TO_NTU_S0402SC.isSameMapping(editedMapping));

        // different universityName, all other attributes same -> returns false
        editedMapping = new MappingBuilder(CS2040S_TO_NTU_S0402SC)
                .withUniversityName(TYPICAL_UNIVERSITY_NAME).build();
        assertFalse(CS2040S_TO_NTU_S0402SC.isSameMapping(editedMapping));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Mapping mappingCopy = new MappingBuilder(CS2040S_TO_NTU_S0402SC).build();
        assertTrue(CS2040S_TO_NTU_S0402SC.equals(mappingCopy));

        // same object -> returns true
        assertTrue(CS2040S_TO_NTU_S0402SC.equals(CS2040S_TO_NTU_S0402SC));

        // null -> returns false
        assertFalse(CS2040S_TO_NTU_S0402SC.equals(null));

        // different type -> returns false
        assertFalse(CS2040S_TO_NTU_S0402SC.equals(5));

        // different person -> returns false
        assertFalse(CS2040S_TO_NTU_S0402SC.equals(CS2030S_TO_ZURICH_COMP3000));

        // different mappingMiscInformation -> returns false
        Mapping editedMapping = new MappingBuilder(CS2040S_TO_NTU_S0402SC)
                .withMappingMiscInformation(TYPICAL_MAPPING_MISC_INFORMATION).build();
        assertFalse(CS2040S_TO_NTU_S0402SC.equals(editedMapping));
    }
}
