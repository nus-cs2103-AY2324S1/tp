package seedu.address.model.person.sorter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class PersonCourseSizeAscendingSorterTest {

    @Test
    public void compareTo_nullPerson_throwsNullPointerException() {
        Person oneCourseAlice = new PersonBuilder(ALICE).withCourses("MA2001").build();

        assertThrows(NullPointerException.class, () ->
                new PersonCourseSizeAscendingSorter().compare(null, oneCourseAlice));
    }

    @Test
    public void compareTo_sameCourseSizeSameName_returnsTrue() {
        Person oneCourseAlice = new PersonBuilder(ALICE).withCourses("MA2001").build();
        Person anotherOneCourseAlice = new PersonBuilder(ALICE).withCourses("EC2101").build();

        PersonCourseSizeAscendingSorter sorter = new PersonCourseSizeAscendingSorter();

        // EP: Person 1 has the same number of courses as Person 2
        assertTrue(sorter.compare(oneCourseAlice, anotherOneCourseAlice) == 0);
    }

    @Test
    public void compareTo_differentCourseSizeSameName_returnsTrue() {
        Person oneCourseAlice = new PersonBuilder(ALICE).withCourses("MA2001").build();
        Person twoCourseAlice = new PersonBuilder(ALICE).withCourses("MA2001", "EC2101").build();

        PersonCourseSizeAscendingSorter sorter = new PersonCourseSizeAscendingSorter();

        // EP: Person 1 has fewer courses than Person 2
        assertTrue(sorter.compare(oneCourseAlice, twoCourseAlice) < 0);

        // EP: Person 2 has fewer courses than Person 1
        assertTrue(sorter.compare(twoCourseAlice, oneCourseAlice) > 0);
    }

    @Test
    public void compareTo_differentCourseSizeAndName_returnsTrue() {

        Person oneCourseAlice = new PersonBuilder(ALICE).withCourses("MA2001").build();
        Person twoCourseBob = new PersonBuilder(BOB).withCourses("MA2001", "EC2101").build();

        PersonCourseSizeAscendingSorter sorter = new PersonCourseSizeAscendingSorter();

        // EP: Person 1 has fewer courses than Person 2, so it should come before Person 2
        assertTrue(sorter.compare(oneCourseAlice, twoCourseBob) < 0);

        // EP: Person 1 has more courses than Person 2, so it should come after Person 2
        assertTrue(sorter.compare(twoCourseBob, oneCourseAlice) > 0);
    }

    @Test
    public void compareTo_zeroCourseSizeForOnePerson_returnsTrue() {

        Person zeroCourseAlice = new PersonBuilder(ALICE).withCourses().build();
        Person twoCourseBob = new PersonBuilder(BOB).withCourses("MA2001", "EC2101").build();

        PersonCourseSizeAscendingSorter sorter = new PersonCourseSizeAscendingSorter();

        // EP: Person 1 has fewer courses than Person 2, so it should come before Person 2
        assertTrue(sorter.compare(zeroCourseAlice, twoCourseBob) < 0);

        // EP: Person 2 has more courses than Person 1, so it should come after Person 1
        assertTrue(sorter.compare(twoCourseBob, zeroCourseAlice) > 0);
    }

    @Test
    public void compareTo_sameCourseSizeAndName_returnsTrue() {

        Person zeroCourseAlice = new PersonBuilder(ALICE).withCourses().build();
        Person anotherZeroCourseAlice = new PersonBuilder(ALICE).withCourses().build();

        PersonCourseSizeAscendingSorter sorter = new PersonCourseSizeAscendingSorter();

        assertTrue(sorter.compare(zeroCourseAlice, anotherZeroCourseAlice) == 0);
    }

    @Test
    public void compareTo_sameCourseSizeDifferentName_returnsTrue() {

        Person oneCourseAlice = new PersonBuilder(ALICE).withCourses("MA2001").build();
        Person oneCourseBob = new PersonBuilder(BOB).withCourses("EC2101").build();

        PersonCourseSizeAscendingSorter sorter = new PersonCourseSizeAscendingSorter();

        // EP: Person 1 has the same number of courses as Person 2, but Person 1's name comes first.
        assertTrue(sorter.compare(oneCourseAlice, oneCourseBob) < 0);

        // EP: Person 1 has the same number of courses as Person 2, but Person 2's name comes first.
        assertTrue(sorter.compare(oneCourseBob, oneCourseAlice) > 0);
    }

    @Test
    public void equals() {
        PersonCourseSizeAscendingSorter personCourseSizeAscendingSorter = new PersonCourseSizeAscendingSorter();

        // EP: same class -> returns true
        assertTrue(personCourseSizeAscendingSorter
                .equals(new PersonCourseSizeAscendingSorter()));

        // EP: same object -> returns true
        assertTrue(personCourseSizeAscendingSorter.equals(personCourseSizeAscendingSorter));

        // EP: null -> returns false
        assertFalse(personCourseSizeAscendingSorter.equals(null));

        // EP: different types -> returns false
        assertFalse(personCourseSizeAscendingSorter.equals(5.0f));
    }
}
