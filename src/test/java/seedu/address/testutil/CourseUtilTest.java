package seedu.address.testutil;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.util.CourseUtil;

public class CourseUtilTest {

    @Test
    public void containsCourseWithSpaces() {
        assertFalse(CourseUtil.contains("CS 1101S"));
    }

    @Test
    public void containsAdditionalValidCourses() {
        assertTrue(CourseUtil.contains("MA1521"));
        assertTrue(CourseUtil.contains("CS2103T"));
        assertTrue(CourseUtil.contains("IS2218"));
        assertTrue(CourseUtil.contains("ABM5002"));
        assertTrue(CourseUtil.contains("ABM5101"));
        assertTrue(CourseUtil.contains("ABM5102"));
        assertTrue(CourseUtil.contains("ABM5106"));
    }

    @Test
    public void containsInvalidCourses() {
        assertFalse(CourseUtil.contains("CS9999"));
        assertFalse(CourseUtil.contains("MA210"));
        assertFalse(CourseUtil.contains("ENG10101"));
    }

    @Test
    public void containsCourseWithSpecialCharacters() {
        assertFalse(CourseUtil.contains("CS-1101S"));
    }

    @Test
    public void containsEmptyCourse() {
        assertFalse(CourseUtil.contains(""));
    }

    @Test
    public void containsVeryLongCourse() {
        assertFalse(CourseUtil.contains("CS1101S" + "A".repeat(1000))); // A very long course code
    }


}

