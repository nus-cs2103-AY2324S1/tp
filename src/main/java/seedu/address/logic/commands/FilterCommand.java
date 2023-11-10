package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEAVE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MANAGER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.employee.ContainsAllPredicate;

/**
 * Filters employees in ManageHR app whose department(s) contains the argument keyword.
 * Keyword matching is case-sensitive.
 */
public class FilterCommand extends Command {
    public static final String COMMAND_WORD = "filter";
    //@@author kenvynKwek
    public static final String MESSAGE_EXAMPLE = COMMAND_WORD + " "
    //@@author
            + PREFIX_LEAVE + " 15 "
            + PREFIX_DEPARTMENT + " R&D ";
    //@@author kenvynKwek
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": "
            + "Finds all employees who have parameters contain any of "
            + "the specified keywords (case-sensitive) and displays them as a list with index numbers.\n"
            + "Parameters:\n"
    //@@author
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_SALARY + "SALARY "
            + PREFIX_LEAVE + "LEAVE "
            + PREFIX_ROLE + "ROLE "
            + "[" + PREFIX_MANAGER + "MANAGER]...\n"
            + "[" + PREFIX_DEPARTMENT + "DEPARTMENT]...\n"
            //@@author kenvynKwek
            + "\n"
            + "Example: \n" + MESSAGE_EXAMPLE;
    private final ContainsAllPredicate predicate;

    public FilterCommand(ContainsAllPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEmployeeList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_EMPLOYEES_LISTED_OVERVIEW, model.getFilteredEmployeeList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterCommand)) {
            return false;
        }

        FilterCommand otherFilterCommand = (FilterCommand) other;
        return predicate.equals(otherFilterCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
//@@author
