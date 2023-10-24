package seedu.address.model.course;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a lesson in a course.
 */
public class Lesson {
    private String name;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    public Lesson(String name, DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        requireAllNonNull(name, dayOfWeek, startTime, endTime);
        this.name = name;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
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
                && otherLesson.startTime.equals(this.startTime)
                && otherLesson.endTime.equals(this.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, startTime, endTime);
    }

    @Override
    public String toString() {
        return String.format("%s %s-%s", name, startTime, endTime);
    }

}
