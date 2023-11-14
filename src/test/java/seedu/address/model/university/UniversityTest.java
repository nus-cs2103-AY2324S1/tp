package seedu.address.model.university;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalObjects.NTU;
import static seedu.address.testutil.TypicalObjects.STANFORD;
import static seedu.address.testutil.TypicalObjects.TYPICAL_LOCAL_COURSE_NAME;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.UniversityBuilder;

public class UniversityTest {

    @Test
    public void isSameUniversity() {
        // same object -> returns true
        assertTrue(NTU.isSameUniversity(NTU));

        // null -> returns false
        assertFalse(NTU.isSameUniversity(null));

        // universityName differs in case, all other attributes same -> returns false
        /*
        University editedNtu =
                new UniversityBuilder(NTU).withUniversityName(NTU.getUniversityName().getValue().toLowerCase()).build();
        assertFalse(NTU.isSameUniversity(editedNtu));
        */
    }

    @Test
    public void equals() {
        // same values -> returns true
        University ntuCopy = new UniversityBuilder(NTU).build();
        assertTrue(NTU.equals(ntuCopy));

        // same object -> returns true
        assertTrue(NTU.equals(NTU));

        // null -> returns false
        assertFalse(NTU.equals(null));

        // different type -> returns false
        assertFalse(NTU.equals(5));

        // different university -> returns false
        assertFalse(NTU.equals(STANFORD));

        // different name -> returns false
        University editedStandford =
                new UniversityBuilder(STANFORD).withUniversityName(TYPICAL_LOCAL_COURSE_NAME).build();
        assertFalse(STANFORD.equals(editedStandford));
    }
}
