package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.Model;
import seedu.address.model.person.Person;



/**
 * Lists all client in the address book to the user.
 */
public class ListClientCommand extends Command {

    public static final String COMMAND_WORD = "listclient";
    public static final String MESSAGE_SUCCESS = "Listed all clients";
    public static final String MESSAGE_NO_CLIENTS = "There are no clients in the address book";
    public static final Predicate<Person> CLIENT_TAG_PREDICATE = person -> person.isClient();

    private static final Logger logger = LogsCenter.getLogger(ListClientCommand.class);

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(CLIENT_TAG_PREDICATE);
        if (model.getFilteredPersonList().size() == 0) {
            return new CommandResult(MESSAGE_NO_CLIENTS);
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

