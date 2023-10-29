package seedu.address.model.course;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.DayOfWeek;
import java.util.Objects;

import seedu.address.model.availability.TimeInterval;

/**
 * Represents a lesson in a course.
 */
public class Lesson {
    private final String name;
    private final String courseCode;
    private final DayOfWeek dayOfWeek;
    private final TimeInterval timeInterval;

    /**
     * Constructs a {@code Lesson}.
     *
     * @param name      A lesson name.
     * @param courseCode A course code.
     * @param dayOfWeek A day of the week.
     * @param timeInterval A time interval representing start and end time of lesson.
     */
    public Lesson(String name, String courseCode, DayOfWeek dayOfWeek, TimeInterval timeInterval) {
        requireAllNonNull(name, courseCode, dayOfWeek, timeInterval);
        this.name = name;
        this.courseCode = courseCode;
        this.dayOfWeek = dayOfWeek;
        this.timeInterval = timeInterval;
    }

//    /**
//     * Constructs a {@code Lesson}.
//     * @param name      A lesson name.
//     * @param courseCode A course code.
//     * @param dayOfWeek A day of the week.
//     * @param startTime A start time.
//     * @param endTime   An end time.
//     */
//    public Lesson(String name, String courseCode, String dayOfWeek, String startTime, String endTime) {
//        requireAllNonNull(name, courseCode, dayOfWeek, startTime, endTime);
//        this.name = name;
//        this.courseCode = courseCode;
//        this.dayOfWeek = DayOfWeek.valueOf(dayOfWeek);
//        this.timeInterval = new TimeInterval(LocalTime.parse(startTime), LocalTime.parse(endTime));
//    }

    public String getCourseCode() {
        return courseCode;
    }
    public String getName() {
        return name;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public TimeInterval getTimeInterval() {
        return timeInterval;
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
        System.out.println(otherLesson);
        System.out.println(this);
        System.out.println(otherLesson.name.equals(this.name));
        return otherLesson.name.equals(this.name)
                && otherLesson.courseCode.equals(this.courseCode)
                && otherLesson.dayOfWeek.equals(this.dayOfWeek)
                && otherLesson.timeInterval.equals(this.timeInterval);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, courseCode, timeInterval);
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", courseCode, name, timeInterval);
    }

}
