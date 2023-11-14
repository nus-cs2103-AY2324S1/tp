package seedu.address.model.course;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.course.exceptions.CourseNotFoundException;
import seedu.address.model.course.exceptions.LessonNotFoundException;

/**
 * Contains tests for UniqueCourseList.
 */
public class UniqueCourseListTest {
    @Test
    public void internalListIsCorrect() {
        assertEquals(UniqueCourseList.getList(), CourseData.getCourseList());
    }

    @Test
    public void findByCourseCode() {
        assertEquals(UniqueCourseList.findByCourseCode("CS2103T"),
                CourseData.getCourseList()
                        .stream()
                        .filter(course -> course.getCourseCode().equals("CS2103T"))
                        .findFirst().get());
    }

    @Test
    public void findByInvalidCourseCode() {
        assertThrows(CourseNotFoundException.class, () -> UniqueCourseList.findByCourseCode("CS2103"));
    }

    @Test
    public void findLesson() {
        assertEquals(UniqueCourseList.findLesson("CS2103T", "Laboratory"),
                CourseData.getCourseList()
                        .stream()
                        .filter(course -> course.getCourseCode().equals("CS2103T"))
                        .findFirst().get()
                        .getLessons()
                        .stream()
                        .filter(lesson -> lesson.getName().equals("Laboratory"))
                        .findFirst().get());
    }

    @Test
    public void findInvalidLessonWithInvalidCourse() {
        assertThrows(CourseNotFoundException.class, (
        ) -> UniqueCourseList.findLesson("CS2103", "Tutorial"));
    }

    @Test
    public void findInvalidLessonWithInvalidLesson() {
        assertThrows(LessonNotFoundException.class, (
        ) -> UniqueCourseList.findLesson("CS2103T", "Midterms"));
    }
}
