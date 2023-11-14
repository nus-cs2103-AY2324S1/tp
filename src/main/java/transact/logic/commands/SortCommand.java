package transact.logic.commands;

import static java.util.Objects.requireNonNull;
import static transact.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static transact.logic.parser.CliSyntax.PREFIX_DATE_SORTING;

import java.util.ArrayList;
import java.util.Comparator;

import transact.logic.commands.exceptions.CommandException;
import transact.model.Model;
import transact.model.transaction.Transaction;
import transact.ui.MainWindow;

/**
 * Sorts the transaction list by the specified attributes.
 */
public class SortCommand extends Command {

    /**
     * The type of attribute to sort by.
     */
    public enum SortType {
        DATE, AMOUNT
    }

    /**
     * Encapsulates all rules to sort by.
     */
    public static class SortRules {
        private final ArrayList<SortInfo> sortRules;

        public SortRules() {
            sortRules = new ArrayList<>();
        }

        public void addSortRule(SortInfo sortInfo) {
            sortRules.add(sortInfo);
        }

        /**
         * Returns a comparator that sorts transactions by the specified rules.
         *
         * @throws CommandException if no sort rules are specified, or invalid sort type is present
         */
        public Comparator<Transaction> getComparator() throws CommandException {
            if (sortRules.isEmpty()) {
                throw new CommandException("No sort rules specified");
            }

            Comparator<Transaction> allComparators = null;
            for (SortInfo sortInfo : sortRules) {
                Comparator<Transaction> comparator;
                switch (sortInfo.sortType) {
                case DATE:
                    comparator = Comparator.comparing(transaction -> transaction.getDate().getDate());
                    break;
                case AMOUNT:
                    comparator = Comparator.comparing(transaction -> transaction.getAmount().getValue());
                    break;
                default:
                    throw new CommandException("Invalid sort type");
                }
                if (!sortInfo.isAscending) {
                    comparator = comparator.reversed();
                }
                if (allComparators == null) {
                    allComparators = comparator;
                    continue;
                }
                allComparators = allComparators.thenComparing(comparator);
            }

            return allComparators;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (SortInfo sortInfo : sortRules) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(sortInfo.sortType.toString().toLowerCase());
                sb.append(" ");
                sb.append(sortInfo.isAscending ? "ASC" : "DESC");
            }
            return sb.toString();
        }
    }

    /**
     * Encapsulates a single rule to sort by, including attribute to sort by and sort direction.
     */
    public static class SortInfo {
        private final SortType sortType;
        private final boolean isAscending;

        /**
         * Creates a new {@code SortInfo} with the specified {@code sortType} and
         * whether it is ascending.
         */
        public SortInfo(SortType sortType, boolean isAscending) {
            this.sortType = sortType;
            this.isAscending = isAscending;
        }
    }

    public static final String COMMAND_WORD = "sort";

    public static final String SORT_DIRECTION_PHRASE = "<asc or desc>";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts transactions by attributes. "
            + "Parameters: "
            + "[" + PREFIX_DATE_SORTING + SORT_DIRECTION_PHRASE + "] "
            + "[" + PREFIX_AMOUNT + SORT_DIRECTION_PHRASE + "]\n"
            + "Parameters are optional, but at least one must be specified.";

    public static final String MESSAGE_SUCCESS = "Transactions sorted by: %1$s";

    private final SortRules sortRules;

    /**
     * Creates a SortCommand to sort the transaction list by the specified
     * {@code sortRules}.
     */
    public SortCommand(SortRules sortRules) {
        this.sortRules = sortRules;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredTransactionList(sortRules.getComparator());

        return new CommandResult(String.format(MESSAGE_SUCCESS, sortRules),
                MainWindow.TabWindow.TRANSACTIONS);
    }
}
