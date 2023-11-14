package seedu.address.logic.commands.addcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.commandresults.CommandResult;
import seedu.address.logic.commands.commandresults.RegularCommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.company.Company;

/**
 * Adds a company to the address book.
 */
public class AddCompanyCommand extends AddCommand {

    public static final String MESSAGE_SUCCESS = "New company added: %1$s";
    public static final String MESSAGE_DUPLICATE_COMPANY = "This company already exists in the address book";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person, "
            + "a company or an internship to the address book. \n"
            + "\n"
            + "Parameters for Company: "
            + "c - add a company's contact \n"
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + "[" + PREFIX_TAG + "TAG]... \n"
            + "Example: " + COMMAND_WORD + " c "
            + PREFIX_NAME + "Apple "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_DESCRIPTION + "Top tech company "
            + PREFIX_TAG + "tech "
            + PREFIX_TAG + "interested";

    private final Company toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Company}.
     */
    public AddCompanyCommand(Company company) {
        requireNonNull(company);
        toAdd = company;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasCompany(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_COMPANY);
        }

        model.addCompany(toAdd);
        return new RegularCommandResult(String.format(MESSAGE_SUCCESS, Messages.formatCompany(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddCompanyCommand)) {
            return false;
        }

        AddCompanyCommand otherAddCompanyCommand = (AddCompanyCommand) other;
        return toAdd.equals(otherAddCompanyCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
