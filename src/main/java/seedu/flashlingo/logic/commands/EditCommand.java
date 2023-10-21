package seedu.flashlingo.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.flashlingo.logic.parser.CliSyntax.PREFIX_ORIGINAL_WORD;
import static seedu.flashlingo.logic.parser.CliSyntax.PREFIX_TRANSLATED_WORD;
import static seedu.flashlingo.model.Model.PREDICATE_SHOW_ALL_FLASHCARDS;

import java.util.List;

import seedu.flashlingo.commons.core.index.Index;
import seedu.flashlingo.commons.util.ToStringBuilder;
import seedu.flashlingo.logic.Messages;
import seedu.flashlingo.logic.commands.exceptions.CommandException;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.flashcard.FlashCard;

/**
 * Edits the details of an existing flashcard in Flashlingo.
 */
public class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";
    public static final String MESSAGE_EDIT_FLASHCARD_SUCCESS = "Edited Flashcard: %1$s";
    public static final String MESSAGE_DUPLICATE_FLASHCARD = "This flashcard already exists in Flashlingo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the flashcard identified "
          + "by the index number used in the displayed flashcard list. "
          + "Existing values will be overwritten by the input values.\n"
          + "Parameters: INDEX (must be a positive integer) "
          + "[" + PREFIX_ORIGINAL_WORD + "ORIGINAL WORD] "
          + "[" + PREFIX_TRANSLATED_WORD + "TRANSLATION] \n"
          + "Example: " + COMMAND_WORD + " 1 "
          + PREFIX_ORIGINAL_WORD + "bye "
          + PREFIX_TRANSLATED_WORD + "再见";

    private final Index index;
    private final FlashCard replacedFlashCard;
    /**
     * @param index of the flashcard in the list to edit
     * @param replacedFlashCard details to edit the flashcard with
     */
    public EditCommand(Index index, FlashCard replacedFlashCard) {
        requireNonNull(index);
        requireNonNull(replacedFlashCard);

        this.index = index;
        this.replacedFlashCard = replacedFlashCard;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<FlashCard> lastShownList = model.getFilteredFlashCardList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
        }

        FlashCard flashCardToEdit = lastShownList.get(index.getZeroBased());
        FlashCard editedFlashCard = this.replacedFlashCard;

        if (!flashCardToEdit.isSameFlashCard(editedFlashCard) && model.hasFlashCard(editedFlashCard)) {
            throw new CommandException(Messages.MESSAGE_DUPLICATE_FLASHCARD);
        }
        System.out.println("here");
        model.setFlashCard(flashCardToEdit, editedFlashCard);
        model.updateFilteredFlashCardList(PREDICATE_SHOW_ALL_FLASHCARDS);
        return new CommandResult(String.format(MESSAGE_EDIT_FLASHCARD_SUCCESS, Messages.format(editedFlashCard)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        EditCommand otherEditCommand = (EditCommand) other;
        return index.equals(otherEditCommand.index)
            && replacedFlashCard.equals(otherEditCommand.replacedFlashCard);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
          .add("index", index)
          .add("replacedFlashCard", replacedFlashCard)
          .toString();
    }
}
