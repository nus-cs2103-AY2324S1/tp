package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELETE_MONTH;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.month.DeleteMonth;
import seedu.address.model.person.Person;

import java.util.List;

/**
 * Deletes all people who policy expiry date is in the specific month.
 */
public class BatchDeleteCommand extends Command {

    public static final String COMMAND_WORD = "batchDelete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": batch delete people whose policy expiry date "
            + "is in the corresponding month and year. "
            + "Parameters: "
            + PREFIX_DELETE_MONTH + "MM-YYYY\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DELETE_MONTH + "11-2022";

    public static final String MESSAGE_DELETE_PEOPLE_SUCCESS = "People deleted: %1$s";

    private final DeleteMonth toDelete;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public BatchDeleteCommand(DeleteMonth deleteMonth) {
        requireNonNull(deleteMonth);
        toDelete = deleteMonth;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_NOT_FOUND_INDEX);
        }

        Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deletePerson(personToDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_PEOPLE_SUCCESS, "hello"));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BatchDeleteCommand)) {
            return false;
        }

        BatchDeleteCommand otherBatchDeleteCommand = (BatchDeleteCommand) other;
        return toDelete.equals(otherBatchDeleteCommand.toDelete);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toDelete", toDelete)
                .toString();
    }
}
