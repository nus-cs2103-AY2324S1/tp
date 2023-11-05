package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number(s) used in the displayed person list.\n"
            + "Parameters: INDEX... (must be a positive integer and within range of displayed person list)\n"
            + "INDEX... must be whitespace separated and must not contain duplicates\n"
            + "Example: " + COMMAND_WORD + " "
            + "1 3 4";
    public static final String MESSAGE_DELETE_PERSON_SUCCESS_HEADER = "Deleted %d Person(s):";
    private final List<Index> targetIndexes;

    /**
     * @param targetIndexes of the persons in the list to delete
     */
    public DeleteCommand(List<Index> targetIndexes) {
        this.targetIndexes = targetIndexes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        for (Index targetIndex: targetIndexes) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
        }
        StringBuilder resultMessage =
                new StringBuilder(String.format(MESSAGE_DELETE_PERSON_SUCCESS_HEADER, targetIndexes.size()));
        for (int i = 0; i < targetIndexes.size(); i++) {
            Index targetIndex = targetIndexes.get(i);
            Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
            resultMessage.append(String.format("\n%1$d. %2$s", i + 1,
                            Messages.formatShortForm(personToDelete)));
        }
        for (int i = targetIndexes.size() - 1; i >= 0; i--) {
            Index targetIndex = targetIndexes.get(i);
            Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
            model.deletePerson(personToDelete);
        }
        model.commit();
        return new CommandResult(resultMessage.toString());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;
        return targetIndexes.equals(otherDeleteCommand.targetIndexes);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndexes", targetIndexes)
                .toString();
    }
}
