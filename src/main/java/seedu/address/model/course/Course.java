package seedu.address.model.course;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a course that the user is managing
 */
public class Course {
    public static final String MESSAGE_CONSTRAINTS = "Course codes should start with 2-3 alphabets, "
            + "followed by 4 numbers, and optionally end with an alphabet.";

    // 2-3 alphabets, followed by 4 digits, and optionally ending with an alphabet
    public static final String VALIDATION_REGEX = "^[A-Za-z]{2,3}\\d{4}[A-Za-z]?$";
    public static final Course EMPTY_COURSE = new Course();

    private final String name;
    private final String courseCode;
    private final Set<Lesson> lessons = new HashSet<>();

    /**
     * Constructs a {@code Course}.
     *
     * @param name       A course name.
     * @param courseCode A course code.
     * @param lessons    A set of lessons.
     */
    public Course(String name, String courseCode, Set<Lesson> lessons) {
        requireAllNonNull(name, courseCode, lessons);
        checkArgument(isValidCourseCode(courseCode), MESSAGE_CONSTRAINTS); //Check if course code is valid
        this.name = name;
        this.courseCode = courseCode;
        this.lessons.addAll(lessons);
    }

    private Course() {
        this.name = "";
        this.courseCode = "";
    }

    public String getName() {
        return name;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public Set<Lesson> getLessons() {
        return lessons;
    }

    /**
     * Returns true if a given string is a valid course code.
     */
    public static boolean isValidCourseCode(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if both courses have the same course code.
     * This defines a weaker notion of equality between two courses.
     */
    public boolean isSameCourse(Course otherCourse) {
        if (otherCourse == this) {
            return true;
        }
        return otherCourse != null
                && otherCourse.courseCode.equals(this.courseCode);

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof Course)) {
            return false;
        }
        Course otherCourse = (Course) other;
        return otherCourse.name.equals(this.name)
                && otherCourse.courseCode.equals(this.courseCode)
                && otherCourse.lessons.equals(this.lessons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lessons);
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nLessons: " + lessons;
    }
}
