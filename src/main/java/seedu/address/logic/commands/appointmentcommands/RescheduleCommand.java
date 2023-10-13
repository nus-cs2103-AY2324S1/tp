package seedu.address.logic.commands.appointmentcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_FROM_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_FROM_START;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_TO_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_TO_START;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.personcommands.AddCommand;
import seedu.address.model.Model;

/**
 * Reschedules an existing appointment
 */
public class RescheduleCommand extends Command {
    public static final String COMMAND_WORD = "reschedule";


    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Reschedules the appointment identified "
            + "by the index number used in the displayed appointment list.\n"
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: " + PREFIX_ID + "(must be a positive integer) "
            + "[" + PREFIX_APPOINTMENT_FROM_START + "FROM START DATE AND TIME] "
            + "[" + PREFIX_APPOINTMENT_FROM_END + "FROM END DATE AND TIME] "
            + "[" + PREFIX_APPOINTMENT_TO_START + "TO START DATE AND TIME] "
            + "[" + PREFIX_APPOINTMENT_TO_END + "TO END DATE AND TIME]\n"
            + "Example: " + PREFIX_ID + " 1 "
            + PREFIX_APPOINTMENT_FROM_START + "2020-02-05 09:00 "
            + PREFIX_APPOINTMENT_FROM_END + "2020-02-05 11:00 "
            + PREFIX_APPOINTMENT_TO_START + "2020-02-05 12:00 "
            + PREFIX_APPOINTMENT_TO_END + "2020-02-05 14:00 ";

    public static final String MESSAGE_SUCCESS = "Patient appointment rescheduled: %1$s";
    public static final String MESSAGE_NO_APPOINTMENT_FOUND = "No such appointment exists in the records";

    private final Index index;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public RescheduleCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        return new CommandResult(MESSAGE_NO_APPOINTMENT_FOUND,
                false, false);
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddCommand)) {
            return false;
        }

        RescheduleCommand e = (RescheduleCommand) other;
        return index.equals(e.index);
    }

}
