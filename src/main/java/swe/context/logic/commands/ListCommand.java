package swe.context.logic.commands;

import static java.util.Objects.requireNonNull;

import swe.context.logic.Messages;
import swe.context.model.Model;
import swe.context.model.ModelManager;
import swe.context.model.contact.Contact;



/**
 * Lists all {@link Contact}s.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setContactsFilter(ModelManager.FILTER_NONE);
        return new CommandResult(Messages.COMMAND_LIST_SUCCESS);
    }
}
