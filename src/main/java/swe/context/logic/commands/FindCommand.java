package swe.context.logic.commands;

import static java.util.Objects.requireNonNull;

import swe.context.commons.util.ToStringBuilder;
import swe.context.logic.Messages;
import swe.context.model.Model;
import swe.context.model.contact.Contact;
import swe.context.model.contact.NameContainsKeywordsPredicate;



/**
 * Finds and lists {@link Contact}s whose names match any of the specified
 * words in full. Case insensitive.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = String.format(
        "%s: Finds contacts by name. Case insensitive. Requires full word match."
                + "%nParameters: KEYWORD..."
                + "%nExample: %s amy Ben CHARLOTTE",
        FindCommand.COMMAND_WORD,
        FindCommand.COMMAND_WORD
    );

    private final NameContainsKeywordsPredicate predicate;

    public FindCommand(NameContainsKeywordsPredicate predicate) {
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
        if (!(other instanceof FindCommand)) {
            return false;
        }

        FindCommand otherFindCommand = (FindCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
