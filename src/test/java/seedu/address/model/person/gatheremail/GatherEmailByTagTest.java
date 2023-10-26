package seedu.address.model.person.gatheremail;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

public class GatherEmailByTagTest {

    @Test
    public void gatherEmails() {
        GatherEmailByTag tagPrompt = new GatherEmailByTag(VALID_TAG_HUSBAND);
        assertEquals(new String(), tagPrompt.gatherEmails(ALICE));
        assertEquals(BOB.getEmail().toString(), tagPrompt.gatherEmails(BOB));
    }

    @Test
    void testEquals() {
        GatherEmailByTag first = new GatherEmailByTag("first");
        GatherEmailByTag second = new GatherEmailByTag("second");
        GatherEmailByTag firstOther = new GatherEmailByTag("first");

        // same object -> returns true
        assertTrue(first.equals(first));

        // different types -> returns false
        assertFalse(first.equals(1));

        // null -> returns false
        assertFalse(first.equals(null));

        // different object -> returns false
        assertFalse(first.equals(second));

        // different object -> returns true
        assertTrue(first.equals(firstOther));
    }

    @Test
    public void testToString() {
        String prompt = "prompt";
        String expected = "Tag: " + prompt;
        GatherEmailByTag tagPrompt = new GatherEmailByTag(prompt);
        assertEquals(tagPrompt.toString(), expected);
    }
}
