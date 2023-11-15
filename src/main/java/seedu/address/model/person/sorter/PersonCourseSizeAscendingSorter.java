package seedu.address.model.person.sorter;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.person.Person;

/**
 * A sorter for persons in the address book based on the number of courses taken.
 * Persons are sorted from the least to the most number of courses taken.
 * Subclass of {@link PersonSorter}.
 */
public class PersonCourseSizeAscendingSorter extends PersonSorter {

    /**
     * Constructs a {@code PersonCourseSizeAscendingSorter}.
     */
    public PersonCourseSizeAscendingSorter() {
        criterion = "course size-ascending";
    }

    @Override
    public int compare(Person person1, Person person2) {
        requireAllNonNull(person1, person2);

        int person1CourseSize = person1.getCourses().size();
        int person2CourseSize = person2.getCourses().size();
        int courseSizeComparison = Integer.compare(person1CourseSize, person2CourseSize);

        if (courseSizeComparison != 0) {
            return courseSizeComparison;
        } else {
            // Sort by name when course sizes are similar.
            PersonNameAscendingSorter nameAscendingSorter = new PersonNameAscendingSorter();
            return nameAscendingSorter.compare(person1, person2);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof PersonCourseSizeAscendingSorter)) {
            return false;
        }

        PersonCourseSizeAscendingSorter otherCourseSizeAscendingSorter = (PersonCourseSizeAscendingSorter) other;
        return this.getSortingCriterion().equals(otherCourseSizeAscendingSorter.getSortingCriterion());
    }

    @Override
    public int hashCode() {
        return criterion.hashCode();
    }
}
