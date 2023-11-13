package seedu.letsgethired.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_CYCLE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.letsgethired.commons.util.ToStringBuilder;
import seedu.letsgethired.logic.commands.exceptions.CommandException;
import seedu.letsgethired.model.Model;
import seedu.letsgethired.model.application.InternApplicationComparator;

/**
 * Adds an intern application to the intern tracker.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the applications in the intern tracker.\n"
            + "Parameters: "
            + "[" + PREFIX_COMPANY + "ORDER] "
            + "[" + PREFIX_ROLE + "ORDER] "
            + "[" + PREFIX_CYCLE + "ORDER] "
            + "[" + PREFIX_STATUS + "ORDER] "
            + "[" + PREFIX_DEADLINE + "ORDER] "
            + "\nExample: " + COMMAND_WORD + " "
            + PREFIX_COMPANY + "a";

    public static final String MESSAGE_SUCCESS = "Intern applications sorted";
    private final InternApplicationComparator comparator;

    /**
     * Creates an AddCommand to add the specified {@code InternApplication}
     */
    public SortCommand(InternApplicationComparator comparator) {
        requireNonNull(comparator);
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.updateFilteredSortedInternApplicationList(comparator);

        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortCommand)) {
            return false;
        }

        SortCommand otherSortCommand = (SortCommand) other;
        // Note: equals method checks for referential equality
        return comparator.equals(otherSortCommand.comparator);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("comparator", comparator)
                .toString();
    }
}
