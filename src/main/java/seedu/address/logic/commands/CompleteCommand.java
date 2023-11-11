package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_DATE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Completes and removes appointments from one or multiple person in address book.
 */
public abstract class CompleteCommand extends Command {

    public static final String COMMAND_WORD = "complete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Completes an appointment identified "
            + "by the index number used in the displayed person list or "
            + "by a specified date\n"
            + "Parameters: [INDEX(must be a positive integer)] "
            + "[" + PREFIX_APPOINTMENT_DATE + "Appointment Date] *At least one parameter specified\n"
            + "Example: " + COMMAND_WORD + " 1 ";
    public static final String MESSAGE_COMPLETE_SUCCESS = "Appointment(s) Completed!";
    public static final String MESSAGE_INVALID_DATE_FORMAT = "Input Date should be in format of dd-MM-yyyy";
    public static final String MESSAGE_INVALID_DATE = "Please input a valid Date";
    public static final String MESSAGE_PERSON_NO_APPOINTMENT = "No Appointment Found:"
            + " Selected Person currently has no appointment scheduled";
    public static final String MESSAGE_DATE_NO_APPOINTMENT = "No Appointment Found:"
            + " No Appointments found with the current date";
    @Override
    public abstract CommandResult execute(Model model) throws CommandException;
}
