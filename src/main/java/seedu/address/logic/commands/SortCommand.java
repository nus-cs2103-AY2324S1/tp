package seedu.address.logic.commands;

import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.SortKeyComparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

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
    private List<String> sortOrder;

    /**
     * Constructor for SortCommand, takes in parameters specifying the sort required.
     * @param sortKeyComparators various comparators that will be merged into 1 in order of their index.
     */
    public SortCommand(SortKeyComparator... sortKeyComparators) {
        requireNonNull(sortKeyComparators);
        this.personComparator = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                if (o1.getName().fullName.equals(o2.getName().fullName)) {
                    return 0;
                }
                return o1.getName().fullName.compareTo(o2.getName().fullName);
            }
        };
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortPersonList(personComparator);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}