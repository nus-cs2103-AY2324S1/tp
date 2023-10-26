package networkbook.model.person.filterCourse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.function.Predicate;

import networkbook.model.person.Course;

/**
 * Checks that a course is still being taken by the given date.
 *
 * Assumption: No start date -> Course is still being taken.
 * Otherwise returns true when the given date is between the
 */
public class CourseIsStillBeingTakenPredicate implements Predicate<Course> {
    private final LocalDate dateOfFinish;
    private final DateTimeFormatter dtf = DateTimeFormatter
            .ofPattern("dd-MM-uuuu")
            .withResolverStyle(ResolverStyle.STRICT);
    public CourseIsStillBeingTakenPredicate(LocalDate dateOfFinish) {
        this.dateOfFinish = dateOfFinish;
    }

    public static boolean dateEqualOrAfter(LocalDate date1, LocalDate date2) {
        return date1.isAfter(date2) || date1.isEqual(date2);
    }
    public static boolean dateEqualOrBefore(LocalDate date1, LocalDate date2) {
        return date1.isBefore(date2) || date1.isEqual(date2);
    }

    public boolean test(Course course) {
        if (!course.startDateExists()) {
            return true;
        }
        if (!course.endDateExists()) {
            return dateEqualOrAfter(dateOfFinish, LocalDate.parse(course.getStartDate(), dtf));
        }
        return dateEqualOrAfter(dateOfFinish, LocalDate.parse(course.getStartDate(), dtf))
                && dateEqualOrBefore(dateOfFinish, LocalDate.parse(course.getEndDate(), dtf));
    }

    @Override
    public boolean equals(Object other) {
        if (other==this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CourseIsStillBeingTakenPredicate)) {
            return false;
        }

        CourseIsStillBeingTakenPredicate otherPredicate = (CourseIsStillBeingTakenPredicate) other;
        return dateOfFinish.isEqual(otherPredicate.dateOfFinish);
    }
}
