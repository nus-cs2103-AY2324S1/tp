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
 * The command handler for {@code add tag} command
 */
public class AddTagCommand extends AddCommand {
    public static final String SECONDARY_COMMAND_WORD = "tag";
    public static final String MESSAGE_SUCCESS = "New tags added: ";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + SECONDARY_COMMAND_WORD
            + ": Adds tags to a contact from the contact list.\n"
            + "Usage:  add tag -id CONTACT_ID -t TAGNAME";
    public static final String MESSAGE_PERSON_NOT_FOUND = "Can not find the target contact with ID: ";

    private final Set<Tag> toAdd;
    private final int contactId;

    /**
     * Creates an AddTagCommand to add the specified {@code Tag}(s).
     */
    public AddTagCommand(int contactId, Set<Tag> tagList) {
        requireNonNull(tagList);
        this.contactId = contactId;
        this.toAdd = tagList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person person = model.findPersonByUserFriendlyId(ContactID.fromInt(this.contactId));
        if (person == null) {
            throw new CommandException(MESSAGE_PERSON_NOT_FOUND + this.contactId);
        }
        person.addTags(this.toAdd);

        final StringBuilder builder = new StringBuilder();
        builder.append(MESSAGE_SUCCESS);
        toAdd.forEach(builder::append);

        return new CommandResult(builder.toString());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddTagCommand)) {
            return false;
        }

        AddTagCommand otherAddNoteCommand = (AddTagCommand) other;

        boolean equalToAdd = toAdd.equals(otherAddNoteCommand.toAdd);
        boolean equalContactId = (contactId == otherAddNoteCommand.contactId);
        return equalToAdd && equalContactId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .add("contactId", contactId)
                .toString();
    }
}
