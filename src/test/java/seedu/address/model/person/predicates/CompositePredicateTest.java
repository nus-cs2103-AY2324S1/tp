package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;

public class CompositePredicateTest {

    @Test
    public void addTest() {
        CompositePredicate predicate = new CompositePredicate();

        PredicateStub elemOne = new PredicateStub(-1);
        predicate.add(elemOne);
        String expectedOneElem = CompositePredicate.class.getCanonicalName() + "{1=" + elemOne + "}";
        assertEquals(expectedOneElem, predicate.toString());

        PredicateStub elemDupe = new PredicateStub(-1);
        predicate.add(elemDupe);
        assertEquals(expectedOneElem, predicate.toString());

        PredicateStub elemTwo = new PredicateStub(1);
        predicate.add(elemTwo);
        String expectedTwoElem = CompositePredicate.class.getCanonicalName() + "{1=" + elemOne + ", 2=" + elemTwo + "}";
        assertEquals(expectedTwoElem, predicate.toString());
    }

    @Test
    public void equals() {
        CompositePredicate predicateMain = new CompositePredicate();
        CompositePredicate predicateDiff = new CompositePredicate();

        predicateMain.add(new PredicateStub(-1));
        predicateDiff.add(new PredicateStub(1));

        // same object -> returns true
        assertTrue(predicateMain.equals(predicateMain));

        // same values -> returns true
        CompositePredicate predicateSame = new CompositePredicate();
        predicateSame.add(new PredicateStub(-1));
        assertTrue(predicateMain.equals(predicateSame));

        // different types -> returns false
        assertFalse(predicateMain.equals(1));

        // null -> returns false
        assertFalse(predicateMain.equals(null));

        // different person -> returns false
        assertFalse(predicateMain.equals(predicateDiff));
    }

    @Test
    public void hashCodeTest() {
        CompositePredicate predicate = new CompositePredicate();
        predicate.add(new PredicateStub(-1));
        CompositePredicate predicateDiff = new CompositePredicate();
        predicateDiff.add(new PredicateStub(1));

        Set<Predicate<Person>> predicateSet = new LinkedHashSet<>();
        predicateSet.add(new PredicateStub(-1));

        assertEquals(Objects.hash("CompositeSet", predicateSet), predicate.hashCode());
        assertNotEquals(predicateDiff.hashCode(), predicate.hashCode());
    }

    @Test
    public void test_singletonComposition_shouldReflectSingletonLogic() {
        // base case always passes
        CompositePredicate predicate = new CompositePredicate();
        assertTrue(predicate.test(ALICE));
        assertTrue(predicate.test(BENSON));

        // composition with logical stub passes if ALICE is passed in
        predicate = new CompositePredicate();
        predicate.add(new PredicateStub(0));
        assertTrue(predicate.test(ALICE));
        assertFalse(predicate.test(BENSON));

        // composition with falsy stub always fails
        predicate = new CompositePredicate();
        predicate.add(new PredicateStub(-1));
        assertFalse(predicate.test(ALICE));
        assertFalse(predicate.test(BENSON));

        // composition with truthy stub always passes
        predicate = new CompositePredicate();
        predicate.add(new PredicateStub(1));
        assertTrue(predicate.test(ALICE));
        assertTrue(predicate.test(BENSON));
    }

    @Test
    public void test_multipleComposition_shouldReflectAndLogic() {
        CompositePredicate predicate;

        // composition with truthy stubs always passes
        predicate = new CompositePredicate();
        predicate.add(new PredicateStub(2));
        predicate.add(new PredicateStub(1));
        assertTrue(predicate.test(ALICE));
        assertTrue(predicate.test(BENSON));

        // composition with logical stubs pass for conditions if all other conditions are true
        predicate = new CompositePredicate();
        predicate.add(new PredicateStub(2));
        predicate.add(new PredicateStub(1));
        predicate.add(new PredicateStub(0));
        assertTrue(predicate.test(ALICE));
        assertFalse(predicate.test(BENSON));

        // composition with falsy stubs always fails
        predicate = new CompositePredicate();
        predicate.add(new PredicateStub(-2));
        predicate.add(new PredicateStub(-1));
        assertFalse(predicate.test(ALICE));
        assertFalse(predicate.test(BENSON));

        // composition with any falsy stub always fails
        predicate = new CompositePredicate();
        predicate.add(new PredicateStub(1));
        predicate.add(new PredicateStub(0));
        predicate.add(new PredicateStub(-1));
        assertFalse(predicate.test(ALICE));
        assertFalse(predicate.test(BENSON));
    }

    private static class PredicateStub implements Predicate<Person> {
        // -ve for falsy, 0 for logical, +ve for truthy
        private final int state;

        private PredicateStub(int state) {
            this.state = state;
        }

        @Override
        public boolean test(Person person) {
            if (state < 0) {
                return false;
            }

            if (state > 0) {
                return true;
            }

            return person.equals(ALICE);
        }

        @Override
        public int hashCode() {
            return state;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof PredicateStub)) {
                return false;
            }

            PredicateStub otherStub = (PredicateStub) other;
            return state == otherStub.state;
        }

        @Override
        public String toString() {
            return "Stub state: " + state;
        }
    }
}
