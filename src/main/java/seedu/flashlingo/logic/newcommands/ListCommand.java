package seedu.flashlingo.logic.newcommands;

import static java.util.Objects.requireNonNull;
import static seedu.flashlingo.model.NewModel.PREDICATE_SHOW_ALL_FLASHCARDS;

import seedu.flashlingo.logic.commands.CommandResult;
import seedu.flashlingo.model.NewModel;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends NewCommand {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all flashcards";


    @Override
    public CommandResult execute(NewModel model) {
        requireNonNull(model);
        model.updateFilteredFlashCardList(PREDICATE_SHOW_ALL_FLASHCARDS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
