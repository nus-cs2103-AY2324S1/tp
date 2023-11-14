package connectify.model.person;

import static connectify.testutil.TypicalPersons.ALICE;
import static connectify.testutil.TypicalPersons.BOB;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

/**
 * Compares a {@code Person} to another using their {@code Priority}.
 */
public class PersonPriorityComparatorTest {

    @Test
    public void compare() {
        Comparator<Person> comparator = new PersonPriorityComparator();
        assertEquals(comparator.compare(ALICE, BOB), 1);
        assertEquals(comparator.compare(ALICE, ALICE), 0);
        assertEquals(comparator.compare(BOB, ALICE), -1);
    }
}
