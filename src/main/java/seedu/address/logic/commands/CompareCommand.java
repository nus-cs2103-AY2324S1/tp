package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.ui.CompareWindow;


/**
 * Compare two applicants side by side.
 * It takes two indices as parameters and compares the GPA of the applicants at those indices.
 */
public class CompareCommand extends Command {

    public static final String COMMAND_WORD = "compare";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Compares two applicants side by side.\n"
            + "Parameters: INDEX1 and INDEX2 (must be a positive integers)\n"
            + "Example: " + COMMAND_WORD + " 1 2";

    private final Index index1;
    private final Index index2;

    /**
     * Constructs a `CompareCommand` with the given indices.
     *
     * @param index1 The index of the first applicant to compare.
     * @param index2 The index of the second applicant to compare.
     */
    public CompareCommand(Index index1, Index index2) {
        requireNonNull(index1);
        requireNonNull(index2);

        this.index1 = index1;
        this.index2 = index2;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index1.equals(index2)) {
            throw new CommandException("Error: Please provide distinct indices. "
                    + "You cannot compare the same applicant.");
        }

        try {
            Person personToCompare1 = lastShownList.get(index1.getZeroBased());
            Person personToCompare2 = lastShownList.get(index2.getZeroBased());

            new CompareWindow(personToCompare1, personToCompare2).show();

            return new CommandResult("Comparison successful! ");

        } catch (IndexOutOfBoundsException e) {
            throw new CommandException("Error: One or both of the specified applicants"
                    + " were not found in the list.");
        }
    }
}

