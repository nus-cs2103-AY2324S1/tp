package swe.context.logic.commands;

import static java.util.Objects.requireNonNull;

import swe.context.commons.util.ToStringBuilder;
import swe.context.logic.Messages;
import swe.context.model.Model;
import swe.context.model.contact.Contact;
import swe.context.model.contact.ContainsTagPredicate;

/**
 * Filters and lists {@link Contact}s whose tags match the specified
 * tag in full. Case insensitive.
 */
public class FilterCommand extends Command {
    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = String.format(
        "%s: Filters contacts by tag. Case insensitive. Requires full tag match."
                + "%nParameters: TAG"
                + "%nExample: %s CS2103 course",
        FilterCommand.COMMAND_WORD,
        FilterCommand.COMMAND_WORD
    );

    private final ContainsTagPredicate predicate;

    public FilterCommand(ContainsTagPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setContactsFilter(predicate);
        return new CommandResult(
                Messages.contactsListedOverview(model.getFilteredContactList().size()));
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
