package seedu.address.model.predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.ATTENDANCE_W0_ABSENT;
import static seedu.address.logic.commands.CommandTestUtil.ATTENDANCE_W1_ABSENT;
import static seedu.address.logic.commands.CommandTestUtil.ATTENDANCE_W1_PRESENT;
import static seedu.address.logic.commands.CommandTestUtil.OPTIONAL_TAG_G01;
import static seedu.address.logic.commands.CommandTestUtil.OPTIONAL_TAG_T09;
import static seedu.address.logic.commands.CommandTestUtil.TAG_G01;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_G01;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_T09;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.week.Week;
import seedu.address.testutil.PersonBuilder;

public class AbsentFromTutorialPredicateTest {
    @Test
    public void test_absentFromTutorialWhenStudentAbsent_returnsTrue() {
        // With person absent
        AbsentFromTutorialPredicate predicate = new AbsentFromTutorialPredicate(
                new Week(1), OPTIONAL_TAG_G01);
        Person person = new PersonBuilder().withTags(VALID_TAG_G01).build();
        person.addAttendance(ATTENDANCE_W1_ABSENT);
        assertTrue(predicate.test(person));

        // Ignore case
        AbsentFromTutorialPredicate predicate2 = new AbsentFromTutorialPredicate(
                new Week(1), OPTIONAL_TAG_G01);
        Person person2 = new PersonBuilder().withTags("g01").build();
        person2.addAttendance(ATTENDANCE_W1_ABSENT);
        assertTrue(predicate2.test(person2));

        // With no tag
        AbsentFromTutorialPredicate predicate3 = new AbsentFromTutorialPredicate(
                new Week(1), Optional.empty());
        Person person3 = new PersonBuilder().withTags(VALID_TAG_G01).build();
        person3.addAttendance(ATTENDANCE_W1_ABSENT);
        assertTrue(predicate3.test(person3));
    }

    @Test
    public void test_absentFromTutorialWhenStudentPresent_returnsFalse() {
        // With person not absent
        AbsentFromTutorialPredicate predicate = new AbsentFromTutorialPredicate(
                new Week(1), OPTIONAL_TAG_G01);
        Person person = new PersonBuilder().withTags(VALID_TAG_G01).build();
        person.addAttendance(ATTENDANCE_W1_PRESENT);
        assertFalse(predicate.test(person));

        // With multiple attendances
        AbsentFromTutorialPredicate predicate2 = new AbsentFromTutorialPredicate(
                new Week(1), OPTIONAL_TAG_G01);
        Person person2 = new PersonBuilder().withTags(VALID_TAG_G01).build();
        person2.addAttendance(ATTENDANCE_W1_ABSENT);
        person2.addAttendance(ATTENDANCE_W1_PRESENT);
        assertFalse(predicate2.test(person2));
    }

    @Test
    public void test_absentFromTutorialWhenStudentAbsentFromOtherWeekOnly_returnsFalse() {
        AbsentFromTutorialPredicate predicate = new AbsentFromTutorialPredicate(
                new Week(1), OPTIONAL_TAG_G01);
        Person person2 = new PersonBuilder().withTags(VALID_TAG_G01).build();
        person2.addAttendance(ATTENDANCE_W0_ABSENT);
        person2.addAttendance(ATTENDANCE_W1_PRESENT);
        assertFalse(predicate.test(person2));
    }

    @Test
    public void test_absentFromTutorialWhenStudentHasDifferentTag_returnsFalse() {
        AbsentFromTutorialPredicate predicate = new AbsentFromTutorialPredicate(
                new Week(1), OPTIONAL_TAG_G01);
        Person person2 = new PersonBuilder().withTags(VALID_TAG_T09).build();
        person2.addAttendance(ATTENDANCE_W1_ABSENT);
        assertFalse(predicate.test(person2));
    }

    @Test
    public void equals() {
        Week firstWeek = new Week(1);
        Week secondWeek = new Week(2);

        AbsentFromTutorialPredicate firstPredicate = new AbsentFromTutorialPredicate(firstWeek, OPTIONAL_TAG_G01);
        AbsentFromTutorialPredicate secondPredicate = new AbsentFromTutorialPredicate(secondWeek, OPTIONAL_TAG_T09);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        AbsentFromTutorialPredicate firstPredicateCopy = new AbsentFromTutorialPredicate(firstWeek, OPTIONAL_TAG_G01);
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
        Week week = new Week(1);
        AbsentFromTutorialPredicate predicate = AbsentFromTutorialPredicate.create(week, OPTIONAL_TAG_G01);

        AbsentFromTutorialPredicate expected = new AbsentFromTutorialPredicate(week, OPTIONAL_TAG_G01);
        assertEquals(expected, predicate);
    }

    @Test
    public void toStringMethod() {
        Week week = new Week(1);
        AbsentFromTutorialPredicate predicateWithTag = AbsentFromTutorialPredicate.create(week, OPTIONAL_TAG_G01);

        assertEquals("Attendance Filter: " + week + " " + TAG_G01.getTagName(), predicateWithTag.toString());

        Optional<Tag> noTag = Optional.empty();
        AbsentFromTutorialPredicate predicateNoTag = AbsentFromTutorialPredicate.create(week, noTag);

        assertEquals("Attendance Filter: " + week + " null", predicateNoTag.toString());
    }
}
