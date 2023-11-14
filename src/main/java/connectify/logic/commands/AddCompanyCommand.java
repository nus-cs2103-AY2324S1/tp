package connectify.logic.commands;

import static connectify.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static connectify.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static connectify.logic.parser.CliSyntax.PREFIX_EMAIL;
import static connectify.logic.parser.CliSyntax.PREFIX_INDUSTRY;
import static connectify.logic.parser.CliSyntax.PREFIX_LOCATION;
import static connectify.logic.parser.CliSyntax.PREFIX_NAME;
import static connectify.logic.parser.CliSyntax.PREFIX_PHONE;
import static connectify.logic.parser.CliSyntax.PREFIX_WEBSITE;
import static java.util.Objects.requireNonNull;

import connectify.logic.Messages;
import connectify.logic.commands.exceptions.CommandException;
import connectify.model.Model;
import connectify.model.company.Company;

/**
 * Adds a company to the address book.
 */
public class AddCompanyCommand extends Command {

    public static final String COMMAND_WORD = "addCompany";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a company to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_INDUSTRY + "INDUSTRY "
            + PREFIX_LOCATION + "LOCATION "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_WEBSITE + "WEBSITE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_ADDRESS + "ADDRESS\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "TechCorp "
            + PREFIX_INDUSTRY + "Technology "
            + PREFIX_LOCATION + "Silicon Valley "
            + PREFIX_DESCRIPTION + "Leading tech company "
            + PREFIX_WEBSITE + "www.techcorp.com "
            + PREFIX_EMAIL + "contact@techcorp.com "
            + PREFIX_PHONE + "12345678 "
            + PREFIX_ADDRESS + "123 Tech St, Silicon Valley";

    public static final String MESSAGE_SUCCESS = "New company added: %1$s";
    public static final String MESSAGE_DUPLICATE_COMPANY = "This company already exists in the address book";

    private final Company toAdd;

    /**
     * Creates an AddCompanyCommand to add the specified {@code Company}
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
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCompanyCommand // instanceof handles nulls
                && toAdd.equals(((AddCompanyCommand) other).toAdd));
    }
}
