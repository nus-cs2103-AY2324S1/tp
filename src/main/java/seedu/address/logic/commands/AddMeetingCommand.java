package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.EventDate;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventTime;
import seedu.address.model.event.Meeting;


/**
 * Adds a meeting.
 * @author Yuheng
 */
public class AddMeetingCommand extends Command{

    public static final String COMMAND_WORD = "add_meeting";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a meeting to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "MEETING_NAME "
            + PREFIX_DATE + "DATE "
            + "[" + PREFIX_START_TIME + "START_TIME]"
            + "[" + PREFIX_END_TIME + "END_TIME]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_DATE + "2020-10-10 "
            + PREFIX_START_TIME + "1000 "
            + PREFIX_END_TIME + "1200";

    public static final String MESSAGE_SUCCESS = "New meeting added: %1$s";
    public static final String MESSAGE_DUPLICATE_MEETING = "This meeting already exists!";
    private final Meeting toAdd;

    /**
     * Creates an AddMeetingCommand to add the specified {@code Meeting}
     */
    public AddMeetingCommand(Meeting meeting) {
        requireAllNonNull(meeting);
        this.toAdd = meeting;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.addEvent(toAdd);

        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.formatMeeting(toAdd)));

    }

}
