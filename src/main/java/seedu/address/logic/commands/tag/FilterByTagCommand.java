package seedu.address.logic.commands.tag;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.ContactIsTaggedPredicate;
import seedu.address.model.tag.Tag;

/**
 * Filters contacts in JobFestGo by {@code Tag}.
 */
public class FilterByTagCommand extends Command {
    public static final String COMMAND_WORD = "filter_by_tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters and displays all contacts who"

            + " are tagged with the input tags.\n"
            + "Parameters: "
            + "TAG_NAME [MORE_TAG_NAMES]...\n"
            + "Example: " + COMMAND_WORD + " "
            + "vendors ";

    private final List<Tag> tags;
    private final ContactIsTaggedPredicate predicate;

    /**
     * Creates a FilterCommand to filter Contacts in JobFestGo.
     */
    public FilterByTagCommand(List<Tag> tags, ContactIsTaggedPredicate predicate) {

        this.tags = tags;
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.isOnEventsScreen() || model.isOnTagsScreen()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CONTACT_NOT_DISPLAYED);
        }

        // Update the filtered lists accordingly.
        // If list has already been filtered, it will be filtered upon the previous predicate.
        Predicate<Contact> prevContactPred = model.getContactListPredicate();

        // Update model to depict which screen it is on currently.
        model.switchToEventsScreen(false);
        model.switchToTagsScreen(false);

        if (prevContactPred != null) {
            model.updateFilteredContactList(predicate.and(prevContactPred));
        } else {
            model.updateFilteredContactList(predicate);
        }

        return new CommandResult(
                String.format(Messages.MESSAGE_CONTACTS_LISTED_OVERVIEW, model.getFilteredContactList().size()),
                true);

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterByTagCommand)) {
            return false;
        }

        FilterByTagCommand otherFilterCommand = (FilterByTagCommand) other;
        return tags.equals(otherFilterCommand.tags) && predicate.equals(otherFilterCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
