package seedu.address.model.course;

import java.util.Arrays;
import java.util.List;

import seedu.address.model.util.SampleDataUtil;
/**
 * Contains a list of SOC courses.
 */
public class CourseData {
    private static final List<Course> courseList = Arrays.asList(SampleDataUtil.getSampleCourses());

    public static List<Course> getCourseList() {
        return courseList;
    }

    public static String getCourseListString() {
        return "Available courses: " + courseList.stream()
                .map(Course::getCourseCode).reduce((courseCode1, courseCode2) -> courseCode1 + ", "
                        + courseCode2).orElse("");
    }
}
