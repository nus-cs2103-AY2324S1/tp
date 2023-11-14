package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICATION_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;

import java.util.Comparator;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.InternshipModel;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.InternshipComparators;

/**
 * Sorts the list of internships by a specified category.
 */
public class SortCommand extends InternshipCommand {
    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the list of internships by the specified "
            + "category in either ascending (ASC) or descending (DESC) order.\n"
            + "You should specify the category by using its prefix and then specify the order (either ASC or DESC).\n"
            + "Available categories are:\n"
            + PREFIX_COMPANY_NAME + " - Company Name\n"
            + PREFIX_ROLE + " - Role\n"
            + PREFIX_APPLICATION_STATUS + " - Application Status\n"
            + PREFIX_DEADLINE + " - Deadline\n"
            + PREFIX_START_DATE + " - Start Date\n"
            + PREFIX_DURATION + " - Duration\n"
            + "Format: " + COMMAND_WORD + " [CATEGORY]/[ORDER]\n"
            + "Example 1: " + COMMAND_WORD + " " + PREFIX_COMPANY_NAME + " ASC\n"
            + "Example 2: " + COMMAND_WORD + " " + PREFIX_ROLE + " DESC";
    public static final String MESSAGE_INVALID_COLUMN = "The value entered for the category is invalid";
    public static final String MESSAGE_INVALID_ORDER = "The value entered for the order is invalid";
    public static final String DEFAULT_KEYWORD = "default";

    public final Prefix category;
    /**
     * Enumerates the possible sorting orders.
     * <p>
     * ASC - Ascending order (from smallest to largest).
     * DESC - Descending order (from largest to smallest).
     * </p>
     */
    public enum Order {
        ASC,
        DESC
    }

    public final Order sortedOrder;
    public final String messageSuccess;
    /**
     * Creates a SortCommand to sort the internships by default of Company Name and Ascending Order.
     */
    public SortCommand() {
        this(PREFIX_COMPANY_NAME, Order.ASC);
    }

    /**
     * Creates a SortCommand to sort the internships.
     *
     * @param category The category to sort by.
     * @param order The order to sort in.
     */
    public SortCommand(Prefix category, Order order) {
        requireNonNull(category);
        requireNonNull(order);
        this.category = category;
        this.sortedOrder = order;
        this.messageSuccess = "Here are the internships sorted by " + category + " and ordered by " + sortedOrder;
    }

    /**
     * Executes the sort command, sorting the internship by category.
     *
     * @param model The {@code InternshipModel} in which the internships will be sorted.
     * @return A {@code CommandResult} with a success message.
     * @throws CommandException If invalid arguments are given.
     */
    @Override
    public CommandResult execute(InternshipModel model) throws CommandException {
        requireNonNull(model);

        Comparator<Internship> comparator = createComparator();
        model.sortInternships(comparator);
        model.setComparatorPrefix(category.getPrefix());
        model.setComparatorOrder(sortedOrder);
        model.updateSortComparator(comparator);
        return new CommandResult(String.format(messageSuccess));
    }

    /**
     * Creates the comparator based on category and order.
     *
     * @return The comparator for sorting.
     * @throws CommandException If the category is invalid.
     */
    private Comparator<Internship> createComparator() throws CommandException {
        Comparator<Internship> comparator;
        if (category == PREFIX_COMPANY_NAME) {
            comparator = InternshipComparators.BY_COMPANY_NAME;
        } else if (category == PREFIX_ROLE) {
            comparator = InternshipComparators.BY_ROLE;
        } else if (category == PREFIX_APPLICATION_STATUS) {
            comparator = InternshipComparators.BY_APPLICATION_STATUS;
        } else if (category == PREFIX_DEADLINE) {
            comparator = InternshipComparators.BY_DEADLINE;
        } else if (category == PREFIX_START_DATE) {
            comparator = InternshipComparators.BY_START_DATE;
        } else if (category == PREFIX_DURATION) {
            comparator = InternshipComparators.BY_DURATION;
        } else {
            throw new CommandException(MESSAGE_INVALID_COLUMN);
        }

        if (sortedOrder == Order.DESC) {
            comparator = comparator.reversed();
        }
        return comparator;
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
        return sortedOrder.equals(otherSortCommand.sortedOrder) && category.equals(otherSortCommand.category);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("category", category)
                .add("sortedOrder", sortedOrder)
                .toString();
    }
}
