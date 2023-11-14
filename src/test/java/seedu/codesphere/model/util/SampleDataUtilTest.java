package seedu.codesphere.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import seedu.codesphere.model.ReadOnlyCourseList;
import seedu.codesphere.model.ReadOnlyStudentList;
import seedu.codesphere.model.course.Course;
import seedu.codesphere.model.student.Student;

public class SampleDataUtilTest {

    @Test
    public void getSampleStudents_validInput_returnsArray() {
        Student[] sampleStudents = SampleDataUtil.getSampleStudents();
        assertNotNull(sampleStudents);
        assertEquals(6, sampleStudents.length);
    }

    @Test
    public void getSampleCourses_validInput_returnsArray() {
        Course[] sampleCourses = SampleDataUtil.getSampleCourses();
        assertNotNull(sampleCourses);
        assertEquals(6, sampleCourses.length);
    }

    @Test
    public void getSampleStudentList_validInput_returnsReadOnlyStudentList() {
        ReadOnlyStudentList sampleStudentList = SampleDataUtil.getSampleStudentList();
        assertNotNull(sampleStudentList);
    }

    @Test
    public void getSampleCourseList_validInput_returnsReadOnlyCourseList() {
        ReadOnlyCourseList sampleCourseList = SampleDataUtil.getSampleCourseList();
        assertNotNull(sampleCourseList);
    }
}
