package seedu.address.model.card;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalCards.CS1101S;
import static seedu.address.testutil.TypicalCards.CS1231S;
import static seedu.address.testutil.TypicalCards.CS2100;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.CardBuilder;

public class CardTest {
    @Test
    public void isSameCard() {
        // same object -> returns true
        assertTrue(CS2100.isSameCard(CS2100));

        // null -> returns false
        assertFalse(CS2100.isSameCard(null));

        // same question, different answer -> returns true
        // to update with CommandTestUtil
        Card editedCS2100 = new CardBuilder(CS2100).withAnswer("000000").build();
        assertTrue(CS2100.isSameCard(editedCS2100));

        // different question, same answer -> returns false
        editedCS2100 = new CardBuilder(CS2100).withQuestion("What is the opcode for R-format instructions").build();
        assertFalse(CS2100.isSameCard(editedCS2100));

        // name differs in case, all other attributes same -> returns false
        Card editedCS1231S = new CardBuilder(CS1231S).withQuestion(
                CS1231S.getQuestion().toString().toLowerCase()).build();
        assertFalse(CS1231S.isSameCard(editedCS1231S));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Card cs1101sCopy = new CardBuilder(CS1101S).build();
        assertTrue(CS1101S.equals(cs1101sCopy));

        // same object -> returns true
        assertTrue(CS1101S.equals(CS1101S));

        // null -> returns false
        assertFalse(CS1101S.equals(null));

        // different type -> returns false
        assertFalse(CS1101S.equals(5));

        // different card -> returns false
        assertFalse(CS1101S.equals(CS2100));

        // different question -> returns false
        Card editedAlice = new CardBuilder(CS1101S).withQuestion(CS2100.getQuestion().toString()).build();
        assertFalse(CS1101S.equals(editedAlice));

        // tags differs in case, all other attributes same -> return false
        Card editedCS1231S = new CardBuilder(CS1231S)
                .withTags(List.of(new Tag("CS1231S"))).build();
        Card editedLowercaseCS1231S = new CardBuilder(CS1231S)
                .withTags(List.of(new Tag("cs1231s"))).build();
        assertFalse(editedLowercaseCS1231S.equals(editedCS1231S));

        // different number of tags -> return false
        Card editedCS1231SWithMoreTags = new CardBuilder(CS1231S)
                .withTags(List.of(new Tag("cs1231s"), new Tag("CS1231S"))).build();
        assertFalse(editedLowercaseCS1231S.equals(editedCS1231S));
    }

    @Test
    public void toStringMethod() {
        String expected = Card.class.getCanonicalName() + "{question=" + CS1101S.getQuestion()
                + ", answer=" + CS1101S.getAnswer() + ", tags=[]" + "}";
        assertEquals(expected, CS1101S.toString());
    }

    @Test
    public void getSolveCountMethod() {
        Card cs1231sCopy = new CardBuilder(CS1231S).build();

        System.out.println(cs1231sCopy.getSolveCount());
        // solve count on creation = 0;
        assertTrue(cs1231sCopy.getSolveCount().equals(0));
    }

    @Test
    public void incrementSolveCountMethod() {
        Card cs1101sCopy = new CardBuilder(CS1101S).build();

        // solve count on creation = 0;
        cs1101sCopy.incrementSolveCount();
        assertTrue(cs1101sCopy.getSolveCount().equals(1));
    }
}
