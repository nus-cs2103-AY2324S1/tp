package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECRUITER_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.company.Company;

/**
 * Adds a company to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE =
            "Format: " + COMMAND_WORD + " "
            + PREFIX_COMPANY_NAME + "COMPANY_NAME "
            + PREFIX_ROLE + "ROLE "
            + PREFIX_STATUS + "STATUS "
            + PREFIX_DEADLINE + "DEADLINE "
            + PREFIX_RECRUITER_NAME + "RECRUITER_NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + "[" + PREFIX_PRIORITY + "PRIORITY] \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_COMPANY_NAME + "Google "
            + PREFIX_ROLE + "Software Engineer "
            + PREFIX_STATUS + "PA "
            + PREFIX_DEADLINE + "10-10-2023 "
            + PREFIX_RECRUITER_NAME + "Francis Tan "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_PRIORITY + "HIGH ";

    public static final String MESSAGE_SUCCESS = "New company added: %1$s";

    private static final Logger logger = LogsCenter.getLogger(AddCommand.class);

    private final Company toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Company}
     */
    public AddCommand(Company company) {
        requireNonNull(company);
        toAdd = company;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasCompany(toAdd)) {
            Company duplicateCompany = model.getDuplicateCompany(toAdd);
            int indexOfDuplicateCompany = model.getDuplicateIndexFromFilteredAddressbook(duplicateCompany);
            boolean inFilteredList = true;

            if (indexOfDuplicateCompany == -1) {
                indexOfDuplicateCompany = model.getDuplicateIndexFromOriginalAddressbook(duplicateCompany);
                inFilteredList = false;
            }

            String allChangedFields = toAdd.listAllChangedFields(duplicateCompany);

            throw new CommandException.DuplicateException(
                    Messages.getErrorMessageForDuplicateCompanyAddCommand(
                    duplicateCompany, indexOfDuplicateCompany, allChangedFields,
                            inFilteredList));
        }

        logger.info("Executing add command: " + toAdd.toString());
        model.addCompany(toAdd);
        model.setCurrentViewedCompany(toAdd);

        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.getCompanyName(toAdd)));
    }

    /**
     * Returns true if both companies have the same identity and data fields.
     * This defines a stronger notion of equality between two companies.
     */
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
