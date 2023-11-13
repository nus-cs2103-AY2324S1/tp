package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddApplicantCommandParser;
import seedu.address.model.Model;
import seedu.address.model.person.Applicant;

/**
 * Adds an applicant to the address book.
 */
public class AddApplicantCommand extends Command {

    public static final String COMMAND_WORD = "addapplicant";
    public static final String COMMAND_ALIAS = "adda";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " or " + COMMAND_ALIAS
            + ": Adds an applicant to the applicant list. "
            + "\nParameters: "
            + PREFIX_NAME + " APPLICANT_NAME "
            + PREFIX_PHONE + " PHONE_NUMBER "
            + "[" + PREFIX_INTERVIEW + " INTERVIEW_TIME] "
            + "\nExample: " + COMMAND_WORD + " "
            + PREFIX_NAME + " John Doe "
            + PREFIX_PHONE + " 98765432 "
            + PREFIX_INTERVIEW + " 01/01/2024 1200";

    public static final String MESSAGE_SUCCESS = "New applicant added: %1$s";

    public static final String MESSAGE_DUPLICATE_APPLICANT = "This applicant already exists in the address book";

    private final Applicant toAdd;
    private final Logger logger = LogsCenter.getLogger(AddApplicantCommandParser.class);

    /**
     * Creates an AddApplicantCommand to add the specified {@code Applicant}
     *
     * @param applicant The applicant to add.
     */
    public AddApplicantCommand(Applicant applicant) {
        requireNonNull(applicant);
        toAdd = applicant;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasApplicant(toAdd)) {
            logger.fine("Duplicate applicant detected: " + toAdd);
            throw new CommandException(MESSAGE_DUPLICATE_APPLICANT);
        }

        model.addApplicant(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AddApplicantCommand)) {
            return false;
        }

        AddApplicantCommand otherCommand = (AddApplicantCommand) other;
        return toAdd.equals(otherCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
