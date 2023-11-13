package seedu.letsgethired.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_CYCLE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.letsgethired.commons.util.ToStringBuilder;
import seedu.letsgethired.logic.commands.exceptions.CommandException;
import seedu.letsgethired.model.Model;
import seedu.letsgethired.model.application.InternApplication;

/**
 * Adds an intern application to the intern tracker.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds an application to the intern tracker.\n"
            + "Parameters: "
            + PREFIX_COMPANY + "COMPANY_NAME "
            + PREFIX_ROLE + "ROLE "
            + PREFIX_CYCLE + "CYCLE "
            + "[" + PREFIX_STATUS + "STATUS] "
            + "[" + PREFIX_DEADLINE + "DEADLINE] "
            + "\nExample: " + COMMAND_WORD + " "
            + PREFIX_COMPANY + "Jane Street "
            + PREFIX_ROLE + "Software Engineering Intern "
            + PREFIX_CYCLE + "Summer 2024 "
            + PREFIX_STATUS + "Accepted "
            + PREFIX_DEADLINE + "25 Sep 2023 ";

    public static final String MESSAGE_SUCCESS = "New intern application added";
    public static final String MESSAGE_DUPLICATE_INTERN_APPLICATION =
            "This application already exists in the intern tracker";

    private final InternApplication toAdd;

    /**
     * Creates an AddCommand to add the specified {@code InternApplication}
     */
    public AddCommand(InternApplication internApplication) {
        requireNonNull(internApplication);
        toAdd = internApplication;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasInternApplication(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_INTERN_APPLICATION);
        }

        model.addInternApplication(toAdd);
        return new CommandResult(MESSAGE_SUCCESS, toAdd);
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
