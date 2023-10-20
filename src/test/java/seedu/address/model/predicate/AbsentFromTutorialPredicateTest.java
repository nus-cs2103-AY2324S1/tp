package seedu.address.model.predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.person.Attendance;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class AbsentFromTutorialPredicateTest {
    @Test
    public void test_absentFromTutorialNum_returnsTrue() {
        // With person absent
        AbsentFromTutorialPredicate predicate = new AbsentFromTutorialPredicate(
                Index.fromOneBased(1), new Tag("CS2103T"));
        Person person = new PersonBuilder().withTags("CS2103T").build();
        person.addAttendance(new Attendance(LocalDate.now(), false));
        assertTrue(predicate.test(person));

        // Ignore case
        AbsentFromTutorialPredicate predicate2 = new AbsentFromTutorialPredicate(
                Index.fromOneBased(1), new Tag("CS2103T"));
        Person person2 = new PersonBuilder().withTags("Cs2103t").build();
        person2.addAttendance(new Attendance(LocalDate.now(), false));
        assertTrue(predicate2.test(person2));

        // With no tag
        AbsentFromTutorialPredicate predicate3 = new AbsentFromTutorialPredicate(
                Index.fromOneBased(1), new Tag("PLACEHOLDER"));
        Person person3 = new PersonBuilder().withTags("CS2103T").build();
        person3.addAttendance(new Attendance(LocalDate.now(), false));
        assertTrue(predicate3.test(person3));
    }

    @Test
    public void test_absentFromTutorialNum_returnsFalse() {
        // With person not absent
        AbsentFromTutorialPredicate predicate = new AbsentFromTutorialPredicate(
                Index.fromOneBased(1), new Tag("CS2103T"));
        Person person = new PersonBuilder().withTags("CS2103T").build();
        person.addAttendance(new Attendance(LocalDate.now(), true));
        assertFalse(predicate.test(person));

        // With multiple attendances
        AbsentFromTutorialPredicate predicate2 = new AbsentFromTutorialPredicate(
                Index.fromOneBased(3), new Tag("CS2103T"));
        Person person2 = new PersonBuilder().withTags("CS2103T").build();
        person2.addAttendance(new Attendance(LocalDate.now(), false));
        person2.addAttendance(new Attendance(LocalDate.now(), true));
        person2.addAttendance(new Attendance(LocalDate.now(), true));
        assertFalse(predicate2.test(person2));

        // With wrong tag
        AbsentFromTutorialPredicate predicate3 = new AbsentFromTutorialPredicate(
                Index.fromOneBased(1), new Tag("CS2103T"));
        Person person3 = new PersonBuilder().withTags("CS2030S").build();
        person.addAttendance(new Attendance(LocalDate.now(), false));
        assertFalse(predicate3.test(person3));
    }


    @Test
    public void equals() {
        Tag firstTag = new Tag("first");
        Tag secondTag = new Tag("second");
        Index firstIndex = Index.fromZeroBased(1);
        Index secondIndex = Index.fromZeroBased(2);

        AbsentFromTutorialPredicate firstPredicate = new AbsentFromTutorialPredicate(firstIndex, firstTag);
        AbsentFromTutorialPredicate secondPredicate = new AbsentFromTutorialPredicate(secondIndex, secondTag);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        AbsentFromTutorialPredicate firstPredicateCopy = new AbsentFromTutorialPredicate(firstIndex, firstTag);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void create() {
        Tag tag = Tag.create("CS2103T T02");
        Index index = Index.fromZeroBased(0);
        AbsentFromTutorialPredicate predicate = AbsentFromTutorialPredicate.create(index, tag);

        AbsentFromTutorialPredicate expected = new AbsentFromTutorialPredicate(index, tag);
        assertEquals(expected, predicate);
    }

    @Test
    public void toStringMethod() {
        Tag tag = Tag.create("CS2103T T02");
        Index index = Index.fromZeroBased(0);
        AbsentFromTutorialPredicate predicate = AbsentFromTutorialPredicate.create(index, tag);

        assertEquals("Attendance Filter: " + index + " " + tag.getTagName(), predicate.toString());
    }
}
