package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * The command handler for {@code list notes} command
 */
public class ListNoteCommand extends ListCommand {
    public static final String SECONDARY_COMMAND_WORD = "notes";
    public static final String MESSAGE = "Here are all the notes in this address book:\n";

    public static final String MESSAGE_USAGE = COMMAND_WORD + SECONDARY_COMMAND_WORD
            + ": Lists all notes in the address book";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> persons = model.getAddressBook().getPersonList();
        String result = MESSAGE + model.getAddressBook().noteListToString();
        return new CommandResult(result);
    }
}
