package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class TutorialTest {

    @Test
    public void constructor_nullCourse_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tutorial(null, "CS2101/G06"));
    }

    @Test
    public void constructor_nullTutorialString_throwsNullPointerException() {
        Course course = new Course("CS2101");
        assertThrows(NullPointerException.class, () -> new Tutorial(course, null));
    }

    @Test
    public void constructor_invalidTutorialString_throwsIllegalArgumentException() {
        Course course = new Course("CS2101");
        String invalidTutorialString = "";

        assertThrows(IllegalArgumentException.class, () -> new Tutorial(course, invalidTutorialString));
    }

    @Test
    public void constructor_mismatchingTutorialCourseName_throwsAssertionError() {
        Course course = new Course("CS2101");
        String tutorialString = "CS2102/G06";

        assertThrows(AssertionError.class, () -> new Tutorial(course, tutorialString));
    }

    @Test
    public void isValidTutorialString() {
        // null tutorialstring
        assertThrows(NullPointerException.class, () -> Tutorial.isValidTutorialString(null));

        // invalid tutorialstring
        assertFalse(Tutorial.isValidTutorialString("")); // empty string
        assertFalse(Tutorial.isValidTutorialString(" ")); // just a space
        assertFalse(Tutorial.isValidTutorialString("/G06")); // no course specified
        assertFalse(Tutorial.isValidTutorialString("abcdef1234/abcdef")); // invalid course string
        assertFalse(Tutorial.isValidTutorialString("cs1234/")); // empty tutorial name

        // valid tutorialstring

        // double letter + quad digit course, three chars for tutorial
        assertTrue(Tutorial.isValidTutorialString("CS2106/G06"));


        // double letter + quad digit + letter course, three chars for tutorial
        assertTrue(Tutorial.isValidTutorialString("CS2103T/F08"));


        // three letter course, one char for tutorial name
        assertTrue(Tutorial.isValidTutorialString("GEA1000/a"));
    }

    @Test
    public void splitCourseTutorialName_invalidTutorialString_returnsEmptyList() {
        String[] result1 = Tutorial.splitCourseTutorialName("/");
        assertEquals(0, result1.length);

        String[] result2 = Tutorial.splitCourseTutorialName("CS2100/");
        assertEquals(0, result2.length);

        String[] result3 = Tutorial.splitCourseTutorialName("/G06");
        assertEquals(0, result3.length);
    }

    @Test
    public void splitCourseTutorialName_validTutorialString_returnsCorrectly() {
        String validCourseString = "CS2103T";
        String validTutorialString = "F08";
        String courseTutorialName = validCourseString
            + Tutorial.COURSE_TUTORIAL_DELIMITER + validTutorialString;

        String[] result = Tutorial.splitCourseTutorialName(courseTutorialName);
        assertEquals(2, result.length);

        String result0 = result[0];
        String result1 = result[1];

        assertEquals(validCourseString, result0);
        assertEquals(validTutorialString, result1);
    }

    @Test
    public void findMatchingCourse_invalidTutorialString_returnsEmptyOptional() {
        Set<Course> courseSet = new HashSet<>();

        Optional<Course> courseOptional1 = Tutorial.findMatchingCourse(courseSet, "/");
        assertThrows(NoSuchElementException.class, () -> courseOptional1.get());

        Optional<Course> courseOptional2 = Tutorial.findMatchingCourse(courseSet, "CS2101/");
        assertThrows(NoSuchElementException.class, () -> courseOptional2.get());

        Optional<Course> courseOptional3 = Tutorial.findMatchingCourse(courseSet, "/G06");
        assertThrows(NoSuchElementException.class, () -> courseOptional3.get());
    }

    @Test
    public void findMatchingCourse_noMatchingCourse_returnsEmptyOptional() {
        Set<Course> courseSet = new HashSet<>();

        Optional<Course> courseOptional = Tutorial.findMatchingCourse(courseSet, "CS2101/G06");

        assertThrows(NoSuchElementException.class, () -> courseOptional.get());
    }

    @Test
    public void findMatchingCourse_validMatchingCourseExists_returnsMatchingCourse() {
        String matchingCourseString = "CS2101";
        String tutorialString = "G06";

        Course matchingCourse = new Course(matchingCourseString);

        Set<Course> courseSet = new HashSet<>();
        courseSet.add(new Course("CS2103T"));
        courseSet.add(matchingCourse);
        courseSet.add(new Course("CS2100"));
        courseSet.add(new Course("CS2109S"));

        String searchString = matchingCourseString + Tutorial.COURSE_TUTORIAL_DELIMITER + tutorialString;

        Optional<Course> courseOptional = Tutorial.findMatchingCourse(courseSet, searchString);

        Course course = courseOptional.get();

        assertEquals(matchingCourse, course);
    }

    @Test
    public void equals() {
        Course course = new Course("CS2101");

        Tutorial tutorial = new Tutorial(course, "CS2101/G06");

        // same values -> returns true
        assertTrue(tutorial.equals(new Tutorial(course, "CS2101/G06")));

        // same object -> returns true
        assertTrue(tutorial.equals(tutorial));

        // null -> returns false
        assertFalse(tutorial.equals(null));

        // different types -> returns false
        assertFalse(tutorial.equals(5.0f));

        // different values -> returns false
        Course wrongCourse = new Course("CS2102");
        assertFalse(tutorial.equals(new Tutorial(wrongCourse, "CS2102/G06"))); // Wrong Course reference
        assertFalse(tutorial.equals(new Tutorial(course, "CS2101/G07"))); // Wrong tutorial String
    }
}
