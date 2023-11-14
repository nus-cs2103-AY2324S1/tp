package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RSVP_STATUS;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.rsvp.Rsvp;
import seedu.address.model.rsvp.RsvpContainsEventPredicate;
import seedu.address.model.rsvp.RsvpStatus;

/**
 * Represent a command to update the RSVP status of a person for an event in the address book.
 */
public class RsvpCommand extends Command {
    public static final String COMMAND_WORD = "rsvp";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets the RSVP status of a person to an event. "
            + "Parameters: "
            + PREFIX_EVENT_ID + "EVENTID "
            + PREFIX_PERSON + "PERSONID "
            + PREFIX_RSVP_STATUS + "RSVPSTATUS\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_EVENT_ID + "1 "
            + PREFIX_PERSON + "1 "
            + PREFIX_RSVP_STATUS + "CC";

    public static final String MESSAGE_SUCCESS = "RSVP status has been updated: %1$s, %2$s, %3$s";
    public static final String MESSAGE_INVALID_INDEX = "Event or Person does not exist!";
    public static final String MESSAGE_PERSON_NOT_IN_EVENT = "%1$s has not been added to %2$s!";

    private final Index eventIndex;
    private final Index personIndex;
    private final RsvpStatus rsvpStatus;

    /**
     * Constructs an {@code RsvpCommand} to set the RSVP status for a person in an event.
     *
     * @param eventIndex The index of the event in the address book.
     * @param personIndex The index of the person in the address book.
     * @param rsvpStatus The RSVP status to be set for the person in the event.
     */
    public RsvpCommand(Index eventIndex, Index personIndex, RsvpStatus rsvpStatus) {
        requireNonNull(eventIndex);
        requireNonNull(personIndex);
        requireNonNull(rsvpStatus);
        this.eventIndex = eventIndex;
        this.personIndex = personIndex;
        this.rsvpStatus = rsvpStatus;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Rsvp rsvp = model.createRsvp(eventIndex, personIndex, rsvpStatus);
        // Check whether the event and person index is in the address book.
        if (rsvp == null) {
            throw new CommandException(MESSAGE_INVALID_INDEX);
        }
        // Check whether the person has been added to the event.
        if (!model.isValidRsvp(rsvp)) {
            throw new CommandException(
                    String.format(MESSAGE_PERSON_NOT_IN_EVENT, rsvp.getPersonName(), rsvp.getEventName()));
        }
        model.addRsvp(rsvp);
        model.updateFilteredEventRsvpList(new RsvpContainsEventPredicate(rsvp.getEvent()));
        model.setEventToView(rsvp.getEvent());
        return new CommandResult(String.format(MESSAGE_SUCCESS,
                rsvp.getEventName(), rsvp.getPersonName(), rsvpStatus.getStatus()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof RsvpCommand)) {
            return false;
        }

        RsvpCommand otherCommand = (RsvpCommand) other;
        return eventIndex.equals(otherCommand.eventIndex)
                && personIndex.equals(otherCommand.personIndex)
                && rsvpStatus.equals(otherCommand.rsvpStatus);
    }
}
