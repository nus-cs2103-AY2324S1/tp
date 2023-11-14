package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Views the employee who is
 * on leave on a specific date.
 */
public class ViewLeaveCommand extends Command {

    public static final String COMMAND_WORD = "viewleave";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": View employee who is on leave on specific date.\n"
            + "Parameters: /on DATE (must be a valid date)\n"
            + "Example: " + COMMAND_WORD + " /on 01/01/2023";

    public static final String MESSAGE_SUCCESS = "Employee who is on leave on %1$s:\n%2$s";
    private final LocalDate dateToView;

    /**
     * Constructs a ViewLeaveCommand with the specified date to view.
     *
     * @param dateToView The date for which to view employees on leave.
     */
    public ViewLeaveCommand(LocalDate dateToView) {
        requireNonNull(dateToView);
        this.dateToView = dateToView;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<String> nameList = getNameList(model.getFilteredPersonList());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = dateToView.format(formatter);

        if (nameList.isEmpty()) {
            throw new CommandException(String.format(Messages.MESSAGE_NO_EMPLOYEE_ON_LEAVE, formattedDate));
        }

        String nameListInString = nameListToString(nameList);

        return new CommandResult(String.format(MESSAGE_SUCCESS, formattedDate, nameListInString),
                false, false, false, false, false, false);
    }

    /**
     * Gets the list of names of employees on leave from the provided list of persons.
     *
     * @param personList The list of persons to search for employees on leave.
     * @return A list of employee names on leave.
     */
    public List<String> getNameList(List<Person> personList) {
        requireNonNull(personList);

        Map<LocalDate, List<String>> leaveRecords = new HashMap<>();

        for (Person person : personList) {
            List<LocalDate> leaveList = person.getLeaveList();

            if (leaveList == null) {
                continue;
            }

            for (LocalDate leaveDate : leaveList) {
                if (leaveDate != null) {
                    LocalDate currentDateToCheck = leaveDate;
                    leaveRecords.computeIfAbsent(currentDateToCheck,
                            k -> new ArrayList<>()).add(person.getName().fullName);
                }
            }
        }

        List<String> personNames = leaveRecords.get(dateToView);

        if (personNames == null) {
            return Collections.emptyList(); // Return an empty list if there are no names
        }

        return personNames;
    }

    /**
     * Converts a list of employee names into a comma-separated string.
     *
     * @param personNames The list of employee names.
     * @return A comma-separated string of employee names.
     */
    public String nameListToString(List<String> personNames) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < personNames.size(); i++) {
            builder.append(personNames.get(i));
            if (i < personNames.size() - 1) {
                builder.append(", ");
            }
        }

        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewLeaveCommand)) {
            return false;
        }

        ViewLeaveCommand otherViewLeaveCommand = (ViewLeaveCommand) other;
        return dateToView.equals(otherViewLeaveCommand.dateToView);
    }
}
