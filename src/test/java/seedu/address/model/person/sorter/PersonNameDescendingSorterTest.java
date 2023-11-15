package seedu.address.model.person.sorter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class PersonNameDescendingSorterTest {

    @Test
    public void compareTo_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new PersonNameDescendingSorter().compare(null, ALICE));
    }

    @Test
    public void compareTo_sameName_returnsTrue() {
        Person firstAlice = new PersonBuilder(ALICE).build();
        Person secondAlice = new PersonBuilder(ALICE).build();

        PersonNameDescendingSorter sorter = new PersonNameDescendingSorter();

        assertTrue(sorter.compare(firstAlice, secondAlice) == 0);
    }

    @Test
    public void compareTo_differentName_returnsTrue() {
        PersonNameDescendingSorter sorter = new PersonNameDescendingSorter();

        // EP: Person 1's name comes before Person 2's name
        assertTrue(sorter.compare(ALICE, BOB) > 0);

        // EP: Person 2's name comes after Person 1's name
        assertTrue(sorter.compare(BOB, ALICE) < 0);
    }

    @Test
    public void compareTo_differentLength_returnsTrue() {
        Person shortNameHoon = new PersonBuilder().withName("Hoon").withPhone("8482424").build();
        Person longNameHoon = new PersonBuilder().withName("Hoon Meier Tan").withPhone("8482424").build();

        PersonNameDescendingSorter sorter = new PersonNameDescendingSorter();

        assertTrue(sorter.compare(shortNameHoon, longNameHoon) > 0);
    }

    @Test
    public void compareTo_sameNameDifferentCase_returnsSortedInReverseOrder() {
        Person lowercaseHoon = new PersonBuilder().withName("hoon").withPhone("8482424").build();
        Person uppercaseHoon = new PersonBuilder().withName("HOON").withPhone("8482424").build();
        Person mixedCaseHoon = new PersonBuilder().withName("hoOn").withPhone("8482424").build();
        Person secondMixedCaseHoon = new PersonBuilder().withName("HOoN").withPhone("8482424").build();

        PersonNameDescendingSorter sorter = new PersonNameDescendingSorter();

        // Name in the same case should be treated equally
        assertTrue(sorter.compare(uppercaseHoon, uppercaseHoon) == 0);
        assertTrue(sorter.compare(mixedCaseHoon, mixedCaseHoon) == 0);

        // Name in uppercase should come before lowercase
        assertTrue(sorter.compare(uppercaseHoon, lowercaseHoon) < 0);

        // Name that has an uppercase character in between should come before the same name in lower case
        assertTrue(sorter.compare(mixedCaseHoon, lowercaseHoon) < 0);

        // Name that has lowercase characters in between should come after the same name in upper case
        assertTrue(sorter.compare(secondMixedCaseHoon, uppercaseHoon) > 0);

        // Negative test: Names in different cases should not be treated equally.
        assertFalse(sorter.compare(lowercaseHoon, mixedCaseHoon) == 0);
    }

    @Test
    public void compareTo_differentNameDifferentCase_returnsSortedInOrder() {
        Person lowerCaseJohn = new PersonBuilder().withName("john 2").withPhone("8482424").build();
        Person mixedCaseJohn123 = new PersonBuilder().withName("John 123").withPhone("8482424").build();
        Person mixedCaseJohn124 = new PersonBuilder().withName("John 124").withPhone("8482424").build();
        Person johnCena = new PersonBuilder().withName("John Cena").withPhone("8482424").build();
        Person johncena = new PersonBuilder().withName("John cena").withPhone("8482424").build();
        Person johnDena = new PersonBuilder().withName("John Dena").withPhone("8482424").build();
        Person lowerCaseJoin2 = new PersonBuilder().withName("join 2").withPhone("8482424").build();
        Person numbersOnly = new PersonBuilder().withName("223987").withPhone("8482424").build();

        PersonNameDescendingSorter sorter = new PersonNameDescendingSorter();

        // Numbers come after the alphabet
        assertTrue(sorter.compare(numbersOnly, mixedCaseJohn123) > 0);
        assertTrue(sorter.compare(mixedCaseJohn123, johnCena) > 0);

        // Numbers are arranged in reverse order
        assertTrue(sorter.compare(mixedCaseJohn123, mixedCaseJohn124) > 0);

        // The letters are arranged in reverse order
        assertTrue(sorter.compare(johnCena, johnDena) > 0);

        // The same letters are arranged in reverse order of their cases
        assertTrue(sorter.compare(johnCena, johncena) < 0);

        // Regardless of case, if names are different, they should be sorted in reverse alphabetical order.
        assertTrue(sorter.compare(lowerCaseJohn, lowerCaseJoin2) > 0);
    }

    @Test
    public void compareTo_differentLength_returnsSortedInOrder() {
        Person upperCaseJohn = new PersonBuilder().withName("JOHN").withPhone("8482424").build();
        Person secondUppercaseJohn = new PersonBuilder().withName("JOHN 2").withPhone("8482424").build();
        Person mixedCaseJohnC = new PersonBuilder().withName("John C").withPhone("8482424").build();
        Person johnCena = new PersonBuilder().withName("John Cena").withPhone("8482424").build();

        PersonNameDescendingSorter sorter = new PersonNameDescendingSorter();

        // If a name is a prefix of another name, it should come later
        assertTrue(sorter.compare(mixedCaseJohnC, johnCena) > 0);
        assertTrue(sorter.compare(upperCaseJohn, secondUppercaseJohn) > 0);

        // Negative test: Names of different lengths should not be treated equally
        assertFalse(sorter.compare(secondUppercaseJohn, upperCaseJohn) == 0);
    }

    @Test
    public void compareTo_sameNameWithNumbers_returnsSortedInReverseOrder() {
        Person lowercaseHoon = new PersonBuilder().withName("hoon 1").withPhone("8482424").build();
        Person secondLowercaseHoon = new PersonBuilder().withName("hoon 2").withPhone("8482424").build();

        PersonNameDescendingSorter sorter = new PersonNameDescendingSorter();

        // Same names with same numbers should be treated equally
        assertTrue(sorter.compare(lowercaseHoon, lowercaseHoon) == 0);

        // Negative test: Names with different numbers should not be treated equally.
        assertFalse(sorter.compare(lowercaseHoon, secondLowercaseHoon) == 0);
    }

    @Test
    public void equals() {
        PersonNameDescendingSorter personNameDescendingSorter = new PersonNameDescendingSorter();

        // EP: same class -> returns true
        assertTrue(personNameDescendingSorter
                .equals(new PersonNameDescendingSorter()));

        // ep: same object -> returns true
        assertTrue(personNameDescendingSorter.equals(personNameDescendingSorter));

        // EP: null -> returns false
        assertFalse(personNameDescendingSorter.equals(null));

        // EP: different types -> returns false
        assertFalse(personNameDescendingSorter.equals(5.0f));
    }
}
