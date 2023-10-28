package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an undo command in the address book.
 * This undoes the last undo-able command, which includes edit, add, or delete commands.
 * Optionally, you can specify the number of commands to undo.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Undoes the last undo-able command. "
            + "An undo-able command includes an edit, add, clear or delete command. "
            + "Optionally, you can specify the number of commands to undo.\n"
            + "Format: " + COMMAND_WORD + " [number]\n"
            + "Examples:\n"
            + " - " + COMMAND_WORD + " (undoes the last command)\n"
            + " - " + COMMAND_WORD + " 4 (undoes the last 4 commands)";

    public static final String INVALID_NEGATIVE_STEPS_TO_UNDO = "Undo step count cannot be a negative number.";

    public static final String INVALID_POSITIVE_STEPS_TO_UNDO = "Please provide a valid number of steps to undo, "
            + "not exceeding the available command history";

    public static final String NO_HISTORY_EXISTS_FAILURE = "There is no history of un-doable commands to be undone. "
            + "Please execute some undo-able commands first.";
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
            throw new CommandException(NO_HISTORY_EXISTS_FAILURE);
        }
        if (stepsToUndo > model.getCommandHistorySize()) {
            throw new CommandException(INVALID_POSITIVE_STEPS_TO_UNDO +
                    " (Currently max is: " + model.getCommandHistorySize() + ")");
        }

        assert stepsToUndo <= model.getCommandHistorySize();

        List<String> undoStatus = new ArrayList<>();

        while (stepsToUndo != 0) {
            UndoableCommand undoableCommand = model.popCommandFromHistory();
            CommandResult result = undoableCommand.undo(model);
            undoStatus.add(result.getFeedbackToUser());
            stepsToUndo--;
        }

        String combinedStatus = String.join("\n", undoStatus);
        return new CommandResult("Undoing command(s):\n" + combinedStatus);
    }
}
