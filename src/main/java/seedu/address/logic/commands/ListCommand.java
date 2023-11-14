package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.Model;
import seedu.address.model.person.ListPredicate;
import seedu.address.model.person.Person;

/**
 * Lists all applicants in the applicant list to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all applicants filtered by the given field.\n"
            + "Parameters: FIELD_NAME\n"
            + "Example: " + COMMAND_WORD + " hidden";

    public static final String MESSAGE_SUCCESS = "Listed applicants.";
    public static final String MESSAGE_EMPTY_LIST = "No applicants to list.";
    public final String fieldName;

    public ListCommand(String fieldName) {
        this.fieldName = fieldName;
    }

    public ListCommand() {
        this.fieldName = "";
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        if (fieldName.isEmpty()) {
            model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_UNHIDDEN_PERSONS);
        } else {
            Predicate<Person> predicate = new ListPredicate(fieldName, true);
            model.updateFilteredPersonList(predicate);
        }
        if (model.getFilteredPersonList().isEmpty()) {
            return new CommandResult(MESSAGE_EMPTY_LIST);
        }


        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof ListCommand)) {
            return false;
        }
        ListCommand otherListCommand = (ListCommand) other;
        return fieldName.equals(otherListCommand.fieldName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("fieldName", fieldName).toString();
    }
}
