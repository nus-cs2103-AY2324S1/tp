package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Selects a specific number of students randomly from the students displayed.
 */
public class RandomCommand extends Command {
    public static final String COMMAND_WORD = "random";
    public static final String MESSAGE_MARK_SUCCESS = "The following students are selected.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Selects a specific number of students randomly.\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + " 2";
    private final Index index;

    /**
     * Constructs a RandomCommand object.
     *
     * @param index of the number of students.
     */
    public RandomCommand(Index index) {
        requireAllNonNull(index);

        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);


        return new CommandResult(MESSAGE_MARK_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RandomCommand)) {
            return false;
        }

        RandomCommand e = (RandomCommand) other;
        return index.equals(e.index);
    }
}
