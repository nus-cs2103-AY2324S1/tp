package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Sorts students in the address book.
 */
public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Export chart. "
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Chart is exported";

    /**
     * Creates an SortCommand to sort the students {@code Student}
     */
    public ExportCommand() {

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        return new CommandResult(String.format(MESSAGE_SUCCESS, model.getFilteredPersonList()));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }
}
