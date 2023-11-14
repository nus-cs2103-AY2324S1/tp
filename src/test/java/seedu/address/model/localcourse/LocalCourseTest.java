package seedu.address.model.localcourse;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalObjects.CS2040S;
import static seedu.address.testutil.TypicalObjects.CS3230;
import static seedu.address.testutil.TypicalObjects.TYPICAL_LOCAL_COURSE_CODE;
import static seedu.address.testutil.TypicalObjects.TYPICAL_LOCAL_COURSE_NAME;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.LocalCourseBuilder;

public class LocalCourseTest {
    @Test
    public void isSameLocalCourse() {
        // same object -> returns true
        assertTrue(CS2040S.isSameLocalCourse(CS2040S));

        // null -> returns false
        assertFalse(CS2040S.isSameLocalCourse(null));

        // same localcode, all other attributes different -> returns true
        LocalCourse editedCS2040S = new LocalCourseBuilder(CS2040S).withLocalName(TYPICAL_LOCAL_COURSE_NAME).build();
        assertTrue(CS2040S.isSameLocalCourse(editedCS2040S));

        // different localcode, all other attributes same -> returns false
        editedCS2040S = new LocalCourseBuilder(CS2040S).withLocalCode(TYPICAL_LOCAL_COURSE_CODE).build();
        assertFalse(CS2040S.isSameLocalCourse(editedCS2040S));

        // localCode differs in case, all other attributes same -> returns false
        /*
        LocalCourse editedCS3230 =
         new LocalCourseBuilder(CS3230).withLocalCode(CS3230.getLocalCode().getValue().toLowerCase()).build();
         assertFalse(CS3230.isSameLocalCourse(editedCS3230));
        */
    }

    @Test
    public void equals() {
        // same values -> returns true
        LocalCourse cs2040sCopy = new LocalCourseBuilder(CS2040S).build();
        assertTrue(CS2040S.equals(cs2040sCopy));

        // same object -> returns true
        assertTrue(CS2040S.equals(CS2040S));

        // null -> returns false
        assertFalse(CS2040S.equals(null));

        // different type -> returns false
        assertFalse(CS2040S.equals(5));

        // different person -> returns false
        assertFalse(CS2040S.equals(CS3230));

        // different name -> returns false
        LocalCourse editedCS2040S = new LocalCourseBuilder(CS2040S).withLocalName(TYPICAL_LOCAL_COURSE_NAME).build();
        assertFalse(CS2040S.equals(editedCS2040S));
    }
}
