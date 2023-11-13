package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class HasPolicyPredicateTest {
    @Test
    public void test_personWithPolicy_returnTrue() {
        Person alice = new PersonBuilder(ALICE).build();
        Predicate<Person> p = new HasPolicyPredicate();
        assertTrue(p.test(alice));
    }

    @Test
    public void test_personWithDefaultPolicy_returnFalse() {
        Person person = new PersonBuilder().withDefaultPolicy().build();
        Predicate<Person> p = new HasPolicyPredicate();
        assertFalse(p.test(person));
    }

    @Test
    public void equals() {
        Predicate<Person> p1 = new HasPolicyPredicate();
        Predicate<Person> p2 = new HasPolicyPredicate();

        Person alice = new PersonBuilder(ALICE).build();

        assertTrue(p1.equals(p1));
        assertTrue(p1.equals(p2));

        assertFalse(p1.equals(null));
        assertFalse(p1.equals(alice));
    }
}
