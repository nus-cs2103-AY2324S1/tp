package seedu.address.model.session;

import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Session}'s {@code StudentSet} matches any of the keywords given.
 */
public class SessionStudentsContainsStudentsPredicate implements Predicate<Session> {
    private final Set<Person> students;

    public SessionStudentsContainsStudentsPredicate(Set<Person> students) {
        this.students = students;
    }

    @Override
    public boolean test(Session session) {
        for (Person student : students) {
            if (session.getStudents().contains(student)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SessionStudentsContainsStudentsPredicate)) {
            return false;
        }

        SessionStudentsContainsStudentsPredicate otherSessionStudentsContainsStudentsPredicate =
                (SessionStudentsContainsStudentsPredicate) other;
        return students.equals(otherSessionStudentsContainsStudentsPredicate.students);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("session students", students).toString();
    }
}
