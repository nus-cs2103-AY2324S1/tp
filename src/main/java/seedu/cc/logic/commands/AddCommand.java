package seedu.cc.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.cc.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.cc.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.cc.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.cc.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.cc.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.cc.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.cc.commons.core.index.Index;
import seedu.cc.commons.util.ToStringBuilder;
import seedu.cc.logic.Messages;
import seedu.cc.logic.commands.exceptions.CommandException;
import seedu.cc.model.Model;
import seedu.cc.model.patient.Patient;

/**
 * Adds a patient to the clinic book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add-patient";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a patient to the clinic book. "
            + "\nParameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_NRIC + "NRIC "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_AGE + "AGE "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_NRIC + "S1234567A "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_AGE + "25 "
            + PREFIX_TAG + "BadBreath "
            + PREFIX_TAG + "OwesMoney";

    public static final String MESSAGE_SUCCESS = "New patient added: %1$s";
    public static final String MESSAGE_DUPLICATE_PATIENT = "This patient already exists in the clinic book";

    private final Patient toAdd;
    private final SwitchCommand switchCommand;


    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Patient patient) {
        requireNonNull(patient);
        toAdd = patient;
        this.switchCommand = new SwitchCommand(Index.fromZeroBased(0));
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPatient(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PATIENT);
        }

        model.addPatient(toAdd);
        switchCommand.execute(model);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
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

        AddCommand otherAddCommand = (AddCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
