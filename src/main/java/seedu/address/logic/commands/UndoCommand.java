package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.IOException;

import seedu.address.model.Model;

/**
 * Represents a command for undoing the previous command in the application.
 * Extends the abstract class {@link Command}.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_SUCCESS = "Previous command undid successfully!";


    @Override
    public CommandResult execute(Model model) {

        try {
            requireNonNull(model);
            model.setToPrevFilePath();
        } catch (IOException e) {
            return new CommandResult("ERROR");
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
