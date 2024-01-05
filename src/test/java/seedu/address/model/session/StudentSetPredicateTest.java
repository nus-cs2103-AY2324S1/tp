package seedu.address.model.session;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.SessionBuilder;
import seedu.address.testutil.TypicalPersons;

public class StudentSetPredicateTest {

    @Test
    public void equals() {
        Set<Person> firstPredicateStudentsSet = Collections.singleton(
                new PersonBuilder(TypicalPersons.ALICE).build());
        Set<Person> secondPredicateStudentsSet = new HashSet<>(Arrays.asList(
                new PersonBuilder(TypicalPersons.ALICE).build(), new PersonBuilder(TypicalPersons.BOB).build()));

        SessionStudentsContainsStudentsPredicate firstPredicate =
                new SessionStudentsContainsStudentsPredicate(firstPredicateStudentsSet);
        SessionStudentsContainsStudentsPredicate secondPredicate =
                new SessionStudentsContainsStudentsPredicate(secondPredicateStudentsSet);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        SessionStudentsContainsStudentsPredicate firstPredicateCopy =
                new SessionStudentsContainsStudentsPredicate(firstPredicateStudentsSet);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different predicate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_sessionStudentsContainsStudents_returnsTrue() {
        // One keyword
        Set<Person> firstPredicateStudentsSet = Collections.singleton(new PersonBuilder(TypicalPersons.ALICE).build());
        SessionStudentsContainsStudentsPredicate predicate =
                new SessionStudentsContainsStudentsPredicate(firstPredicateStudentsSet);
        assertTrue(predicate.test(new SessionBuilder().withStudent(TypicalPersons.ALICE).build()));

        // Multiple keywords
        Set<Person> secondPredicateStudentsSet = new HashSet<>(Arrays.asList(
                new PersonBuilder(TypicalPersons.ALICE).build(), new PersonBuilder(TypicalPersons.BOB).build()));
        predicate = new SessionStudentsContainsStudentsPredicate(secondPredicateStudentsSet);
        assertTrue(predicate.test(new SessionBuilder().withStudents(TypicalPersons.ALICE, TypicalPersons.BOB).build()));

        // Only one matching keyword
        predicate = new SessionStudentsContainsStudentsPredicate(secondPredicateStudentsSet);
        assertTrue(predicate.test(new SessionBuilder().withStudent(TypicalPersons.BOB).build()));
    }

    @Test
    public void test_sessionStudentDoesNotContainStudents_returnsFalse() {
        // Zero keywords
        SessionStudentsContainsStudentsPredicate predicate =
                new SessionStudentsContainsStudentsPredicate(Collections.emptySet());
        assertFalse(predicate.test(new SessionBuilder().withStudent(TypicalPersons.CARL).build()));

        // Non-matching keyword
        Set<Person> studentSet = Collections.singleton(new PersonBuilder().withName("Alice").build());
        predicate = new SessionStudentsContainsStudentsPredicate(studentSet);
        assertFalse(predicate.test(new SessionBuilder().withStudent(TypicalPersons.CARL).build()));
    }

    @Test
    public void toStringMethod() {
        Set<Person> studentSet = new HashSet<>(Arrays.asList(
                new PersonBuilder().withName("student1").build(), new PersonBuilder().withName("student2").build()));
        SessionStudentsContainsStudentsPredicate predicate = new SessionStudentsContainsStudentsPredicate(studentSet);

        String expected = SessionStudentsContainsStudentsPredicate.class.getCanonicalName()
                + "{session students=" + studentSet + "}";
        assertEquals(expected, predicate.toString());
    }
}
