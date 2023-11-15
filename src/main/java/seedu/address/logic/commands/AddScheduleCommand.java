package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.timetable.Schedule;

/**
 * Represents a command to add a schedule to a specified contact.
 * Inherits from the Command class.
 */
public class AddScheduleCommand extends Command {

    public static final String COMMAND_WORD = "addschedule";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a schedule to the specified contact.\n"
            + "Parameters: "
            + "INDEX "
            + "type/EVENT_TYPE "
            + "en/EVENT_NAME "
            + "h/[DAY_OF_WEEK START_TIME [HHMM] END_TIME [HHMM]]\n"
            + "Example: " + COMMAND_WORD
            + " 1"
            + " type/cca"
            + " en/Basketball"
            + " h/Monday 1400 1600\n"
            + "NOTE: If you want to add a cca/module to yourself, use addschedule user\n"
            + "Example: " + COMMAND_WORD
            + " user"
            + " type/cca"
            + " en/Basketball"
            + " h/Monday 1400 1600";

    public static final String MESSAGE_SUCCESS = "New event added: ";

    private final String eventName;
    private final String eventType;
    private final Index index;
    private final String schedule;

    /**
     * Constructs an AddScheduleCommand object with the specified event name, index, schedule and event type.
     * @param eventName The name of the event.
     * @param eventType The type of the event.
     * @param index The index of the friend to add the event to.
     * @param schedule The schedule of the event.
     */
    public AddScheduleCommand(String eventName, String eventType, Index index, String schedule) {
        this.eventName = eventName;
        this.eventType = eventType;
        this.index = index;
        this.schedule = schedule;
    }

    /**
     * Constructs an AddScheduleCommand object with the specified event name, schedule and event type.
     * @param eventName The name of the event.
     * @param eventType The type of the event.
     * @param schedule The schedule of the event.
     */
    public AddScheduleCommand(String eventName, String eventType, String schedule) {
        this(eventName, eventType, null, schedule);
    }

    /**
     * Executes the AddScheduleCommand and adds the specified event to the contact's calendar.
     * If the index is not null, the event will be added to the friend's calendar.
     * Otherwise, the event will be added to the user's calendar.
     *
     * @param model {@code Model} which the command should operate on.
     * @return Feedback message of the operation result, along with other information.
     * @throws CommandException If the event to be added already exists or the event type is invalid.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person friend;

        try {
            if (this.index == null) {
                friend = model.getUser();
            } else {
                List<Person> lastShownList = model.getFilteredPersonList();
                if (index.getZeroBased() >= lastShownList.size()) {
                    throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX + "\n"
                        + "Index can be max " + lastShownList.size() + "!");
                }
                friend = model.getFilteredPersonList().get(index.getZeroBased());
            }

            Schedule friendSchedule = friend.getSchedule();

            switch (eventType) {
            // If the event type is cca, add the event to the friend's cca schedule
            case "cca":
                friendSchedule.addCca(eventName + " " + schedule);
                return new CommandResult(MESSAGE_SUCCESS
                    + "\nCCA:\n"
                    + eventName
                    + " "
                    + schedule
                    + " to "
                    + friend.getName(), false, false, true, false);
            case "module":
                // If the event type is module, add the event to the friend's module schedule
                friendSchedule = friend.getSchedule();
                friendSchedule.addModule(eventName + " " + schedule);
                return new CommandResult(MESSAGE_SUCCESS
                    + "\nModule:\n"
                    + eventName
                    + " "
                    + schedule
                    + " to "
                    + friend.getName(), false, false, true, false);
            // If the event type is invalid, throw an exception
            default:
                throw new CommandException("Invalid event type!"
                    + "\n Event type can only be 'Module' or 'CCA'");
            }
        } catch (Exception e) {
            throw new CommandException(e.getMessage());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof AddScheduleCommand)) {
            return false;
        }

        AddScheduleCommand other = (AddScheduleCommand) o;
        if (index == null && other.index != null) {
            return false;
        } else if (index != null && other.index == null) {
            return false;
        } else {
            return eventName.equals(other.eventName)
                && eventType.equals(other.eventType)
                && schedule.equals(other.schedule);
        }
    }
}
