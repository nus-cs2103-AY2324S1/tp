package seedu.ccacommander.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.ccacommander.logic.Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX;

import java.util.Collection;
import java.util.List;

import seedu.ccacommander.commons.core.index.Index;
import seedu.ccacommander.logic.Messages;
import seedu.ccacommander.logic.commands.exceptions.CommandException;
import seedu.ccacommander.model.Model;
import seedu.ccacommander.model.event.Event;
import seedu.ccacommander.model.event.SameEventPredicate;
import seedu.ccacommander.model.member.MemberInNameCollectionPredicate;
import seedu.ccacommander.model.shared.Name;
import seedu.ccacommander.ui.EventListPanel;
import seedu.ccacommander.ui.MemberListPanel;

/**
 * Lists all members of an event to the user
 */
public class ViewEventCommand extends Command {
    public static final String COMMAND_WORD = "viewEvent";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the members of the event identified by the index number used in the displayed event list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_SUCCESS = "Listed all members in %1$s.";

    private final Index targetIndex;

    /**
     * Creates a ViewEventCommand to list all members of the event displayed at the specified index.
     */
    public ViewEventCommand(Index targetIndex) {
        requireNonNull(targetIndex);

        this.targetIndex = targetIndex;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Event> lastShownEventList = model.getFilteredEventList();

        if (targetIndex.getOneBased() > lastShownEventList.size()) {
            throw new CommandException(MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }


        Event event = lastShownEventList.get(targetIndex.getZeroBased());
        Name eventName = event.getName();


        Collection<Name> memberNameCollection = model.updateEventHoursAndRemark(eventName);

        MemberListPanel.setDisplayMemberHoursAndRemark(true);
        EventListPanel.setDisplayEventHoursAndRemark(false);
        model.updateFilteredMemberList(new MemberInNameCollectionPredicate(memberNameCollection));
        model.updateFilteredEventList(new SameEventPredicate(event));
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(event)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewEventCommand // instanceof handles nulls
                && targetIndex.equals(((ViewEventCommand) other).targetIndex));
    }
}
