package seedu.codesphere.testutil;

import static seedu.codesphere.testutil.TypicalStudents.getTypicalStudentList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.codesphere.model.CourseList;
import seedu.codesphere.model.course.Course;

/**
 * A utility class containing a list of {@code Course} objects to be used in tests.
 */
public class TypicalCourses {

    public static final Course CS1101S = new CourseBuilder().withCourseName("CS1101S")
            .withStudents(getTypicalStudentList()).build();
    public static final Course CS1231S = new CourseBuilder().withCourseName("CS1231S")
            .withStudents(getTypicalStudentList()).build();
    public static final Course CS2030S = new CourseBuilder().withCourseName("CS2030S").build();
    public static final Course CS2040S = new CourseBuilder().withCourseName("CS2040S").build();
    public static final Course CS2100 = new CourseBuilder().withCourseName("CS2100").build();
    public static final Course CS2103T = new CourseBuilder().withCourseName("CS2103T").build();
    public static final Course CS3230 = new CourseBuilder().withCourseName("CS3230").build();

    public static final Course ST2334 = new CourseBuilder().withCourseName("ST2334").build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalCourses() {} // prevents instantiation

    /**
     * Returns a {@code CourseList} with all typical Courses.
     */
    public static CourseList getTypicalCourseList() {
        CourseList cl = new CourseList();
        for (Course course : getTypicalCourses()) {
            cl.addCourse(course);
        }
        return cl;
    }

    public static List<Course> getTypicalCourses() {
        return new ArrayList<>(Arrays.asList(CS1101S, CS1231S, CS2030S, CS2040S, CS3230, ST2334));
    }

    public static Course getTypicalCourse() {
        return CS2103T;
    }
}
