package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.model.Model;
import seedu.address.model.interview.Interview;

/**
 * Encapsulates a command which, when executed, will sort all
 * the interviews that the user has scheduled so far by ascending
 * order of start time
 *
 * @author Tan Kerway
 */
public class SortTimeCommand extends Command {

    public static final String COMMAND_WORD = "sort-time";

    public static final String MESSAGE_SUCCESS = "Interviews are sorted by start times successfully";

    public final Comparator<Interview> comparator = Comparator.comparing(Interview::getInterviewStartTime);

    /**
     * Sorts the list of interviews based on the custom comparator which compares the start
     * time of two interviews.
     *
     * @author Tan Kerway
     * @param model {@code Model} which the command should operate on.
     * @return the CommandResult instance associated with the execution of the method
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortInterviewList(comparator);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
