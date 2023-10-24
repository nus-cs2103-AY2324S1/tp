package seedu.address.model.person;

import java.util.Comparator;

/**
 * Compares a {@code Person}'s {@code Lesson} against another {@code Lesson}.
 */
public class LessonComparator implements Comparator<Person> {
    @Override
    public int compare(Person person1, Person person2) {
        Lesson lesson1 = person1.getLesson();
        Lesson lesson2 = person2.getLesson();

        // Compare first by day of the week
        int dayComparison = lesson1.day.compareTo(lesson2.day);
        if (dayComparison != 0) {
            return dayComparison;
        }

        // If days are the same, compare by start time
        return lesson1.begin.compareTo(lesson2.begin);
    }
}
