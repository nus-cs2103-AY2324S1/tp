package seedu.address.model.course;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.model.course.Course.MESSAGE_CONSTRAINTS;
import static seedu.address.model.course.Course.isValidCourseCode;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.course.exceptions.CourseNotFoundException;
import seedu.address.model.course.exceptions.LessonNotFoundException;

/**
 * Represents a list of courses with TAs in SOC. Immutable.
 */
public class UniqueCourseList implements Iterable<Course> {
    private static final ObservableList<Course> internalList = FXCollections.observableArrayList(
            CourseData.getCourseList()
    );
    private static final ObservableList<Course> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns a course with the given course code.
     */
    public static Course findByCourseCode(String courseCode) {
        checkArgument(isValidCourseCode(courseCode), MESSAGE_CONSTRAINTS); //Check if the course code is valid
        for (Course course : internalList) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        throw new CourseNotFoundException();
    }

    /**
     * Returns a lesson with the given course code and lesson name.
     */
    public static Lesson findLesson(String courseCode, String name) {
        Course course = findByCourseCode(courseCode);
        for (Lesson lesson : course.getLessons()) {
            if (lesson.getName().equals(name)) {
                return lesson;
            }
        }
        throw new LessonNotFoundException();
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public static ObservableList<Course> getList() {
        return internalUnmodifiableList;
    }

    public boolean contains(Course toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameCourse);
    }

    /**
     * Adds a course to the list.
     * The course must not already exist in the list.
     */
    public void add(Course toAdd) {
        internalList.add(toAdd);
    }

    public void setCourses(List<Course> replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement);
    }

    @Override
    public Iterator<Course> iterator() {
        return internalList.iterator();
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

}
