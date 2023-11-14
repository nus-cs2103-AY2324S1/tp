package seedu.spendnsplit.model.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.spendnsplit.testutil.TypicalPersons.ALICE;
import static seedu.spendnsplit.testutil.TypicalPersons.BENSON;
import static seedu.spendnsplit.testutil.TypicalPersons.CARL;
import static seedu.spendnsplit.testutil.TypicalPortions.ALICE_PORTION;
import static seedu.spendnsplit.testutil.TypicalPortions.BENSON_PORTION;
import static seedu.spendnsplit.testutil.TypicalPortions.CARL_PORTION;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.spendnsplit.model.person.Name;
import seedu.spendnsplit.testutil.TransactionBuilder;

public class TransactionContainsPersonNamesPredicateTest {
    @Test
    public void equals() {
        List<Name> firstPredicateNameList = List.of(new Name("first"));
        List<Name> secondPredicateNameList = List.of(new Name("first"), new Name("second"));

        TransactionContainsKeywordsAndPersonNamesPredicate firstPredicate =
            new TransactionContainsKeywordsAndPersonNamesPredicate(List.of(), firstPredicateNameList);
        TransactionContainsKeywordsAndPersonNamesPredicate secondPredicate =
            new TransactionContainsKeywordsAndPersonNamesPredicate(List.of(), secondPredicateNameList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        TransactionContainsKeywordsAndPersonNamesPredicate firstPredicateCopy =
            new TransactionContainsKeywordsAndPersonNamesPredicate(List.of(), firstPredicateNameList);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertNotEquals(1, firstPredicate);

        // null -> returns false
        assertNotEquals(null, firstPredicate);

        // different name list -> returns false
        assertNotEquals(firstPredicate, secondPredicate);

        // different keywords list -> returns false
        assertNotEquals(firstPredicate, new TransactionContainsKeywordsAndPersonNamesPredicate(List.of("a"),
                firstPredicateNameList));
    }

    @Test
    public void test_transactionContainsPersonNames_returnsTrue() {
        // One name
        TransactionContainsKeywordsAndPersonNamesPredicate predicate =
            new TransactionContainsKeywordsAndPersonNamesPredicate(List.of(), List.of(CARL.getName()));
        assertTrue(predicate.test(new TransactionBuilder().withPayeeName(CARL.getName().fullName).build()));
        assertTrue(predicate.test(new TransactionBuilder().withPortions(Set.of(CARL_PORTION)).build()));

        // Multiple names
        predicate = new TransactionContainsKeywordsAndPersonNamesPredicate(List.of(),
                List.of(BENSON.getName(), CARL.getName()));
        assertTrue(predicate.test(new TransactionBuilder().withPayeeName(CARL.getName().fullName).build()));
        assertTrue(predicate.test(new TransactionBuilder()
            .withPayeeName(ALICE.getName().fullName).withPortions(Set.of(BENSON_PORTION, CARL_PORTION)).build()));
        assertTrue(predicate.test(new TransactionBuilder().withPortions(Set.of(BENSON_PORTION)).build()));
        assertTrue(predicate.test(new TransactionBuilder().withPortions(Set.of(ALICE_PORTION, CARL_PORTION)).build()));
    }

    @Test
    public void test_transactionContainsKeywords_returnsTrue() {
        // One keyword
        TransactionContainsKeywordsAndPersonNamesPredicate predicate =
            new TransactionContainsKeywordsAndPersonNamesPredicate(List.of("A"), List.of());
        assertTrue(predicate.test(new TransactionBuilder().withDescription("a").build()));
        assertTrue(predicate.test(new TransactionBuilder().withDescription("b a").build()));

        // Multiple keywords
        predicate =
                new TransactionContainsKeywordsAndPersonNamesPredicate(List.of("A", "B"), List.of());
        assertTrue(predicate.test(new TransactionBuilder().withDescription("b").build()));
        assertTrue(predicate.test(new TransactionBuilder().withDescription("a b").build()));
    }

    @Test
    public void test_transactionDoesNotContainNames_returnsTrue() {
        TransactionContainsKeywordsAndPersonNamesPredicate predicate =
            new TransactionContainsKeywordsAndPersonNamesPredicate(List.of(), List.of());
        assertTrue(predicate.test(new TransactionBuilder().withPayeeName(CARL.getName().fullName).build()));
        assertTrue(predicate.test(new TransactionBuilder().withPortions(Set.of(CARL_PORTION)).build()));

    }

    @Test
    public void test_transactionDoesNotContainPersonNames_returnsFalse() {
        TransactionContainsKeywordsAndPersonNamesPredicate predicate =
                new TransactionContainsKeywordsAndPersonNamesPredicate(List.of(), List.of(new Name("Carol")));
        assertFalse(predicate.test(new TransactionBuilder().withPayeeName(CARL.getName().fullName).build()));
        assertFalse(predicate.test(new TransactionBuilder().withPortions(Set.of(CARL_PORTION)).build()));
    }

    @Test
    public void test_transactionDoesNotContainKeywords_returnsFalse() {
        TransactionContainsKeywordsAndPersonNamesPredicate predicate =
                    new TransactionContainsKeywordsAndPersonNamesPredicate(List.of("A"), List.of());
        assertFalse(predicate.test(new TransactionBuilder().withDescription("B").build()));
    }

    @Test
    public void toStringMethod() {
        List<Name> names = List.of(new Name("name1"), new Name("name2"));
        TransactionContainsKeywordsAndPersonNamesPredicate predicate =
            new TransactionContainsKeywordsAndPersonNamesPredicate(List.of(), names);

        String expected = TransactionContainsKeywordsAndPersonNamesPredicate.class.getCanonicalName()
            + "{keywords=" + List.of() + ", personNames=" + names + "}";
        assertEquals(expected, predicate.toString());
    }
}
