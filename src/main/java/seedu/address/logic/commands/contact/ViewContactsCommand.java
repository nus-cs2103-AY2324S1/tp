package seedu.address.logic.commands.contact;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CONTACTS;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all contacts in the JobFestGo to the user.
 */
public class ViewContactsCommand extends Command {
    public static final String COMMAND_WORD = "view_contacts";

    public static final String MESSAGE_SUCCESS = "Listed all contacts";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.switchToContactsScreen(true);
        model.switchToEventsScreen(false);
        model.switchToTagsScreen(false);
        model.updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
