package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
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
import seedu.address.model.property.Price;
import seedu.address.model.property.PriceAndTagsInRangePredicate;
import seedu.address.model.tag.Tag;

/**
 * Filter all properties in the address book to the user based on specific tags and/or price.
 */
public class FilterPropertyCommand extends Command {
    public static final String COMMAND_WORD = "filterprop";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters properties from the address book. "
            + "Parameters: "
            + "[" + PREFIX_PRICE + "PRICE] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PRICE + "100000000 "
            + PREFIX_TAG + "bright "
            + PREFIX_TAG + "sunny";

    private PriceAndTagsInRangePredicate predicate;

    /**
     * Creates an FilteredPropertyCommand to get all the specified {@code Property}
     */
    public FilterPropertyCommand(PriceAndTagsInRangePredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPropertyList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PROPERTIES_LISTED_OVERVIEW, model.getFilteredPropertyList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterPropertyCommand)) {
            return false;
        }

        FilterPropertyCommand otherFilterPropertyCommand = (FilterPropertyCommand) other;
        return predicate.equals(otherFilterPropertyCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }

    /**
     * Stores the details to filter the property with price and tags.
     */
    public static class FilterPropertyDescriptor {
        private Price price;
        private Set<Tag> tags;

        public FilterPropertyDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public FilterPropertyDescriptor(FilterPropertyDescriptor toCopy) {
            setPrice(toCopy.price);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldFiltered() {
            return CollectionUtil.isAnyNonNull(price, tags);
        }

        public void setPrice(Price price) {
            this.price = price;
        }

        public Optional<Price> getPrice() {
            return Optional.ofNullable(price);
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

        public PriceAndTagsInRangePredicate getPredicate() {
            return new PriceAndTagsInRangePredicate(price, tags);
        }
        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof FilterPropertyDescriptor)) {
                return false;
            }

            FilterPropertyDescriptor otherFilterPropertyDescriptor = (FilterPropertyDescriptor) other;
            return Objects.equals(price, otherFilterPropertyDescriptor.price)
                    && Objects.equals(tags, otherFilterPropertyDescriptor.tags);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("price", price)
                    .add("tags", tags)
                    .toString();
        }
    }
}
