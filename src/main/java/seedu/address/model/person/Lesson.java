package seedu.address.model.person;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static java.util.Objects.requireNonNull;

/**
 * Represents the day and time when the Tutee has tuition every week.
 * Guarantees: immutable; is valid as declared in
 */
public class Lesson {
    public final DayOfWeek day;

    public final LocalTime begin;

    public final LocalTime end;

    /**
     * Constructs a {@code DayTime}.
     *
     * @param day   A valid day.
     * @param begin A valid begin.
     * @param end   A valid end.
     */
    public Lesson(Day day, Begin begin, End end) {
        requireNonNull(day);
        requireNonNull(begin);
        requireNonNull(end);

        this.day = day.value;
        this.begin = begin.getTime();
        this.end = end.getTime();
    }

    @Override
    public String toString() {
        return day.toString() + ", " + begin.toString() + " - " + end.toString();
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
        return day.equals(otherLesson.day)
                && begin.equals(otherLesson.begin)
                && end.equals(otherLesson.end);
    }

}
