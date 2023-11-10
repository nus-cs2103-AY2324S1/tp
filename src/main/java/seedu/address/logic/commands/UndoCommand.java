package seedu.address.logic.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Represents an undo command in the address book.
 * This undoes the last undo-able command, which includes add, clear, edit, delete, log, alog, and clog commands.
 * Optionally, you can specify the number of commands to undo.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";

    public static final String COMMAND_WORD_ALIAS = "u";

    public static final String COMMAND_FORMAT = "Command Format: " + COMMAND_WORD + " or " + COMMAND_WORD_ALIAS
            + " [number]\n"
            + "Examples 1:\n"
            + " - " + COMMAND_WORD + " (undoes the last command)\n"
            + " - " + COMMAND_WORD_ALIAS + " 4 (undoes the last 4 commands)";

    public static final String MESSAGE_USAGE = COMMAND_WORD + "/" + COMMAND_WORD_ALIAS
            + ": Undoes the last undo-able command. "
            + "An undo-able command includes an add, clear, delete, edit, log, alog, and clog command.\n"
            + "Optionally, you can specify the number of commands to undo.\n"
            + COMMAND_FORMAT;
    public static final String INVALID_STEPS_TO_UNDO = "Undo count cannot be a negative number or zero.\n";

    public static final String INVALID_NATURAL_NUMBER_TO_UNDO = "Please provide a valid number of commands to undo, "
            + "not exceeding the available command history size";

    public static final String NO_HISTORY_EXISTS_FAILURE = "There is no history of undo-able commands to be undone.\n"
            + "Please execute some undo-able commands first.\n"
            + "Undo-able commands include add, clear, delete, edit, log, alog, and clog commands.";

    private static final Logger logger = Logger.getLogger(UndoCommand.class.getName());
    private int stepsToUndo;

    /**
     * Constructs an UndoCommand for the specified number of undo steps.
     *
     * @param stepsToUndo The number of steps to undo.
     */
    public UndoCommand(int stepsToUndo) {
        assert stepsToUndo > 0;
        this.stepsToUndo = stepsToUndo;
    }

    /**
     * Executes the undo command based on the number of steps provided.
     *
     * @param model The model where the undo operation will be applied.
     * @return A CommandResult encapsulating the status of the undo operation.
     * @throws CommandException If the number of steps to undo exceeds the available command history size.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (model.getCommandHistorySize() == 0) {
            logger.log(Level.WARNING, "No command history to undo");
            throw new CommandException(NO_HISTORY_EXISTS_FAILURE);
        }
        if (stepsToUndo > model.getCommandHistorySize()) {
            logger.log(Level.WARNING, "Invalid steps to undo");
            throw new CommandException(INVALID_NATURAL_NUMBER_TO_UNDO
                    + " (Currently max is: " + model.getCommandHistorySize() + ").\n"
                    + MESSAGE_USAGE);
        }

        assert stepsToUndo <= model.getCommandHistorySize();

        List<String> undoStatus = new ArrayList<>();
        int counter = 1;

        while (stepsToUndo != 0) {
            UndoableCommand undoableCommand = model.popCommandFromHistory();
            CommandResult result = undoableCommand.undo(model);
            undoStatus.add(counter + ". " + result.getFeedbackToUser());
            stepsToUndo--;
            counter++;
        }

        String combinedStatus = String.join("\n", undoStatus);
        return new CommandResult("Undoing " + (counter - 1) + " command(s):\n" + combinedStatus);
    }
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof UndoCommand)) {
            return false;
        }
        UndoCommand that = (UndoCommand) object;
        return stepsToUndo == that.stepsToUndo;
    }
}
