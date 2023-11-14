package seedu.address.model.localcourse.comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalObjects.CS2030S;
import static seedu.address.testutil.TypicalObjects.MA2001;

import org.junit.jupiter.api.Test;

public class LocalCourseComparatorByLocalNameTest {
    private LocalCourseComparatorByLocalName localCourseComparatorByLocalName = new LocalCourseComparatorByLocalName();

    @Test
    public void compare() {
        //MA2001 LocalName = Linear Algebra
        //CS2030S LocalName = Programming Methodology II
        assertEquals(-1, localCourseComparatorByLocalName.compare(MA2001, CS2030S));
        assertEquals(0, localCourseComparatorByLocalName.compare(CS2030S, CS2030S));
        assertEquals(1, localCourseComparatorByLocalName.compare(CS2030S, MA2001));
    }

    @Test
    public void equals() {
        // same values -> returns true
        assertTrue(localCourseComparatorByLocalName.equals(new LocalCourseComparatorByLocalName()));

        // same object -> returns true
        assertTrue(localCourseComparatorByLocalName.equals(localCourseComparatorByLocalName));

        // null -> returns false
        assertFalse(localCourseComparatorByLocalName.equals(null));

        // different types -> returns false
        assertFalse(localCourseComparatorByLocalName.equals(1));

        // different values -> returns false
        assertFalse(localCourseComparatorByLocalName.equals(new LocalCourseComparatorByLocalCode()));
    }
}
