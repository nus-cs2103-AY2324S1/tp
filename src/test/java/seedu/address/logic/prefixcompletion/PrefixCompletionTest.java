package seedu.address.logic.prefixcompletion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.prefixcompletion.exceptions.PrefixCompletionException;

public class PrefixCompletionTest {

    private PrefixCompletion prefixCompletion = new PrefixCompletion();

    @Test
    public void getNextCompletion_validInput_returnsExpectedCompletion() throws PrefixCompletionException {
        String input = "add ";
        String expectedCompletion = "r/1"; // Replace with actual expected value

        String completion = prefixCompletion.getNextCompletion(input);

        assertEquals(expectedCompletion, completion);
    }

    @Test
    public void getNextCompletion_noSpaceAtEnd_throwsException() {
        String input = "add";
        assertThrows(PrefixCompletionException.class, () -> prefixCompletion.getNextCompletion(input));
    }

    @Test
    public void getNextCompletion_unknownCommand_throwsException() {
        String input = "unknownCommand ";
        assertThrows(PrefixCompletionException.class, () -> prefixCompletion.getNextCompletion(input));
    }
}
