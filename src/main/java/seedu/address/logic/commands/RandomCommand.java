package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;

/**
 * Selects a specific number of students randomly from the students displayed.
 */
public class RandomCommand extends Command {
    public static final String COMMAND_WORD = "random";
    public static final String MESSAGE_RANDOM_SUCCESS = "The following students are selected.\n";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Selects a specific number of students randomly.\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + " 2";
    public static final String MESSAGE_TOO_MUCH_TO_BE_SELECTED =
            "The number of students to be selected exceeds that of current students displayed";
    private final Index index;

    /**
     * Constructs a RandomCommand object.
     *
     * @param index of the number of students to be selected.
     */
    public RandomCommand(Index index) {
        requireAllNonNull(index);

        this.index = index;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory commandHistory) throws CommandException {
        requireNonNull(model);

        List<Student> lastShownList = model.getFilteredStudentList();
        if (this.index.getOneBased() > lastShownList.size()) {
            throw new CommandException(MESSAGE_TOO_MUCH_TO_BE_SELECTED);
        }

        Random random = new Random();
        HashSet<Integer> distinctInt = new HashSet<>();

        while (distinctInt.size() < index.getOneBased()) {
            int i = random.nextInt(lastShownList.size());
            distinctInt.add(i);
        }

        Integer[] randomInt = distinctInt.toArray(new Integer[0]);

        String result = MESSAGE_RANDOM_SUCCESS;

        for (Integer i : randomInt) {
            Student s = lastShownList.get(i);
            result += s.getName().toString() + " " + s.getStudentNumber().toString() + "\n";
        }

        return new CommandResult(result);
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
