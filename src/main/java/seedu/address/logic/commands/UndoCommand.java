package seedu.address.logic.commands;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_SUCCESS = "Undo Completed";
    public static final String MESSAGE_USAGE = "This command will undo the last command";


    @Override
    public CommandResult execute(Model model) { return new CommandResult(MESSAGE_SUCCESS); }
}
