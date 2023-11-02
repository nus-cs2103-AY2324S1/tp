package seedu.address.logic.commands;

import seedu.address.logic.MacroPredicate;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
/**
 * Represents a command that filters the list of students.
 */
public class FilterPersonCommand extends FilterCommand {
    private MacroPredicate<Person> predicate;
    /**
     * Creates a FilterPersonCommand with the specified predicate.
     */
    public FilterPersonCommand(MacroPredicate<Person> predicate) {
        this.predicate = predicate;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.updateFilteredPersonList(predicate);
        return new CommandResult("Filtered student list successfully!");
    }
}
