package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.testutil.PersonBuilder;

public class PersonContainsAllTagsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateTagList = Collections.singletonList("first");
        List<String> secondPredicateTagList = Arrays.asList("first", "second");

        PersonContainsAllTagsPredicate firstPredicate =
                new PersonContainsAllTagsPredicate(firstPredicateTagList);
        PersonContainsAllTagsPredicate secondPredicate =
                new PersonContainsAllTagsPredicate(secondPredicateTagList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        PersonContainsAllTagsPredicate firstPredicateCopy =
                new PersonContainsAllTagsPredicate(firstPredicateTagList);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertNotEquals(firstPredicate, 1);

        // null -> returns false
        assertNotEquals(firstPredicate, null);

        // different values -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_personContainsTags_returnsTrue() {
        Person testPerson = new PersonBuilder()
                .withName("Alice Bob")
                .withTags("Charlie", "Derrick")
                .build();

        // One tag
        PersonContainsAllTagsPredicate predicate =
                new PersonContainsAllTagsPredicate(Collections.singletonList("Charlie"));
        assertTrue(predicate.test(testPerson));

        // Multiple tags
        predicate = new PersonContainsAllTagsPredicate(Arrays.asList("Charlie", "Derrick"));
        assertTrue(predicate.test(testPerson));

        // Only one matching tag
        predicate = new PersonContainsAllTagsPredicate(Arrays.asList("Charlie", "Invalid"));
        assertFalse(predicate.test(testPerson));

        // Mixed-case tags
        predicate = new PersonContainsAllTagsPredicate(Arrays.asList("cHarlIE", "DeRRiCk"));
        assertTrue(predicate.test(testPerson));

        // Excess tags
        predicate = new PersonContainsAllTagsPredicate(Arrays.asList("Charlie", "Derrick", "Excess"));
        assertFalse(predicate.test(testPerson));
    }

    @Test
    public void test_personDoesNotContainTags_returnsFalse() {
        // Zero tags
        PersonContainsAllTagsPredicate predicate =
                new PersonContainsAllTagsPredicate(Collections.singletonList("Valid"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withTags().build()));

        // Non-matching tag
        predicate = new PersonContainsAllTagsPredicate(Collections.singletonList("Invalid"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withTags("Charlie").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> tags = List.of("tag1", "tag2");
        PersonContainsAllTagsPredicate predicate = new PersonContainsAllTagsPredicate(tags);

        String expected = new ToStringBuilder(predicate).add("keywords", tags).toString();
        assertEquals(expected, predicate.toString());
    }
}
