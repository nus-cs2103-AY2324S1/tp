package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

import java.util.Comparator;


/**
 * Sorts all persons in the list by lexicographical order barring capitalisation.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the current list based currently available "
            + "sort functions.\n"
            + "Currently available sorting variations: \n"
            + "-sort (lexicographical name sort)\n -appointment (earliest timing first)\n"
            + "Parameters: sort [variation]...\n"
            + "Example: sort sort";

    private Comparator<Person> comparator;

    /**
     * Default constructor for a SortCommand.
     * @param comparator comparator to be used to sort the list.
     */
    public SortCommand(Comparator<Person> comparator) {
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortFilteredPersonList(comparator);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("comparator", comparator)
                .toString();
    }
}
