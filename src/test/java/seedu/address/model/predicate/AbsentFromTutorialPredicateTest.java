package seedu.address.model.predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Attendance;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.week.Week;
import seedu.address.testutil.PersonBuilder;

public class AbsentFromTutorialPredicateTest {
    @Test
    public void test_absentFromTutorialNum_returnsTrue() {
        // With person absent
        AbsentFromTutorialPredicate predicate = new AbsentFromTutorialPredicate(
                new Week(1), Optional.of(new Tag("G02")));
        Person person = new PersonBuilder().withTags("G02").build();
        person.addAttendance(new Attendance(new Week(1), false, "Sick"));
        assertTrue(predicate.test(person));

        // Ignore case
        AbsentFromTutorialPredicate predicate2 = new AbsentFromTutorialPredicate(
                new Week(1), Optional.of(new Tag("G02")));
        Person person2 = new PersonBuilder().withTags("g02").build();
        person2.addAttendance(new Attendance(new Week(1), false, "Sick"));
        assertTrue(predicate2.test(person2));

        // With no tag
        AbsentFromTutorialPredicate predicate3 = new AbsentFromTutorialPredicate(
                new Week(1), Optional.empty());
        Person person3 = new PersonBuilder().withTags("G10").build();
        person3.addAttendance(new Attendance(new Week(1), false, "Sick"));
        assertTrue(predicate3.test(person3));
    }

    @Test
    public void test_absentFromTutorialNum_returnsFalse() {
        // With person not absent
        AbsentFromTutorialPredicate predicate = new AbsentFromTutorialPredicate(
                new Week(1), Optional.of(new Tag("G01")));
        Person person = new PersonBuilder().withTags("G01").build();
        person.addAttendance(new Attendance(new Week(1), true));
        assertFalse(predicate.test(person));

        // With multiple attendances
        AbsentFromTutorialPredicate predicate2 = new AbsentFromTutorialPredicate(
                new Week(1), Optional.of(new Tag("G01")));
        Person person2 = new PersonBuilder().withTags("CS2103T").build();
        person2.addAttendance(new Attendance(new Week(1), false, "Late"));
        person2.addAttendance(new Attendance(new Week(1), true));
        person2.addAttendance(new Attendance(new Week(1), true));
        assertFalse(predicate2.test(person2));

        // With wrong tag
        AbsentFromTutorialPredicate predicate3 = new AbsentFromTutorialPredicate(
                new Week(1), Optional.of(new Tag("G01")));
        Person person3 = new PersonBuilder().withTags("G02").build();
        person.addAttendance(new Attendance(new Week(1), false, "Late"));
        assertFalse(predicate3.test(person3));
    }


    @Test
    public void equals() {
        Optional<Tag> firstTag = Optional.of(new Tag("first"));
        Optional<Tag> secondTag = Optional.of(new Tag("second"));
        Week firstWeek = new Week(1);
        Week secondWeek = new Week(2);

        AbsentFromTutorialPredicate firstPredicate = new AbsentFromTutorialPredicate(firstWeek, firstTag);
        AbsentFromTutorialPredicate secondPredicate = new AbsentFromTutorialPredicate(secondWeek, secondTag);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        AbsentFromTutorialPredicate firstPredicateCopy = new AbsentFromTutorialPredicate(firstWeek, firstTag);
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
        Tag tag = Tag.create("T02");
        Week week = new Week(1);
        AbsentFromTutorialPredicate predicate = AbsentFromTutorialPredicate.create(week, Optional.of(tag));

        AbsentFromTutorialPredicate expected = new AbsentFromTutorialPredicate(week, Optional.of(tag));
        assertEquals(expected, predicate);
    }

    @Test
    public void toStringMethod() {
        Tag tag = Tag.create("T02");
        Week week = new Week(1);
        AbsentFromTutorialPredicate predicate = AbsentFromTutorialPredicate.create(week, Optional.of(tag));

        assertEquals("Attendance Filter: " + week + " " + tag.getTagName(), predicate.toString());
    }
}
