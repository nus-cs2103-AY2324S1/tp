package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.model.Model;
import seedu.address.model.Time;
import seedu.address.model.interview.Interview;

/**
 * Encapsulates the list interviews today command. This command
 * will list out the interview time based on the system time of the
 * user's machine.
 *
 * @author Tan Kerway
 */
public class ListInterviewsTodayCommand extends Command {
    public static final String COMMAND_WORD = "list-i-today";

    public static final String MESSAGE_SUCCESS = "Listed all interviews today";
    private static final Logger logger = LogsCenter.getLogger(AddressBookParser.class);
    private static final Predicate<Interview> PREDICATE_SHOW_INTERVIEWS_TODAY =
            interview -> Time.isToday(interview.getInterviewStartTime());


    /**
     * Will pass in the custom predicate specific to the list interviews today
     * command, which is to filter out all interviews that have the same date as
     * today's date.
     *
     * @author Tan Kerway
     * @param model {@code Model} which the command should operate on.
     * @return the CommandResult object instance that stores the information on
     *         the result of executing the command
     */
    @Override
    public CommandResult execute(Model model) {
        logger.fine("Executing listing all interviews today");
        requireNonNull(model);
        model.updateFilteredInterviewList(PREDICATE_SHOW_INTERVIEWS_TODAY);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
