package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Exits the view mode and prompts to the original fosterer list view.
 */
public class ViewExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting view mode as requested ...";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(
                MESSAGE_EXIT_ACKNOWLEDGEMENT,
                false,
                false,
                false,
                null,
                true
        );
    }
}
