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

public class PersonContainsTagsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateTagList = Collections.singletonList("first");
        List<String> secondPredicateTagList = Arrays.asList("first", "second");

        PersonContainsTagsPredicate firstPredicate =
                new PersonContainsTagsPredicate(firstPredicateTagList);
        PersonContainsTagsPredicate secondPredicate =
                new PersonContainsTagsPredicate(secondPredicateTagList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        PersonContainsTagsPredicate firstPredicateCopy =
                new PersonContainsTagsPredicate(firstPredicateTagList);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertNotEquals(1, firstPredicate);
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertNotEquals(null, firstPredicate);

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
        PersonContainsTagsPredicate predicate =
                new PersonContainsTagsPredicate(Collections.singletonList("Charlie"));
        assertTrue(predicate.test(testPerson));


        // Multiple tags
        predicate = new PersonContainsTagsPredicate(Arrays.asList("Charlie", "Derrick"));
        assertTrue(predicate.test(testPerson));


        // Only one matching tag
        predicate = new PersonContainsTagsPredicate(Arrays.asList("Charlie", "Invalid"));
        assertTrue(predicate.test(testPerson));


        // Mixed-case tags
        predicate = new PersonContainsTagsPredicate(Arrays.asList("cHarlIE", "DeRRiCk"));
        assertTrue(predicate.test(testPerson));
    }

    @Test
    public void test_personDoesNotContainTags_returnsFalse() {
        // Zero tags
        PersonContainsTagsPredicate predicate = new PersonContainsTagsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withTags("Charlie").build()));

        // Non-matching tag
        predicate = new PersonContainsTagsPredicate(Collections.singletonList("Invalid"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withTags("Charlie").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> tags = List.of("tag1", "tag2");
        PersonContainsTagsPredicate predicate = new PersonContainsTagsPredicate(tags);

        String expected = new ToStringBuilder(predicate).add("keywords", tags).toString();
        assertEquals(expected, predicate.toString());
    }
}
