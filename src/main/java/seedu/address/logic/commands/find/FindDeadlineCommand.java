package seedu.address.logic.commands.find;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.getMessageDeadlinesListedOverview;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATEJOINED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.TabIndex;
import seedu.address.model.Model;
import seedu.address.model.project.Deadline;

/**
 * Represents a command to filter deadlines within projects based on specified criteria.
 * This command allows users to search for deadlines based on dates and priority levels.
 */
public class FindDeadlineCommand extends Command {
    public static final String COMMAND_WORD = "find-deadline";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filter deadlines within projects.\n"
            + "Parameters: "
            + PREFIX_DATEJOINED + "DATE (Shows deadlines before this date) \n"
            + "[" + PREFIX_PRIORITY + "[HIGH/MEDIUM/LOW] (Shows deadlines based on priority)\n"
            + "Example: " + COMMAND_WORD + " d/28-12-2021 pri/HIGH\n";

    private final Predicate<Deadline> predicate;

    /**
     * Creates a FindDeadlineCommand with the specified predicate for filtering deadlines.
     *
     * @param predicate The predicate used to filter deadlines within projects.
     */
    public FindDeadlineCommand(Predicate<Deadline> predicate) {
        this.predicate = predicate;
    }

    /**
     * Executes the find operation by updating the filtered project deadline list based on the given predicate.
     *
     * @param model The model to execute the command on.
     * @return A CommandResult indicating the results of the find operation.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.updateFilteredProjectDeadlineList(predicate);

        int resultCount =
                model.getFilteredProjectList().stream()
                        .mapToInt(project -> project.getProjectFilteredDeadlines().size()).filter(x -> x > 0).sum();

        String message = getMessageDeadlinesListedOverview(resultCount);

        return new CommandResult(message, TabIndex.Project);
    }

    /**
     * Checks if this FindDeadlineCommand is equal to another object.
     *
     * @param other The object to compare with this FindDeadlineCommand.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof FindDeadlineCommand)) {
            return false;
        }

        FindDeadlineCommand otherFindDeadlineCommand = (FindDeadlineCommand) other;
        return predicate.equals(otherFindDeadlineCommand.predicate);
    }

    /**
     * Generates a string representation of this FindDeadlineCommand.
     *
     * @return A string representation of this object.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
