package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;



/**
 * Lists all {@link Contact}s.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setContactsFilter(ModelManager.FILTER_NONE);
        return new CommandResult(Messages.MESSAGE_LIST_COMMAND_SUCCESS);
    }
}
