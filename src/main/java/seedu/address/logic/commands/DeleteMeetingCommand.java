package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Client;
import seedu.address.model.person.Lead;
import seedu.address.model.person.Person;

/**
 * Deletes a meeting identified using it's displayed index from the address book.
 */
public class DeleteMeetingCommand extends Command {

    public static final String COMMAND_WORD = "deletemeeting";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the meeting identified by the index number used in the displayed meeting list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_MEETING_SUCCESS = "Deleted Meeting: %1$s from Person %2$s";

    private final Index targetIndex;

    public DeleteMeetingCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Deletes the meeting from the person.
     * @param personToDeleteMeeting Person to delete meeting from.
     * @return Person with meeting deleted.
     */
    private static Person deleteMeeting(Person personToDeleteMeeting) {
        requireNonNull(personToDeleteMeeting);
        assert personToDeleteMeeting.isLead() || personToDeleteMeeting.isClient();

        if (personToDeleteMeeting.getMeetingTime().isEmpty()) {
            return personToDeleteMeeting;
        }

        Person personWithMeetingDeleted;

        if (personToDeleteMeeting.isClient()) {
            personWithMeetingDeleted = new Client(
                personToDeleteMeeting.getName(),
                personToDeleteMeeting.getPhone(),
                personToDeleteMeeting.getEmail(),
                personToDeleteMeeting.getAddress(),
                Optional.empty(),
                personToDeleteMeeting.getTags());
        } else {
            // If person is not Client, person is a Lead
            assert personToDeleteMeeting.isLead();
            Lead leadWithMeetingDeleted = (Lead) personToDeleteMeeting;
            personWithMeetingDeleted = new Lead(
                    leadWithMeetingDeleted.getName(),
                    leadWithMeetingDeleted.getPhone(),
                    leadWithMeetingDeleted.getEmail(),
                    leadWithMeetingDeleted.getAddress(),
                    leadWithMeetingDeleted.getKeyMilestone(),
                    Optional.empty(),
                    leadWithMeetingDeleted.getTags());
        }

        return personWithMeetingDeleted;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDeleteMeeting = lastShownList.get(targetIndex.getZeroBased());
        Person personWithMeetingDeleted = deleteMeeting(personToDeleteMeeting);
        model.setPerson(personToDeleteMeeting, personWithMeetingDeleted);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_DELETE_MEETING_SUCCESS,
                personToDeleteMeeting.getMeetingTimeString(),
                targetIndex.getOneBased()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteMeetingCommand)) {
            return false;
        }

        DeleteMeetingCommand otherDeleteMeetingCommand = (DeleteMeetingCommand) other;
        return targetIndex.equals(otherDeleteMeetingCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
