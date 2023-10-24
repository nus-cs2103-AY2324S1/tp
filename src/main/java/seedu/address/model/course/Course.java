package seedu.address.model.course;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.CourseTag;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.tag.CourseTag.MESSAGE_CONSTRAINTS;
import static seedu.address.model.tag.CourseTag.isValidCourseCode;

/**
 * Represents a course that the user is managing
 */
public class Course {
    private String name;
    private String courseCode;
    private Set<Lesson> lessons = new HashSet<>();

    private CourseTag courseTag;

    public Course(String name, String courseCode, Set<Lesson> lessons) {
        requireAllNonNull(name, courseCode, lessons);
        checkArgument(isValidCourseCode(courseCode), MESSAGE_CONSTRAINTS); //Check if course code is valid
        this.name = name;
        this.courseCode = courseCode;
        this.courseTag = CourseTag.of(courseCode);
        this.lessons.addAll(lessons);
    }

    public CourseTag getCourseTag() {
        return courseTag;
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
                && otherCourse.courseTag.equals(this.courseTag)
                && otherCourse.lessons.equals(this.lessons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, courseTag, lessons);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("courseTag", courseTag)
                .add("lessons", lessons)
                .toString();
    }
}
