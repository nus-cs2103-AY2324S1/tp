package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.course.Course;
import seedu.address.model.course.UniqueCourseList;
import seedu.address.model.person.Person;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class Courses implements ReadOnlyCourses {

    private final UniqueCourseList courses;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        courses = new UniqueCourseList();
    }

    public Courses() {}

    public Courses(ReadOnlyCourses toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setCourses(List<Course> courses) {
        this.courses.setCourses(courses);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyCourses newData) {
        requireNonNull(newData);
        setCourses(newData.getCourseList());
    }

    public boolean hasCourse(Course c) {
        requireNonNull(c);
        return courses.contains(c);
    }

    /**
     * Adds a course to the courses data.
     * The course must not already exist in the courses data.
     */
    public void addCourse(Course c) {
        courses.add(c);
    }

    @Override
    public ObservableList<Course> getCourseList() {
        return UniqueCourseList.getList();
    }
}
