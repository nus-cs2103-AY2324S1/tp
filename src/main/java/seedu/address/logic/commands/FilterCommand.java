package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICATION_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.InternshipModel;
import seedu.address.model.internship.Internship;

/**
 * Filters for relevant internships by category.
 */
public class FilterCommand extends InternshipCommand {
    public static final String COMMAND_WORD = "filter";
    public static final String DEFAULT_KEYWORD = "default";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters the internship list.\n"
            + "Parameters 1: PREFIX/KEYWORDS\n"
            + "Available categories are:\n"
            + PREFIX_COMPANY_NAME + " - Company Name\n"
            + PREFIX_ROLE + " - Role\n"
            + PREFIX_APPLICATION_STATUS + " - Application Status\n"
            + "Parameters 2: PREFIX/X-Y\n"
            + "Available categories are:\n"
            + PREFIX_DEADLINE + " - Deadline\n"
            + PREFIX_START_DATE + " - Start Date\n"
            + PREFIX_DURATION + " - Duration\n"
            + "Example 1: " + COMMAND_WORD + " " + PREFIX_ROLE + "Google\n"
            + "Example 2: " + COMMAND_WORD + " " + PREFIX_DURATION + "2-3\n"
            + "To reset filters: " + COMMAND_WORD + " " + DEFAULT_KEYWORD;

    private final Predicate<Internship> predicate;
    private final String filterParameter;
    private final String filterValue;

    /**
     * Constructs a Filter Command without the additional parameter and value information.
     * @param predicate
     */
    public FilterCommand(Predicate<Internship> predicate) {
        this.predicate = predicate;
        this.filterParameter = "";
        this.filterValue = "";
    }

    /**
     * Constructs a Filter Command.
     * @param filterParameter
     * @param filterValue
     * @param predicate
     */
    public FilterCommand(String filterParameter, String filterValue, Predicate<Internship> predicate) {
        this.predicate = predicate;
        this.filterParameter = filterParameter;
        this.filterValue = filterValue;
    }

    @Override
    public CommandResult execute(InternshipModel model) {
        requireNonNull(model);
        model.updateFilteredInternshipList(predicate);
        model.setFilterParameter(filterParameter);
        model.setFilterValue(filterValue);
        return new CommandResult(
                String.format(Messages.MESSAGE_INTERNSHIPS_LISTED_OVERVIEW, model.getFilteredInternshipList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterCommand)) {
            return false;
        }

        FilterCommand otherFilterCommand = (FilterCommand) other;
        return predicate.equals(otherFilterCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
