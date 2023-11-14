package seedu.address.model.partnercourse;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalObjects.COMP1000;
import static seedu.address.testutil.TypicalObjects.COMP2000;
import static seedu.address.testutil.TypicalObjects.TYPICAL_PARTNER_COURSE_CODE;
import static seedu.address.testutil.TypicalObjects.TYPICAL_PARTNER_COURSE_NAME;
import static seedu.address.testutil.TypicalObjects.TYPICAL_PARTNER_UNIVERSITY_NAME;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PartnerCourseBuilder;

public class PartnerCourseTest {
    @Test
    public void isSamePartnerCourse() {
        // same object -> returns true
        assertTrue(COMP1000.isSamePartnerCourse(COMP1000));

        // null -> returns false
        assertFalse(COMP1000.isSamePartnerCourse(null));

        // same partnercode and universityname, all other attributes different -> returns true
        PartnerCourse editedComp1000 = new PartnerCourseBuilder(COMP1000)
                .withPartnerName(TYPICAL_PARTNER_COURSE_NAME).build();
        assertTrue(COMP1000.isSamePartnerCourse(editedComp1000));

        // different partnercode, all other attributes same -> returns false
        editedComp1000 = new PartnerCourseBuilder(COMP1000).withPartnerCode(TYPICAL_PARTNER_COURSE_CODE).build();
        assertFalse(COMP1000.isSamePartnerCourse(editedComp1000));

        // different universityname, all other attributes same -> returns false
        editedComp1000 = new PartnerCourseBuilder(COMP1000)
                .withPartnerUniversity(TYPICAL_PARTNER_UNIVERSITY_NAME).build();
        assertFalse(COMP1000.isSamePartnerCourse(editedComp1000));

        // partnerCode differs in case, all other attributes same -> returns false
        /*
        PartnerCourse editedComp2000 =
                new PartnerCourseBuilder(COMP2000).withPartnerCode(
                        COMP2000.getPartnerCode().getValue().toLowerCase()).build();
        assertFalse(COMP2000.isSamePartnerCourse(editedComp2000));
        */
    }

    @Test
    public void equals() {
        // same values -> returns true
        PartnerCourse comp1000Copy = new PartnerCourseBuilder(COMP1000).build();
        assertTrue(COMP1000.equals(comp1000Copy));

        // same object -> returns true
        assertTrue(COMP1000.equals(COMP1000));

        // null -> returns false
        assertFalse(COMP1000.equals(null));

        // different type -> returns false
        assertFalse(COMP1000.equals(5));

        // different person -> returns false
        assertFalse(COMP1000.equals(COMP2000));

        // different name -> returns false
        PartnerCourse editedComp1000 = new PartnerCourseBuilder(COMP1000)
                .withPartnerName(TYPICAL_PARTNER_COURSE_NAME).build();
        assertFalse(COMP1000.equals(editedComp1000));
    }
}
