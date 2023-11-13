package seedu.address.model.card;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AnswerTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Answer(null));
    }

    @Test
    public void constructor_invalidAnswer_throwsIllegalArgumentException() {
        String invalidAnswer = "";
        assertThrows(IllegalArgumentException.class, () -> new Answer(invalidAnswer));
    }

    @Test
    public void isValidAnswer() {
        // null answer
        assertThrows(NullPointerException.class, () -> Answer.isValidAnswer(null));

        // invalid answer
        assertFalse(Answer.isValidAnswer("")); // empty string

        // valid answer
        assertTrue(Answer.isValidAnswer("Craft the truth table for A and B")); // alphabets only
        assertTrue(Answer.isValidAnswer("12345")); // numbers only
        assertTrue(Answer.isValidAnswer(
                "What are the first 6 bits for R-format instructions?")); // long answers
    }

    @Test
    public void equals() {
        Answer answer = new Answer("Valid Answer");

        // same values -> returns true
        assertTrue(answer.equals(new Answer("Valid Answer")));

        // same object -> returns true
        assertTrue(answer.equals(answer));

        // null -> returns false
        assertFalse(answer.equals(null));

        // different types -> returns false
        assertFalse(answer.equals(5.0f));

        // different values -> returns false
        assertFalse(answer.equals(new Answer("Other Valid Answer")));
    }

    @Test
    public void hashcode() {
        Answer answer = new Answer("Valid Answer");

        // same values -> returns true
        assertEquals(answer.hashCode(), new Answer("Valid Answer").hashCode());

        // same object -> returns true
        assertEquals(answer.hashCode(), answer.hashCode());

        // different values -> returns false
        assertNotEquals(answer.hashCode(), new Answer("Other Valid Answer").hashCode());
    }
}
