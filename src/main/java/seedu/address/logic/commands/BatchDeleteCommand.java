package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELETE_MONTH;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.month.DeleteMonth;
import seedu.address.model.person.Person;
import seedu.address.model.person.PolicyExpiryInDeleteMonthPredicate;

import java.util.function.Predicate;

/**
 * Deletes all people who policy expiry date is in the specific month.
 */
public class BatchDeleteCommand extends Command {

    public static final String COMMAND_WORD = "batchDelete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": batch delete people whose policy expiry date "
            + "is in the corresponding month and year. "
            + "Parameters: "
            + PREFIX_DELETE_MONTH + "MM-yyyy\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DELETE_MONTH + "11-2022";

    public static final String MESSAGE_DELETE_PEOPLE_SUCCESS = "Batch delete people in: %1$s";

    private final DeleteMonth month;

    /**
     * Creates an BatchDeleteCommand to batch delete the specified {@code Person}
     */
    public BatchDeleteCommand(DeleteMonth deleteMonth) {
        requireNonNull(deleteMonth);
        month = deleteMonth;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Predicate<Person> p = new PolicyExpiryInDeleteMonthPredicate(month);
        model.batchDeleteWithPredicate(p);

        return new CommandResult(String.format(MESSAGE_DELETE_PEOPLE_SUCCESS, month.toString()));
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
        return month.equals(otherBatchDeleteCommand.month);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toDelete", month)
                .toString();
    }
}
