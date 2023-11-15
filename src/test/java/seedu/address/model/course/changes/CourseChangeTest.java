package seedu.address.model.course.changes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CourseChangeTest {
    @Test
    public void isValidCreateCourseChange() {

        // null description
        assertThrows(NullPointerException.class, () -> CourseChange.createCourseChange(null));

        // invalid description
        assertNull(CourseChange.createCourseChange("-CS2103T"));

        // add description
        assertEquals(new CourseAddition("add-CS2103T"),
                CourseChange.createCourseChange("add-CS2103T"));

        // delete description
        assertEquals(new CourseDeletion("del-CS2103T"),
            CourseChange.createCourseChange("del-CS2103T"));

        // edit description
        assertEquals(new CourseEdit("MA2001-CS2103T"),
            CourseChange.createCourseChange("MA2001-CS2103T"));
    }
}

