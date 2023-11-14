package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICATION_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REQUIREMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.InternshipModel;
import seedu.address.model.internship.Internship;

/**
 * Creates an internship entry in Flagship.
 */
public class CreateCommand extends InternshipCommand {

    public static final String COMMAND_WORD = "create";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates an internship in Flagship.\n"
            + "Parameters: "
            + PREFIX_COMPANY_NAME + "COMPANY_NAME "
            + PREFIX_ROLE + "ROLE "
            + PREFIX_APPLICATION_STATUS + "APPLICATION_STATUS "
            + PREFIX_DEADLINE + "DEADLINE "
            + PREFIX_START_DATE + "START_DATE "
            + PREFIX_DURATION + "DURATION "
            + "[" + PREFIX_REQUIREMENT + "REQUIREMENT]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_COMPANY_NAME + "Jane Street "
            + PREFIX_ROLE + "ML Engineer "
            + PREFIX_APPLICATION_STATUS + "Yet to apply "
            + PREFIX_DEADLINE + "25/12/2022 "
            + PREFIX_START_DATE + "20/01/2023 "
            + PREFIX_DURATION + "3 "
            + PREFIX_REQUIREMENT + "C++ "
            + PREFIX_REQUIREMENT + "Java ";

    public static final String MESSAGE_SUCCESS = "Here is the information of the entry: %s";

    public static final String MESSAGE_DUPLICATE_INTERNSHIP = "This internship already exists in Flagship";

    private final Internship toAdd;

    /**
     * Creates an CreateCommand to create the specified {@code Internship}.
     */
    public CreateCommand(Internship internship) {
        requireNonNull(internship);
        this.toAdd = internship;
    }

    /**
     * Executes the create command, adding the internship to the model.
     *
     * @param model The {@code InternshipModel} in which the internship should be created.
     * @return A {@code CommandResult} with a success message.
     * @throws CommandException If an identical internship entry already exists.
     */
    @Override
    public CommandResult execute(InternshipModel model) throws CommandException {
        requireNonNull(model);

        if (model.hasInternship(this.toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_INTERNSHIP);
        }

        model.createInternship(this.toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(this.toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CreateCommand)) {
            return false;
        }

        CreateCommand otherCreateCommand = (CreateCommand) other;
        return toAdd.equals(otherCreateCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
