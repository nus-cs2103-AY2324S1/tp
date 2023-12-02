package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.SortOrder;
import seedu.address.model.Model;
import seedu.address.model.company.Company;

/**
 * Sorts and lists all companies in the address book to the user by their deadlines.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_SUCCESS_ASCENDING = "Sorted all companies by their deadlines in ascending order";
    public static final String MESSAGE_SUCCESS_DESCENDING = "Sorted all companies by their deadlines in descending "
            + "order";
    public static final String MESSAGE_INVALID_SORT_ORDER = "Oops! You have entered an invalid sort order. "
            + "Please try again with a valid sort order! \n%1$s";

    public static final String MESSAGE_USAGE = "Valid ascending sort orders: a, asc, ascending. Valid descending "
            + "sort orders:  d, desc, descending, \n" + "Example: " + COMMAND_WORD + " ascending";
    private static final Logger logger = LogsCenter.getLogger(SortCommand.class);
    private final SortOrder sortOrder;

    /**
     * Creates a SortCommand to sort the companies by their deadlines.
     *
     * @param sortOrder the order in which the companies are sorted.
     */
    public SortCommand(SortOrder sortOrder) {
        this.sortOrder = sortOrder;
    }
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        logger.info("Executing SortCommand in " + sortOrder.toString().toLowerCase() + " order");

        Comparator<Company> comparator = sortOrder == SortOrder.ASCENDING
                ? Comparator.comparing(Company::getDeadline) : Comparator.comparing(Company::getDeadline).reversed();

        List<Company> sortedList = model.getAddressBook().getCompanyList().stream()
                .sorted(comparator)
                .collect(Collectors.toList());

        model.setAllCompanies(FXCollections.observableArrayList(sortedList));
        return new CommandResult(sortOrder == SortOrder.ASCENDING ? MESSAGE_SUCCESS_ASCENDING
                : MESSAGE_SUCCESS_DESCENDING);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof SortCommand)) {
            return false;
        }

        SortCommand otherSortCommand = (SortCommand) other;
        return sortOrder == otherSortCommand.sortOrder;
    }

    @Override
    public String toString() {
        return COMMAND_WORD + " " + sortOrder.toString();
    }
}
