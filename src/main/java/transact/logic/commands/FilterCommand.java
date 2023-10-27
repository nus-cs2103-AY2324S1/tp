package transact.logic.commands;

import static java.util.Objects.requireNonNull;
import static transact.logic.parser.CliSyntax.PREFIX_AFTER_DATE;
import static transact.logic.parser.CliSyntax.PREFIX_BEFORE_DATE;
import static transact.logic.parser.CliSyntax.PREFIX_BY_PERSON;
import static transact.logic.parser.CliSyntax.PREFIX_DESCRIPTION_HAS;
import static transact.logic.parser.CliSyntax.PREFIX_LESS_THAN_AMOUNT;
import static transact.logic.parser.CliSyntax.PREFIX_MORE_THAN_AMOUNT;
import static transact.model.Model.PREDICATE_SHOW_ALL_TRANSACTIONS;

import java.util.List;
import java.util.function.Predicate;

import transact.logic.Messages;
import transact.logic.commands.exceptions.CommandException;
import transact.model.Model;
import transact.model.transaction.Transaction;
import transact.model.transaction.info.Amount;
import transact.model.transaction.info.Date;
import transact.model.transaction.predicates.AfterOrOnDatePredicate;
import transact.model.transaction.predicates.BeforeOrOnDatePredicate;
import transact.model.transaction.predicates.ByPersonIdPredicate;
import transact.model.transaction.predicates.DescriptionContainsKeywordsPredicate;
import transact.model.transaction.predicates.LessThanOrEqualAmountPredicate;
import transact.model.transaction.predicates.MoreThanOrEqualAmountPredicate;
import transact.ui.MainWindow.TabWindow;

/**
 * Filters the transaction list to show only transactions that match the given
 * filter conditions.
 */
public class FilterCommand extends Command {

    /**
     * Stores the filter conditions to be used to filter the transaction list.
     */
    public static class FilterConditions {

        private List<String> descriptionHas;
        private Date afterDate;
        private Date beforeDate;
        private Amount moreThanAmount;
        private Amount lessThanAmount;
        private Integer byPersonId;

        public void setDescriptionHas(List<String> descriptionHas) {
            this.descriptionHas = descriptionHas;
        }

        public void setAfterDate(Date afterDate) {
            this.afterDate = afterDate;
        }

        public void setBeforeDate(Date beforeDate) {
            this.beforeDate = beforeDate;
        }

        public void setMoreThanAmount(Amount moreThanAmount) {
            this.moreThanAmount = moreThanAmount;
        }

        public void setLessThanAmount(Amount lessThanAmount) {
            this.lessThanAmount = lessThanAmount;
        }

        public void setByPersonId(Integer byPersonId) {
            this.byPersonId = byPersonId;
        }

        public Predicate<Transaction> getPredicate() {
            return PREDICATE_SHOW_ALL_TRANSACTIONS
                    .and(transaction -> descriptionHas == null
                            || new DescriptionContainsKeywordsPredicate(descriptionHas).test(transaction))
                    .and(transaction -> afterDate == null
                            || new AfterOrOnDatePredicate(afterDate).test(transaction))
                    .and(transaction -> beforeDate == null
                            || new BeforeOrOnDatePredicate(beforeDate).test(transaction))
                    .and(transaction -> moreThanAmount == null
                            || new MoreThanOrEqualAmountPredicate(moreThanAmount).test(transaction))
                    .and(transaction -> lessThanAmount == null
                            || new LessThanOrEqualAmountPredicate(lessThanAmount).test(transaction))
                    .and(transaction -> byPersonId == null
                            || new ByPersonIdPredicate(byPersonId).test(transaction));
        }
    }

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters transactions by conditions. "
            + "Parameters: "
            + "[" + PREFIX_DESCRIPTION_HAS + "KEYWORD(S)] "
            + "[" + PREFIX_AFTER_DATE + "DATE] "
            + "[" + PREFIX_BEFORE_DATE + "DATE] "
            + "[" + PREFIX_MORE_THAN_AMOUNT + "AMOUNT] "
            + "[" + PREFIX_LESS_THAN_AMOUNT + "AMOUNT] "
            + "[" + PREFIX_BY_PERSON + "PERSON_ID]\n"
            + "Multiple KEYWORDS can be specified, each separated by a space.\n"
            + "Parameters are optional, but at least one must be specified.";

    private final FilterConditions filterConditions;

    public FilterCommand(FilterConditions filterConditions) {
        this.filterConditions = filterConditions;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredTransactionList(filterConditions.getPredicate());
        return new CommandResult(
                String.format(Messages.MESSAGE_TRANSACTIONS_LISTED_OVERVIEW, model.getFilteredTransactionList().size()),
                TabWindow.TRANSACTIONS);
    }
}
