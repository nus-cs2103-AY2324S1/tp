package seedu.address.logic.commands.finance;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.FinancesBook;
import seedu.address.model.Model;


/**
 * Clears the finance book.
 */
public class ClearFinancesCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Finance book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setFinancesBook(new FinancesBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
