package seedu.address.model.note;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.NoteBuilder;

public class NoteTagContainsKeywordsPredicateTest {

    @Test
    public void testNoteTagContainsKeywordsPredicate() {
        // Create a Note with tags "Tag1" and "Tag2"
        Note note = new NoteBuilder()
                .withContent("Sample content")
                .withTag(new Tag("Tag1"))
                .withTag(new Tag("Tag2"))
                .build();

        // Test with a predicate that matches "Tag1"
        NoteTagContainsKeywordsPredicate predicate = new NoteTagContainsKeywordsPredicate("Tag1");
        assertTrue(predicate.test(note));

        // Test with a predicate that matches "Tag2"
        predicate = new NoteTagContainsKeywordsPredicate("Tag2");
        assertTrue(predicate.test(note));

        // Test with a predicate that doesn't match any tag
        predicate = new NoteTagContainsKeywordsPredicate("Tag3");
        assertFalse(predicate.test(note));
    }

    @Test
    public void equals() {
        NoteTagContainsKeywordsPredicate predicate1 = new NoteTagContainsKeywordsPredicate("Tag1");
        NoteTagContainsKeywordsPredicate predicate2 = new NoteTagContainsKeywordsPredicate("Tag2");

        // Test for equality
        assertTrue(predicate1.equals(predicate1));

        // Test for inequality
        assertFalse(predicate1.equals(predicate2));
        assertFalse(predicate1.equals(null));
    }

    @Test
    public void toStringTest() {
        NoteTagContainsKeywordsPredicate predicate = new NoteTagContainsKeywordsPredicate("Tag1");
        String expectedString = new NoteTagContainsKeywordsPredicate("Tag1").toString();
        assertEquals(expectedString, predicate.toString());
    }
}
