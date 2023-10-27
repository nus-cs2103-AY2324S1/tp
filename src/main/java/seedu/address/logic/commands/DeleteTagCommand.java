package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.ContactID;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * The command handler for {@code delete tag} command
 */
public class DeleteTagCommand extends DeleteCommand {
    public static final String SECONDARY_COMMAND_WORD = "tag";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " "
            + SECONDARY_COMMAND_WORD + ": Delete one or more tags from a contact.\n"
            + "Usage:  delete tag -id CONTACT_ID -t TAGNAME";
    public static final String MESSAGE_PERSON_NOT_FOUND = "Can not find the target contact with ID: ";
    public static final String MESSAGE_SUCCESS = "Successfully deleted tags: ";

    private final Set<Tag> toDelete;
    private final int contactId;

    /**
     * Creates an DeleteTagCommand to delete the specified {@code Tag}(s).
     */
    public DeleteTagCommand(int contactId, Set<Tag> tagList) {
        requireNonNull(tagList);
        this.contactId = contactId;
        this.toDelete = tagList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person person = model.findPersonByUserFriendlyId(ContactID.fromInt(this.contactId));
        if (person == null) {
            throw new CommandException(MESSAGE_PERSON_NOT_FOUND + this.contactId);
        }
        person.removeTags(toDelete);

        final StringBuilder builder = new StringBuilder();
        builder.append(MESSAGE_SUCCESS);
        toDelete.forEach(builder::append);

        return new CommandResult(builder.toString());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteTagCommand)) {
            return false;
        }

        DeleteTagCommand otherAddNoteCommand = (DeleteTagCommand) other;

        boolean equalToDelete = toDelete.equals(otherAddNoteCommand.toDelete);
        boolean equalContactId = (contactId == otherAddNoteCommand.contactId);
        return equalToDelete && equalContactId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toDelete", toDelete)
                .add("contactId", contactId)
                .toString();
    }
}
