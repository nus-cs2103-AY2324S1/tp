package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;
import seedu.address.ui.MainWindow;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class DeleteEventCommand extends DeleteCommand {

    public static final String SECONDARY_COMMAND_WORD = "event";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + SECONDARY_COMMAND_WORD + ": Deletes an event from a contact.\n" +
            "Usage:  delete event -n CONTACT_NAME -en EVENT_NAME";
    private static final String MESSAGE_PERSON_NOT_FOUNT = "Can not find the target contact with name: ";
    private static final String MESSAGE_SUCCESS = "Successfully deleted event: ";
    private static final String MESSAGE_EVENT_NOT_FOUND = "Event not found: ";

    private final String eventNameToDelete;
    private final String contactName;
    /**
     * Creates an DeleteEventCommand to delete the specified {@code Event}
     */
    public DeleteEventCommand(String contactName, String eventNameToDelete) {
        requireNonNull(contactName);
        requireNonNull(eventNameToDelete);
        this.contactName = contactName;
        this.eventNameToDelete = eventNameToDelete;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person person = model.findPersonByName(this.contactName);
        if(person == null) {
            throw new CommandException(MESSAGE_PERSON_NOT_FOUNT + this.contactName);
        }
        boolean success = person.removeEventByName(this.eventNameToDelete);
        if(!success) {
            throw new CommandException(MESSAGE_EVENT_NOT_FOUND + this.eventNameToDelete);
        }

        MainWindow.refreshPersonListPanelImmediately();
        return new CommandResult(MESSAGE_SUCCESS + this.eventNameToDelete);
    }
}
