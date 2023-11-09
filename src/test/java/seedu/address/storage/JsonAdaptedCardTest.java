package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCards.CS1101S;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.card.Answer;
import seedu.address.model.card.Card;
import seedu.address.model.card.PracticeDate;
import seedu.address.model.card.Question;



public class JsonAdaptedCardTest {

    private static final String VALID_QUESTION = CS1101S.getQuestion().toString();
    private static final String VALID_ANSWER = CS1101S.getAnswer().toString();
    private static final String VALID_DIFFICULTY = CS1101S.getDifficulty();
    private static final String VALID_SOLVECOUNT = CS1101S.getSolveCount().toString();
    private static final String VALID_NEXT_PRACTISE_DATE = CS1101S.getNextPracticeDate().toString();
    private static final String VALID_LAST_PRACTISE_DATE = CS1101S.getLastPracticeDate().toString();
    private static final String VALID_HINT = CS1101S.getHint().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = CS1101S.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validCardDetails_returnsCard() throws Exception {
        JsonAdaptedCard person = new JsonAdaptedCard(CS1101S);
        assertEquals(CS1101S, person.toModelType());
    }

    @Test
    public void toModelType_emptyQuestion_throwsIllegalValueException() {
        JsonAdaptedCard card =
                new JsonAdaptedCard("", VALID_ANSWER, VALID_DIFFICULTY, VALID_TAGS, VALID_SOLVECOUNT,
                        VALID_NEXT_PRACTISE_DATE, VALID_LAST_PRACTISE_DATE, VALID_HINT);
        String expectedMessage = Question.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, card::toModelType);
    }



    @Test
    public void toModelType_emptyAnswer_throwsIllegalValueException() {
        JsonAdaptedCard card =
                new JsonAdaptedCard(VALID_QUESTION, "", VALID_DIFFICULTY, VALID_TAGS, VALID_SOLVECOUNT,
                        VALID_NEXT_PRACTISE_DATE, VALID_LAST_PRACTISE_DATE, VALID_HINT);
        String expectedMessage = Answer.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, card::toModelType);
    }

    @Test
    public void toModelType_invalidDifficulty_throwsIllegalValueException() {
        JsonAdaptedCard card =
                new JsonAdaptedCard(VALID_QUESTION, VALID_ANSWER, "mid", VALID_TAGS, VALID_SOLVECOUNT,
                        VALID_NEXT_PRACTISE_DATE, VALID_LAST_PRACTISE_DATE, VALID_HINT);
        String expectedMessage = "No enum constant seedu.address.model.card.Difficulty.MID";
        assertThrows(IllegalArgumentException.class, expectedMessage, card::toModelType);
    }


    @Test
    public void toModelType_invalidNextDate_throwsIllegalValueException() {
        JsonAdaptedCard card =
                new JsonAdaptedCard(VALID_QUESTION, VALID_ANSWER, VALID_DIFFICULTY, VALID_TAGS, VALID_SOLVECOUNT,
                        "1111", VALID_LAST_PRACTISE_DATE, VALID_HINT);
        String expectedMessage = PracticeDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, card::toModelType);
    }

    @Test
    public void toModelType_valid_success() {
        JsonAdaptedCard card =
                new JsonAdaptedCard(VALID_QUESTION, VALID_ANSWER, VALID_DIFFICULTY, VALID_TAGS, VALID_SOLVECOUNT,
                        VALID_NEXT_PRACTISE_DATE, VALID_LAST_PRACTISE_DATE, VALID_HINT);

        // Explicitly assert that no exception is thrown
        assertDoesNotThrow(() -> {
            Card modelCard = card.toModelType();
            // Perform state verification if needed
            assertEquals(VALID_QUESTION, modelCard.getQuestion().toString());
            assertEquals(VALID_ANSWER, modelCard.getAnswer().toString());
            assertEquals(VALID_QUESTION, modelCard.getQuestion().toString());
            assertEquals(VALID_DIFFICULTY, modelCard.getDifficulty());
            assertEquals(VALID_NEXT_PRACTISE_DATE, modelCard.getNextPracticeDate().toString());
            assertEquals(VALID_LAST_PRACTISE_DATE, modelCard.getLastPracticeDate().toString());
            assertEquals(VALID_HINT, modelCard.getHint().toString());
        });
    }


}
