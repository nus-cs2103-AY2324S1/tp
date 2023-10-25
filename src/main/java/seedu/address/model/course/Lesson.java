package seedu.address.model.course;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;

/**
 * Represents a lesson in a course.
 */
public class Lesson {
    private final String name;
    private final String courseCode;
    private final DayOfWeek dayOfWeek;
    private final LocalTime startTime;
    private final LocalTime endTime;

    /**
     * Constructs a {@code Lesson}.
     *
     * @param name      A lesson name.
     * @param courseCode A course code.
     * @param dayOfWeek A day of the week.
     * @param startTime A start time.
     * @param endTime   An end time.
     */
    public Lesson(String name, String courseCode, DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        requireAllNonNull(name, courseCode, dayOfWeek, startTime, endTime);
        this.name = name;
        this.courseCode = courseCode;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Constructs a {@code Lesson}.
     * @param name      A lesson name.
     * @param courseCode A course code.
     * @param dayOfWeek A day of the week.
     * @param startTime A start time.
     * @param endTime   An end time.
     */
    public Lesson(String name, String courseCode, String dayOfWeek, String startTime, String endTime) {
        requireAllNonNull(name, courseCode, dayOfWeek, startTime, endTime);
        this.name = name;
        this.courseCode = courseCode;
        this.dayOfWeek = DayOfWeek.valueOf(dayOfWeek);
        this.startTime = LocalTime.parse(startTime);
        this.endTime = LocalTime.parse(endTime);
    }

    public String getCourseCode() {
        return courseCode;
    }
    public String getName() {
        return name;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof Lesson)) {
            return false;
        }
        Lesson otherLesson = (Lesson) other;
        return otherLesson.name.equals(this.name)
                && otherLesson.courseCode.equals(this.courseCode)
                && otherLesson.dayOfWeek.equals(this.dayOfWeek)
                && otherLesson.startTime.equals(this.startTime)
                && otherLesson.endTime.equals(this.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, courseCode, startTime, endTime);
    }

    @Override
    public String toString() {
        return String.format("%s %s %s-%s", courseCode, name, startTime, endTime);
    }

}
