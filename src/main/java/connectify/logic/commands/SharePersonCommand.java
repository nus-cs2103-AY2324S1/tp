package connectify.logic.commands;

import static connectify.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.util.List;

import connectify.commons.core.index.Index;
import connectify.commons.util.ToStringBuilder;
import connectify.logic.Messages;
import connectify.logic.commands.exceptions.CommandException;
import connectify.model.Model;
import connectify.model.company.Company;
import connectify.model.person.Person;
import connectify.model.person.PersonList;

/**
 * Shares a person to another address book.
 */
public class SharePersonCommand extends Command {

    public static final String COMMAND_WORD = "sharePerson";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shares instructions on how to add a Person, from the specified "
            + "company, to another address book.\n"
            + "Parameters: COMPANY_INDEX (must be a positive integer) PERSON_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 2 1";

    public static final String MESSAGE_SHARE_PERSON_SUCCESS = "Command to add this Person:\n%1$s\n"
            + "Do take note that you need to specify Company, priority, "
            + "and any additional notes on your own.";

    private final Index companyIndex;
    private final Index personIndex;

    /**
     * Creates a SharePersonCommand to share a person from a company using their respective indices.
     *
     * @param companyIndex The index of the company from which the person should be shared.
     * @param personIndex The index of the person to be shared within the specified company.
     */
    public SharePersonCommand(Index companyIndex, Index personIndex) {
        requireAllNonNull(companyIndex, personIndex);
        this.companyIndex = companyIndex;
        this.personIndex = personIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Company> companyList = model.getFilteredCompanyList();

        if (companyIndex.getZeroBased() >= companyList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
        }

        Company companyToUpdate = companyList.get(companyIndex.getZeroBased());
        PersonList companyPersonsList = companyToUpdate.getPersonList();

        if (personIndex.getZeroBased() >= companyPersonsList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToShare = companyPersonsList.get(personIndex.getZeroBased());
        return new CommandResult(String.format(MESSAGE_SHARE_PERSON_SUCCESS,
                formatInput(Messages.format(personToShare))));
    }

    /**
     * Searches for Person information, and formats the output to the user.
     *
     * @param input Input to be formatted.
     * @return Formatted output.
     */
    public static String formatInput(String input) {
        String[] parts = input.split("; ");
        StringBuilder result = new StringBuilder("addPerson ");

        for (String part : parts) {
            String[] keyValue = part.split(": ", 2);
            String key = keyValue[0];
            String value = keyValue.length > 1 ? keyValue[1] : "";

            switch (key) {
            case "Phone":
                result.append("p/").append(value).append(" ");
                break;
            case "Email":
                result.append("e/").append(value).append(" ");
                break;
            case "Address":
                result.append("a/").append(value).append(" ");
                break;
            case "Note":
            case "Company":
                break;
            case "Tags":
                String[] tags = value.substring(1, value.length() - 1).split("]\\[");
                for (String tag : tags) {
                    result.append("t/").append(tag).append(" ");
                }
                break;
            default:
                result.append("n/").append(key).append(" ");
                break;
            }
        }

        return result.toString().trim();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("companyIndex", companyIndex)
                .add("personIndex", personIndex)
                .toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SharePersonCommand)) {
            return false;
        }

        SharePersonCommand otherShareCommand = (SharePersonCommand) other;
        return personIndex.equals(otherShareCommand.personIndex);
    }
}
