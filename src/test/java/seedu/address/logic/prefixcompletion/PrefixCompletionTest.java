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

    @Test
    public void getNextCompletion_emptyInput_throwsException() {
        String input = "";
        assertThrows(PrefixCompletionException.class, () -> prefixCompletion.getNextCompletion(input));
    }

    @Test
    public void getNextCompletion_somePrefixesPresent_returnsNextCompletion() throws PrefixCompletionException {
        String input = "add r/1 ";
        String expectedCompletion = "d/2023-01-01 08:00 to 2023-01-02 12:00";

        String completion = prefixCompletion.getNextCompletion(input);

        assertEquals(expectedCompletion, completion);
    }

    @Test
    public void getNextCompletion_allPrefixesPresent_throwsException() {
        String input = "add r/1 d/2023-01-01 08:00 to 2023-01-02 12:00 n/John Doe p/98765432 e/johnd@example.com "
                + "t/friends t/owesMoney";

        assertThrows(PrefixCompletionException.class, () -> prefixCompletion.getNextCompletion(input));
    }

}
