package seedu.address.logic.prefixcompletion;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.prefixcompletion.exceptions.PrefixCompletionException;

public class EditPrefixFinderTest {
    private EditPrefixFinder editPrefixFinder = new EditPrefixFinder();

    @Test
    public void getPrefix_allPrefixesUsed_throwsPrefixCompletionException() {
        String currentInput = "edit 1 d/ n/ p/ e/ r/";
        assertThrows(PrefixCompletionException.class, () -> editPrefixFinder.getPrefix(currentInput));
    }

    @Test
    public void getPrefix_invalidIndexFormat_throwsPrefixCompletionException() {
        String currentInput = "edit invalidIndex n/John";
        assertThrows(PrefixCompletionException.class, () -> editPrefixFinder.getPrefix(currentInput));
    }

    @Test
    public void getPrefix_indexOutOfBound_throwsPrefixCompletionException() {
        String currentInput = "edit -1 n/John";
        assertThrows(PrefixCompletionException.class, () -> editPrefixFinder.getPrefix(currentInput));
    }
}
