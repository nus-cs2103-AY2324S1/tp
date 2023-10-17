package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class AbsentFromTutorialNumPredicateTest {

    @Test
    public void test_absentFromTutorialNum_returnsTrue() {
        // With person absent
        AbsentFromTutorialNumPredicate predicate = new AbsentFromTutorialNumPredicate(
                Index.fromOneBased(1), new Tag("CS2103T"));
        Person person = new PersonBuilder().withTags("CS2103T").build();
        person.addAttendance(new Attendance(LocalDate.now(), false, "CS2103T"));
        assertTrue(predicate.test(person));
    }

    @Test
    public void test_absentFromTutorialNum_returnsFalse() {
        // With person not absent
        AbsentFromTutorialNumPredicate predicate = new AbsentFromTutorialNumPredicate(
                Index.fromOneBased(1), new Tag("CS2103T"));
        Person person = new PersonBuilder().withTags("CS2103T").build();
        person.addAttendance(new Attendance(LocalDate.now(), true, "CS2103T"));
        assertFalse(predicate.test(person));

        // With multiple attendances
        AbsentFromTutorialNumPredicate predicate2 = new AbsentFromTutorialNumPredicate(
                Index.fromOneBased(3), new Tag("CS2103T"));
        Person person2 = new PersonBuilder().withTags("CS2103T").build();
        person2.addAttendance(new Attendance(LocalDate.now(), false, "CS2103T"));
        person2.addAttendance(new Attendance(LocalDate.now(), true, "CS2103T"));
        person2.addAttendance(new Attendance(LocalDate.now(), true, "CS2103T"));
        assertFalse(predicate2.test(person2));

        // With wrong tag
        AbsentFromTutorialNumPredicate predicate3 = new AbsentFromTutorialNumPredicate(
                Index.fromOneBased(1), new Tag("CS2103T"));
        Person person3 = new PersonBuilder().withTags("CS2030S").build();
        person.addAttendance(new Attendance(LocalDate.now(), false, "CS2030S"));
        assertFalse(predicate3.test(person3));
    }

}

