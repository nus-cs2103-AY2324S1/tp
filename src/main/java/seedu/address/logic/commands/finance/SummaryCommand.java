package seedu.address.logic.commands.finance;

import javafx.collections.ObservableList;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.finance.ClientNameExactMatchPredicate;
import seedu.address.model.finance.Finance;
import seedu.address.model.finance.FinanceSummary;
import seedu.address.model.person.Person;


/**
 * Provides a summary of the Finances for the given Client to the user.
 */
public class SummaryCommand extends Command {

    public static final String COMMAND_WORD = "summary";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Provides a summary of a client's "
            + "commissions and expenses\n"
            + "Parameters: CLIENT\n"
            + "Example: " + COMMAND_WORD + " John Doe";

    private final ClientNameExactMatchPredicate namePredicate;

    public SummaryCommand(ClientNameExactMatchPredicate namePredicate) {
        this.namePredicate = namePredicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        String clientName = namePredicate.getClientName();
        Person dummyPerson = Person.makeDummyWithName(clientName);
        if (!model.isValidClient(dummyPerson)) {
            throw new CommandException(Messages.MESSAGE_CLIENT_DOES_NOT_EXIST);
        }
        model.updateFilteredFinanceList(namePredicate);
        ObservableList<Finance> filteredFinances = model.getFilteredFinanceList();
        return new CommandResult(FinanceSummary.generateSummary(filteredFinances, clientName));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SummaryCommand)) {
            return false;
        }

        SummaryCommand otherSummaryCommandCommand = (SummaryCommand) other;
        return namePredicate.equals(otherSummaryCommandCommand.namePredicate);
    }

}
