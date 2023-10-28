package seedu.address.logic.commands.personcommands;

import org.junit.jupiter.api.Test;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.IllnessContainsKeywordsPredicate;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

public class FindIllnessCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        IllnessContainsKeywordsPredicate firstPredicate =
                new IllnessContainsKeywordsPredicate(Collections.singletonList("fever"));
        IllnessContainsKeywordsPredicate secondPredicate =
                new IllnessContainsKeywordsPredicate(Collections.singletonList("headache"));

        FindIllnessCommand findIllnessFirstCommand = new FindIllnessCommand(firstPredicate);
        FindIllnessCommand findIllnessSecondCommand = new FindIllnessCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findIllnessFirstCommand.equals(findIllnessFirstCommand));

        // same values -> returns true
        FindIllnessCommand findFirstCommandCopy = new FindIllnessCommand(firstPredicate);
        assertTrue(findIllnessFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findIllnessFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findIllnessFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findIllnessFirstCommand.equals(findIllnessSecondCommand));
    }
}
