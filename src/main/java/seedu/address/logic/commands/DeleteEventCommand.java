package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventID;
import seedu.address.model.event.exceptions.EventNotFoundException;
import seedu.address.model.person.ContactID;
import seedu.address.model.person.Person;

/**
 * The command handler for {@code delete event} command
 */
public class DeleteEventCommand extends DeleteCommand {

    public static final String SECONDARY_COMMAND_WORD = "event";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " "
            + SECONDARY_COMMAND_WORD + ": Deletes an event from a contact.\n"
            + "Usage:  delete event -id CONTACT_ID -eid EVENT_ID";
    public static final String MESSAGE_PERSON_NOT_FOUND = "Can not find the target contact with ID: ";
    public static final String MESSAGE_SUCCESS = "Successfully deleted event: ";
    public static final String MESSAGE_EVENT_NOT_FOUND = "Event not found: ID = ";

    private final EventID eventIdToDelete;
    private final ContactID contactId;
    /**
     * Creates an DeleteEventCommand to delete the specified {@code Event}
     */
    public DeleteEventCommand(ContactID contactId, EventID eventIdToDelete) {
        this.contactId = contactId;
        this.eventIdToDelete = eventIdToDelete;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person person = model.findPersonByUserFriendlyId(this.contactId);
        if (person == null) {
            throw new CommandException(MESSAGE_PERSON_NOT_FOUND + this.contactId);
        }
        try {
            Event deletedEvent = model.removeEventByID(this.eventIdToDelete, person);
            return new CommandResult(MESSAGE_SUCCESS + this.eventIdToDelete
                    + ". " + deletedEvent.getName());
        } catch (EventNotFoundException e) {
            throw new CommandException(MESSAGE_EVENT_NOT_FOUND + this.eventIdToDelete);
        }
    }
}
