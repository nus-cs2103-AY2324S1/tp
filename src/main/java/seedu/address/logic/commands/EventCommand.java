package seedu.address.logic.commands;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import java.time.LocalDateTime;
import java.util.List;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;

public class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds Event relating to a candidate. "
            + "Parameters: " + "[" + COMMAND_WORD + " <USERID> <DESCRIPTION> <BEGIN_TIME> <END_TIME>]...\n"
            + "Example: " + COMMAND_WORD + " 2 ev/Interview Round 1 bt/2023-10-22 09:00 et/2023-10-22 10:00";

    public static final String MESSAGE_SUCCESS = "Event added for: %1$s";
    private final Event event;

    public EventCommand(Event event) {
        this.event = event;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.addEvent(event);
        return new CommandResult(String.format(MESSAGE_SUCCESS, event.toString()), false, false, false, true);
    }


}
