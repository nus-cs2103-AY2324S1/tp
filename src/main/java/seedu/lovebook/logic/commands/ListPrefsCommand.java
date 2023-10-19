package seedu.lovebook.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.lovebook.logic.Messages;
import seedu.lovebook.logic.commands.exceptions.CommandException;
import seedu.lovebook.model.DatePrefs;
import seedu.lovebook.model.Model;

/**
 * Lists all date preferences to the user.
 */
public class ListPrefsCommand extends Command {
    public static final String COMMAND_WORD = "showP";

    public static final String MESSAGE_SUCCESS = "Here are your preferences: ";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        DatePrefs prefs = model.getDatePrefs().getPreferences();
        return new CommandResult(MESSAGE_SUCCESS + Messages.format(prefs));
    }
}
