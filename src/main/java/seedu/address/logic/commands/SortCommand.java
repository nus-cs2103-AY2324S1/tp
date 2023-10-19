package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Changes the remark of an existing person in the address book.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String REVERSE_KEYWORD = "R";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all persons in UniMate "
            + "by the alphabetical order of their name. "
            + "Existing remark will be overwritten by the input.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Sorted all persons by alphabetical order";
    private Comparator<Person> personComparator;

    /**
     * Constructor for SortCommand, creates a comparator to sort by Person's full name.
     */
    public SortCommand() {
        this.personComparator = (o1, o2) -> {
            if (o1.getName().fullName.equals(o2.getName().fullName)) {
                return 0;
            }
            return o1.getName().fullName.compareTo(o2.getName().fullName);
        };
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortPersonList(personComparator);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
