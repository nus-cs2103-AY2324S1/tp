package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Comparator;

import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.comparer.SortComparator;

/**
 * Sorts the persons in the address book by the given field.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String SORTBY_KEYWORD1 = "/byaddress";
    public static final String SORTBY_KEYWORD2 = "/byemail";
    public static final String SORTBY_KEYWORD3 = "/byname";
    public static final String SORTBY_KEYWORD4 = "/byphone";
    public static final String REVERSE_KEYWORD = "/reverse";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all persons in UniMate "
            + "by the ascending or descending order of specified attribute. \n"
            + "Example: " + COMMAND_WORD + " " + SORTBY_KEYWORD1 + " " + REVERSE_KEYWORD;

    public static final String MESSAGE_SUCCESS = "Sorted all persons by specified order";
    private Comparator<Person> personComparator;

    /**
     * Constructor for SortCommand, creates a comparator to sort by the specified field and order.
     */
    public SortCommand(ArrayList<SortComparator> sortComparatorList) {
        SortComparator sortComparator = sortComparatorList.get(0);
        boolean isReverse = sortComparator.getIsReverse();
        this.personComparator = isReverse
                ? sortComparator.reversed()
                : sortComparator;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortPersonList(personComparator);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
