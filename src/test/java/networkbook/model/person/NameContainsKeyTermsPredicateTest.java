package networkbook.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import networkbook.testutil.PersonBuilder;

public class NameContainsKeyTermsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeyTermList = Collections.singletonList("first");
        List<String> secondPredicateKeyTermList = Arrays.asList("first", "second");

        NameContainsKeyTermsPredicate firstPredicate = new NameContainsKeyTermsPredicate(firstPredicateKeyTermList);
        NameContainsKeyTermsPredicate secondPredicate = new NameContainsKeyTermsPredicate(secondPredicateKeyTermList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsKeyTermsPredicate firstPredicateCopy = new NameContainsKeyTermsPredicate(firstPredicateKeyTermList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeyTerms_returnsTrue() {
        // One keyword
        NameContainsKeyTermsPredicate predicate = new NameContainsKeyTermsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new NameContainsKeyTermsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new NameContainsKeyTermsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new NameContainsKeyTermsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // One key term
        predicate = new NameContainsKeyTermsPredicate(Collections.singletonList("Ali"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Multiple key terms
        predicate = new NameContainsKeyTermsPredicate(Arrays.asList("lic", "ob"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Only one matching key term
        predicate = new NameContainsKeyTermsPredicate(Arrays.asList("Bo", "arol"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Carol").build()));

        // Mixed-case key terms
        predicate = new NameContainsKeyTermsPredicate(Arrays.asList("aL", "ce", "oB"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
        predicate = new NameContainsKeyTermsPredicate(Arrays.asList("Bo", "aRoL"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Carol").build()));
    }

    @Test
    public void test_nameDoesNotContainKeyTerms_returnsFalse() {
        // Zero keywords
        NameContainsKeyTermsPredicate predicate = new NameContainsKeyTermsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new NameContainsKeyTermsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Keywords match phone and email, but does not match name
        predicate = new NameContainsKeyTermsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhones(List.of("12345"))
                .withEmails(List.of("alice@email.com")).build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keyTerms = List.of("keyterm1", "keyterm2");
        NameContainsKeyTermsPredicate predicate = new NameContainsKeyTermsPredicate(keyTerms);

        String expected = NameContainsKeyTermsPredicate.class.getCanonicalName() + "{key terms=" + keyTerms + "}";
        assertEquals(expected, predicate.toString());
    }
}
