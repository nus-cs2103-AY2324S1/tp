package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BUDGET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.customer.Budget;
import seedu.address.model.customer.BudgetAndTagsInRangePredicate;
import seedu.address.model.tag.Tag;

/**
 * Filter all customers in the address book to the user based on specific tags and/or budget.
 */
public class FilterCustomerCommand extends Command {
    public static final String COMMAND_WORD = "filtercust";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters customers from the address book. "
            + "Parameters: "
            + "[" + PREFIX_BUDGET + "BUDGET] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_BUDGET + "100000000 "
            + PREFIX_TAG + "bright "
            + PREFIX_TAG + "sunny";

    private BudgetAndTagsInRangePredicate predicate;

    /**
     * Creates an FilteredCustomerCommand to get all the specified {@code Customer}
     */
    public FilterCustomerCommand(BudgetAndTagsInRangePredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredCustomerList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_CUSTOMERS_LISTED_OVERVIEW, model.getFilteredCustomerList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterCustomerCommand)) {
            return false;
        }

        FilterCustomerCommand otherFilterCustomerCommand = (FilterCustomerCommand) other;
        return predicate.equals(otherFilterCustomerCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }

    /**
     * Stores the details to filter the customer with budget and tags.
     */
    public static class FilterCustomerDescriptor {
        private Budget budget;
        private Set<Tag> tags;

        public FilterCustomerDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public FilterCustomerDescriptor(FilterCustomerDescriptor toCopy) {
            setBudget(toCopy.budget);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldFiltered() {
            return CollectionUtil.isAnyNonNull(budget, tags);
        }

        public void setBudget(Budget budget) {
            this.budget = budget;
        }

        public Optional<Budget> getBudget() {
            return Optional.ofNullable(budget);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        public BudgetAndTagsInRangePredicate getPredicate() {
            return new BudgetAndTagsInRangePredicate(budget, tags);
        }
        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof FilterCustomerDescriptor)) {
                return false;
            }

            FilterCustomerDescriptor otherFilterPropertyDescriptor = (FilterCustomerDescriptor) other;
            return Objects.equals(budget, otherFilterPropertyDescriptor.budget)
                    && Objects.equals(tags, otherFilterPropertyDescriptor.tags);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("budget", budget)
                    .add("tags", tags)
                    .toString();
        }
    }
}
