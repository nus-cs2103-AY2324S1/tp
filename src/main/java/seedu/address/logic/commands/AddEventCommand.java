package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.Main;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;
import seedu.address.ui.MainWindow;

/**
 * The command handler for {@code add event} command
 */
public class AddEventCommand extends AddCommand {

    public static final String SECONDARY_COMMAND_WORD = "event";
    public static final String MESSAGE_SUCCESS = "New event added: ";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + SECONDARY_COMMAND_WORD
            + ": Adds an event to a contact.\n"
            + "Usage:  add event -n CONTACT_NAME -en EVENT_NAME -st "
            + "START_TIME [-et END_TIME] [-loc LOCATION] [-i INFORMATION]";
    public static final String MESSAGE_PERSON_NOT_FOUNT = "Can not find the target contact with name: ";
    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists under the contact.";

    private final Event toAdd;
    private final String contactName;

    /**
     * Creates an AddEventCommand to add the specified {@code Event}
     */
    public AddEventCommand(String contactName, Event event) {
        requireNonNull(contactName);
        requireNonNull(event);
        this.contactName = contactName;
        this.toAdd = event;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person person = model.findPersonByName(this.contactName);
        if (person == null) {
            throw new CommandException(MESSAGE_PERSON_NOT_FOUNT + this.contactName);
        }
        boolean eventExists = person.getEvents().stream().anyMatch(e -> e.getName().equals(this.toAdd.getName()));
        if (eventExists) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }
        person.addEvent(this.toAdd);

        MainWindow.refreshPersonListPanelImmediately();
        return new CommandResult(MESSAGE_SUCCESS + toAdd.getName());
    }
}
