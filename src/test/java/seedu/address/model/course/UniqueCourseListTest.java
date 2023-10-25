package seedu.address.model.course;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

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
}
