package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Deck;
import seedu.address.model.Model;
import seedu.address.model.card.Card;
import seedu.address.model.card.Hint;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditCardDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_QUESTION_CS2100 = "What is 5 in 2's complement";
    public static final String VALID_QUESTION_CS2100_R = "R-Format instruction opcode";
    public static final String VALID_QUESTION_CS1101S = "What is the language used for this mod?";
    public static final String VALID_ANSWER_CS2100 = "0101";
    public static final String VALID_ANSWER_CS1101S = "Source";
    public static final String VALID_ANSWER_CS2100_R = "0";

    public static final List<Tag> VALID_TAG_CS2100 = new ArrayList<>(Collections.singleton(new Tag("CS2100")));
    public static final List<Tag> VALID_TAG_CS1101S = new ArrayList<>(Collections.singleton(new Tag("CS1101S")));

    public static final String VALID_SINGULAR_TAG_CS2100 = "CS2100";

    public static final String VALID_HINT_CS2100 = "Base 2";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditCardDescriptor DESC_CS2100;
    public static final EditCommand.EditCardDescriptor DESC_CS1101S;

    static {
        DESC_CS2100 = new EditCardDescriptorBuilder().withQuestion(VALID_QUESTION_CS2100)
                .withAnswer(VALID_ANSWER_CS2100).withTags(VALID_TAG_CS2100).withHint(VALID_HINT_CS2100).build();
        DESC_CS1101S = new EditCardDescriptorBuilder().withQuestion(VALID_QUESTION_CS1101S)
                .withAnswer(VALID_ANSWER_CS1101S).withTags(VALID_TAG_CS1101S).withHint(new Hint.EmptyHint()).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered Card list and selected Card in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Deck expectedDeck = new Deck(actualModel.getDeck());
        List<Card> expectedFilteredList = new ArrayList<>(actualModel.getFilteredCardList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedDeck, actualModel.getDeck());
        assertEquals(expectedFilteredList, actualModel.getFilteredCardList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showCardAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredCardList().size());

        Card card = model.getFilteredCardList().get(targetIndex.getZeroBased());
        final String[] splitName = card.getQuestion().question.split("\\s+");

        assertEquals(1, model.getFilteredCardList().size());
    }


}
