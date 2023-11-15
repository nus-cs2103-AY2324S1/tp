package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.timetable.Schedule;

/**
 * Represents a command to add a non-recurring event to the calendar.
 * The event can either be a dated event or a meetup event.
 * A dated event is an event that is added to a friend's schedule or the user's schedule.
 * A meetup event is an event that is added to the user's schedule and involves meeting up with a friend.
 * Inherits from the Command class.
 */
public class AddEventCommand extends Command {

    public static final String COMMAND_WORD = "addevent";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Adds a non-recurring event to the calendar.\n"
        + "Parameters: "
        + "INDEX "
        + "en/EVENT_NAME "
        + "h/[Date [YYYY-MM-DD] START_TIME [HHMM] END_TIME [HHMM]] "
        + "r/[REMINDER: y/n] \n"
        + "Example: " + COMMAND_WORD + " "
        + "1 "
        + "en/CS2103T Final Exam "
        + "h/2020-03-02 1400 1600 "
        + "r/y \n"
        + "Note: "
        + "Index should be the index of "
        + "the friend you are adding the dated event to or 'user' "
        + "if you would like to add the event to yourself \n";

    public static final String MESSAGE_SUCCESS = "New event added: ";

    private final String eventName;
    private final Index index;
    private final String schedule;
    private final String reminder;

    /**
     * Constructs an AddEventCommand object with the specified event name, index, schedule,
     * reminder and event type.
     * @param eventName The name of the event.
     * @param index The index of the friend to add the event to.
     * @param schedule The schedule of the event.
     * @param reminder The reminder of the event.
     */
    public AddEventCommand(String eventName, Index index,
        String schedule, String reminder) {

        requireAllNonNull(schedule);

        this.eventName = eventName;
        this.schedule = schedule;
        this.index = index;
        this.reminder = reminder;
    }

    /**
     * Constructs an AddEventCommand object with the specified event name, schedule,
     * reminder and event type.
     * @param eventName The name of the event.
     * @param schedule The schedule of the event.
     * @param reminder The reminder of the event.
     */
    public AddEventCommand(String eventName, String schedule,
        String reminder) {

        this(eventName, null, schedule, reminder);
    }

    /**
     * When successfully executed, it should add a new event to the user's timetable.
     * @param model {@code Model} which the command should operate on.
     * @return A command result in the form of a string.
     * @throws CommandException If the command is invalid.
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
            friendSchedule.addDatedEvent(eventName + " " + schedule + " " + reminder);
            return new CommandResult(MESSAGE_SUCCESS + "\nDated Event:\n" + eventName + " "
                    + schedule + " to " + friend.getName(), false, false, true, false);
        } catch (Exception e) {
            throw new CommandException(e.getMessage());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof AddEventCommand)) {
            return false;
        }

        AddEventCommand other = (AddEventCommand) o;
        if (index == null && other.index != null) {
            return false;
        } else if (index != null && other.index == null) {
            return false;
        } else {
            return eventName.equals(other.eventName)
                    && schedule.equals(other.schedule)
                    && reminder.equals(other.reminder);
        }
    }
}
