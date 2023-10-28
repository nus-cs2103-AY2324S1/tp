package flashlingo.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.flashlingo.logic.commands.StatsCommand;
import seedu.flashlingo.model.Model;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Indicates user wants to display the learning statistics
 */
public class StatsCommandTest {

    @Test
    public void equals() {
        StatsCommand statsFirstCommand = new StatsCommand();
        StatsCommand statsSecondCommand = new StatsCommand();


        // same object -> returns true
        assertTrue(statsFirstCommand.equals(statsFirstCommand));

        // same values -> returns true
        StatsCommand statsFirstCommandCopy = new StatsCommand();
        assertTrue(statsFirstCommand.equals(statsFirstCommandCopy));

        // different types -> returns false
        assertFalse(statsFirstCommand.equals(1));

        // null -> returns false
        assertFalse(statsFirstCommand.equals(null));

//        // different FlashCard -> returns false
//        assertFalse(statsFirstCommand.equals(statsSecondCommand));
    }
//    public static final String COMMAND_WORD = "stats";
//
//    // For stats function
//    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Show user statistics\n"
//            + "Example: " + COMMAND_WORD + " ";
//
//
//    public static final String MESSAGE_SUCCESS = "Great work fellow learner! \nTotal number of flashcards: %d\n";
//
//
//    /**
//     * Executes the functioning for this command
//     * @param model {@code Model} which the command should operate on.
//     * @return CommandResultTest after executing this command
//     */
//    @Override
//    public CommandResultTest execute(Model model) {
//        requireNonNull(model);
//        return new CommandResultTest(String.format(MESSAGE_SUCCESS, model.getNumberOfFlashCards(),
//                model.getNumberOfRememberedWords()));
//    }
//
//    /**
//     * Checks whether this is equal to the passed Object
//     * @param other The passed object to check for equality against
//     * @return True or False depending on whether this and other are equal
//     */
//    @Override
//    public boolean equals(Object other) {
//        if (other == this) {
//            return true;
//        }
//
//        // instanceof handles nulls
//        if (!(other instanceof StatsCommandTest)) {
//            return false;
//        }
//
//        return true;
//    }

}
