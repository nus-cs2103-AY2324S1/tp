package flashlingo.logic.commands;

import seedu.flashlingo.commons.util.ToStringBuilder;
import seedu.flashlingo.model.Model;

import static java.util.Objects.requireNonNull;
import static seedu.flashlingo.model.Model.PREDICATE_SHOW_ALL_FLASHCARDS;

/**
 * Ends the session of reviewing.
 */
public class EndCommandTest {

    public static final String COMMAND_WORD = "end";

    // For help function
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Ends reviewing session.\n"
        + "Example: " + COMMAND_WORD + " ";

    public static final String MESSAGE_SUCCESS = "Review Session has ended.";
    public static final String MESSAGE_STATE_REPEATED = "Review session hasn't been started!";

    @Override
    public CommandResultTest execute(Model model) {
        requireNonNull(model);
        model.updateFilteredFlashCardList(PREDICATE_SHOW_ALL_FLASHCARDS);
        return new CommandResultTest(String.format(MESSAGE_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddCommandTest)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
          .add("start", "")
          .toString();
    }
}
