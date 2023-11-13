package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.appointment.AppointmentContainsNamePredicate;
import seedu.address.model.student.NameContainsKeywordsPredicate;


/**
 * Finds and lists all students in wellnus storage whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all students whose names matches all of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example (finding by first name): " + COMMAND_WORD + " bernice\n"
            + "Example (finding by full name): " + COMMAND_WORD + " bernice yu";

    private final String[] nameKeywords;

    /**
     * @param nameKeywords array of name strings to match
     */
    public FindCommand(String[] nameKeywords) {
        this.nameKeywords = nameKeywords;

    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        NameContainsKeywordsPredicate studentPredicate = new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords));
        AppointmentContainsNamePredicate appointmentPredicate =
                new AppointmentContainsNamePredicate(Arrays.asList(nameKeywords));
        // reset to all students and appointments
        model.updateFilteredStudentList(unused -> true);
        model.updateFilteredAppointmentList(unused -> true);
        model.updateFilteredStudentList(studentPredicate);
        model.updateFilteredAppointmentList(appointmentPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW, model.getFilteredStudentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindCommand)) {
            return false;
        }

        FindCommand otherFindCommand = (FindCommand) other;
        for (int i = 0; i < nameKeywords.length; i++) {
            if (!nameKeywords[i].equals(otherFindCommand.nameKeywords[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        ToStringBuilder str = new ToStringBuilder(this);
        for (int i = 0; i < nameKeywords.length; i++) {
            str.add("name keyword " + String.valueOf(i), nameKeywords[i]);
        }
        return str.toString();
    }
}
