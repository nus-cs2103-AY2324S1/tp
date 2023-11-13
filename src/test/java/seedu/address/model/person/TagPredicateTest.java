package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.predicates.TagPredicate;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class TagPredicateTest {

    @Test
    public void equals() {
        List<Tag> firstPredicateTagList = Collections.singletonList(new Tag("first"));

        TagPredicate firstPredicate = new TagPredicate(firstPredicateTagList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        TagPredicate firstPredicateCopy = new TagPredicate(firstPredicateTagList);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertNotEquals(firstPredicate, 1);

        // null -> returns false
        assertNotEquals(null, firstPredicate);
    }

    @Test
    public void test_personHasTag_returnsTrue() {
        List<Tag> firstPredicateTagList = Collections.singletonList(new Tag("first"));
        TagPredicate firstPredicate = new TagPredicate(firstPredicateTagList);
        assertTrue(firstPredicate.test(new PersonBuilder().withTags("first").build()));
    }

    @Test
    public void test_personHasNoTag_returnsFalse() {
        List<Tag> firstPredicateTagList = Collections.singletonList(new Tag("first"));
        TagPredicate firstPredicate = new TagPredicate(firstPredicateTagList);
        assertFalse(firstPredicate.test(new PersonBuilder().withTags("second").build()));
    }

    @Test
    public void toStringMethod() {
        List<Tag> firstPredicateTagList = Arrays.asList(new Tag("first"), new Tag("second"));
        TagPredicate firstPredicate = new TagPredicate(firstPredicateTagList);
        assertEquals("tag: [[first], [second]]", firstPredicate.toString());
    }

    @Test
    public void toFilterStringMethod() {
        List<Tag> firstPredicateTagList = Arrays.asList(new Tag("first"), new Tag("second"));
        TagPredicate firstPredicate = new TagPredicate(firstPredicateTagList);
        assertEquals("tag: [first, second]", firstPredicate.toFilterString());
    }
}
