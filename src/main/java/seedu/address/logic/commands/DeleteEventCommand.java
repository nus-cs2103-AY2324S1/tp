package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * The command handler for {@code delete event} command
 */
public class DeleteEventCommand extends DeleteCommand {

    public static final String SECONDARY_COMMAND_WORD = "event";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " "
            + SECONDARY_COMMAND_WORD + ": Deletes an event from a contact.\n"
            + "Usage:  delete event -n CONTACT_NAME -en EVENT_NAME";
    public static final String MESSAGE_PERSON_NOT_FOUND = "Can not find the target contact with ID: ";
    public static final String MESSAGE_SUCCESS = "Successfully deleted event: ";
    public static final String MESSAGE_EVENT_NOT_FOUND = "Event not found: ID = ";

    private final int eventIdToDelete;
    private final int contactId;
    /**
     * Creates an DeleteEventCommand to delete the specified {@code Event}
     */
    public DeleteEventCommand(int contactId, int eventIdToDelete) {
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
        boolean success = person.removeEventByUserFriendlyId(this.eventIdToDelete);
        if (!success) {
            throw new CommandException(MESSAGE_EVENT_NOT_FOUND + this.eventIdToDelete);
        }

        return new CommandResult(MESSAGE_SUCCESS + this.eventIdToDelete);
    }
}
