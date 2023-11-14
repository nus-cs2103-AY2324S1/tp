package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.ui.AppState;
import seedu.address.ui.AppState.ListType;
import seedu.address.ui.AppState.ModeType;


/**
 * Toggles the mode of NoteNote between contacts and meetings mode.
 */
public class ModeCommand extends Command {

    public static final String COMMAND_WORD = "mode";

    public static final String MESSAGE_SUCCESS = "Application mode set: %1$s";

    private static final Logger logger = LogsCenter.getLogger(ModeCommand.class);

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        AppState appState = AppState.getInstance();
        appState.changeMode();

        ModeType mode = appState.getModeType();

        if (mode == ModeType.CONTACTS) {
            logger.fine("Switching from contacts mode to meetings mode");
            model.updateFilteredContactList(Model.PREDICATE_SHOW_ALL_CONTACTS);
            appState.setListType(ListType.CONTACTS);
            appState.setContact(null);
            return new CommandResult(String.format(MESSAGE_SUCCESS, mode.toString()));
        } else if (mode == ModeType.MEETINGS) {
            logger.fine("Switching from meetings mode to contacts mode");
            model.updateFilteredMeetingList(Model.PREDICATE_SHOW_ALL_MEETINGS);
            appState.setMeeting(null);
            appState.setListType(ListType.MEETINGS);
            return new CommandResult(String.format(MESSAGE_SUCCESS, mode.toString()));
        } else {
            logger.warning("Error has occurred while switching modes due to unexpected mode type");
            throw new CommandException("Unexpected application mode");
        }
    }
}
