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
 * Merges two people in the address book.
 */
public class MergeCommand extends Command {

    public static final String COMMAND_WORD = "merge";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Merges two contacts.\n"
            + "Parameters: PRIMARY_INDEX (must be a positive integer), SECONDARY_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1  2 ";

    public static final String MESSAGE_MERGE_PERSON_SUCCESS = "Merged persons: \n%1$s \nand \n%2$s \ninto \n%3$s";

    private final Index primaryIndex;
    private final Index secondaryIndex;

    /**
     * Creates a MergeCommand to add the specified {@code Person}
     */
    public MergeCommand(Index primaryIndex, Index secondaryIndex) {
        this.primaryIndex = primaryIndex;
        this.secondaryIndex = secondaryIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (primaryIndex.getZeroBased() >= lastShownList.size()
                || secondaryIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        assert primaryIndex.getOneBased() > 0;
        assert secondaryIndex.getOneBased() > 0;

        Person primaryPerson = lastShownList.get(primaryIndex.getZeroBased());
        Person secondaryPerson = lastShownList.get(secondaryIndex.getZeroBased());
        Person newPerson = primaryPerson.mergePersons(secondaryPerson);

        model.setPerson(primaryPerson, newPerson);
        model.deletePerson(secondaryPerson);
        model.clearFilters();
        return new CommandResult(String.format(MESSAGE_MERGE_PERSON_SUCCESS, Messages.format(primaryPerson),
                Messages.format(secondaryPerson), Messages.format(newPerson)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MergeCommand)) {
            return false;
        }

        MergeCommand otherMergeCommand = (MergeCommand) other;
        return primaryIndex.equals(otherMergeCommand.primaryIndex)
                && secondaryIndex.equals(otherMergeCommand.secondaryIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("primary index", primaryIndex)
                .add("secondary index", secondaryIndex)
                .toString();
    }
}
