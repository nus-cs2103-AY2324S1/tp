package seedu.address.model.course;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a list of courses that the user is managing
 */
public class UniqueCourseList {
    private static final Set<Course> courses = new HashSet<>();

    /**
     * Returns the set of courses.
     */
    public static Set<Course> getCourses() {
        return courses;
    }

    /**
     * Adds a course to the list of courses.
     */
    public static void add(Course course) {
        courses.add(course);
    }

    /**
     * Removes a course from the list of courses.
     */
    public static void remove(Course course) {
        courses.remove(course);
    }

    /**
     * Returns a course with the given course code.
     */
    public static Course findByCourseCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }
}
