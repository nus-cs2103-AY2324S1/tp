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
 * Views detailed information of person identified using it's displayed index from the address book.
 */
public class ViewContactCommand extends Command {

    public static final String COMMAND_WORD = "viewc";
    public static final int EXPECTED_INDEXES = 1;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Views detailed information of the person identified "
            + "by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final Index targetIndex;

    /**
     * @param index of the person in the filtered person list to view detailed information
     */
    public ViewContactCommand(Index index) {
        requireNonNull(index);
        this.targetIndex = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToView = lastShownList.get(targetIndex.getZeroBased());
        model.setViewedPersonIndex(targetIndex);
        return new CommandResult(String.format(Messages.MESSAGE_PERSON_VIEWED_OVERVIEW, personToView.getName()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instance handles nulls
        if (!(other instanceof ViewContactCommand)) {
            return false;
        }

        ViewContactCommand otherViewContactCommand = (ViewContactCommand) other;
        return targetIndex.equals(otherViewContactCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
