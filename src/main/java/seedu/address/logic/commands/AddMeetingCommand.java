package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_TIME;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Client;
import seedu.address.model.person.Lead;
import seedu.address.model.person.MeetingTime;
import seedu.address.model.person.Person;

/**
 * Adds a new meeting time to the person identified by the displayed index in the address book.
 */
public class AddMeetingCommand extends Command {
    public static final String COMMAND_WORD = "addmeeting";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a new meeting time to the person identified by the displayed index in the address book.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_MEETING_TIME + "MEETING_TIME \n"
            + "Example: " + COMMAND_WORD + " 1 m/12/10/2023 16:00";

    public static final String MESSAGE_ADD_MEETING_SUCCESS = "Added Meeting: %1$s to Person %2$s";
    public static final String MESSAGE_MEETING_ALREADY_EXISTS =
            "Person already has a meeting time, use the edit command instead";

    private final Index targetIndex;
    private final MeetingTime meetingTime;

    /**
     * Adds a meeting to a person.
     * @param targetIndex of the Person in the filtered list to add a meeting time to
     * @param meetingTime to be added
     */
    public AddMeetingCommand(Index targetIndex, MeetingTime meetingTime) {
        requireAllNonNull(targetIndex, meetingTime);

        this.targetIndex = targetIndex;
        this.meetingTime = meetingTime;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToAddMeeting = lastShownList.get(targetIndex.getZeroBased());

        if (personToAddMeeting.getMeetingTime().isPresent()) {
            throw new CommandException(MESSAGE_MEETING_ALREADY_EXISTS);
        }

        Person personWithMeetingAdded = addMeeting(personToAddMeeting, meetingTime);
        model.setPerson(personToAddMeeting, personWithMeetingAdded);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_ADD_MEETING_SUCCESS,
                personWithMeetingAdded.getMeetingTimeString(),
                targetIndex.getOneBased()));
    }

    /**
     * Adds a meeting to a person.
     * @param personToAddMeeting Person to add meeting to.
     * @return Person with meeting added.
     */
    private static Person addMeeting(Person personToAddMeeting, MeetingTime meetingTime) {
        requireNonNull(personToAddMeeting);
        assert personToAddMeeting.isLead() || personToAddMeeting.isClient();
        assert personToAddMeeting.getMeetingTime().isEmpty();

        Person personWithMeetingAdded;

        if (personToAddMeeting.isClient()) {
            personWithMeetingAdded = new Client(
                    personToAddMeeting.getName(),
                    personToAddMeeting.getPhone(),
                    personToAddMeeting.getEmail(),
                    personToAddMeeting.getAddress(),
                    Optional.of(meetingTime),
                    personToAddMeeting.getTags());
        } else {
            // If person is not Client, person is a Lead
            assert personToAddMeeting.isLead();
            Lead leadToAddMeeting = (Lead) personToAddMeeting;
            personWithMeetingAdded = new Lead(
                    leadToAddMeeting.getName(),
                    leadToAddMeeting.getPhone(),
                    leadToAddMeeting.getEmail(),
                    leadToAddMeeting.getAddress(),
                    leadToAddMeeting.getKeyMilestone(),
                    Optional.of(meetingTime),
                    leadToAddMeeting.getTags());
        }

        return personWithMeetingAdded;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddMeetingCommand)) {
            return false;
        }

        AddMeetingCommand otherAddMeetingCommand = (AddMeetingCommand) other;
        return targetIndex.equals(otherAddMeetingCommand.targetIndex)
                && meetingTime.equals(otherAddMeetingCommand.meetingTime);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
