package seedu.address.logic.commands.find;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.getMessageDevelopersListedOverview;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATEJOINED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUBID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.TabIndex;
import seedu.address.model.Model;
import seedu.address.model.developer.Developer;

/**
 * Represents a command to find developers in the address book based on various attributes.
 * This command allows users to search for developers using their names, roles, addresses, dates joined, emails,
 * phone numbers, associated projects, salaries, ratings, or GitHub IDs.
 */
public class FindDeveloperCommand extends Command {

    public static final String COMMAND_WORD = "find-developer";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Find developers based on various attributes.\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "NAME_KEYWORDS] "
            + "[" + PREFIX_ROLE + "ROLE_KEYWORDS] "
            + "[" + PREFIX_ADDRESS + "ADDRESS_KEYWORDS] "
            + "[" + PREFIX_DATEJOINED + "DATE_JOINED_KEYWORDS] "
            + "[" + PREFIX_EMAIL + "EMAIL_KEYWORDS] "
            + "[" + PREFIX_PHONE + "PHONE_KEYWORDS] "
            + "[" + PREFIX_PROJECT + "PROJECT_KEYWORDS] "
            + "[" + PREFIX_SALARY + "SALARY_KEYWORDS] "
            + "[" + PREFIX_RATING + "RATING_KEYWORDS] "
            + "[" + PREFIX_GITHUBID + "GITHUBID_KEYWORDS]\n"
            + "Example: " + COMMAND_WORD + " n/John r/developer\n";

    private Predicate<Developer> predicate;

    /**
     * Creates a FindDeveloperCommand with the specified predicate for filtering developers.
     *
     * @param predicate The predicate used to filter developers based on attributes.
     */
    public FindDeveloperCommand(Predicate<Developer> predicate) {
        this.predicate = predicate;
    }

    /**
     * Executes the find operation by updating the filtered developer list based on the given predicate.
     *
     * @param model The model to execute the command on.
     * @return A CommandResult indicating the results of the find operation.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredDeveloperList(predicate);

        int resultCount = model.getFilteredDeveloperList().size();
        String message = getMessageDevelopersListedOverview(resultCount);

        return new CommandResult(message, TabIndex.Developer);
    }

    /**
     * Checks if this FindDeveloperCommand is equal to another object.
     *
     * @param other The object to compare with this FindDeveloperCommand.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof FindDeveloperCommand)) {
            return false;
        }

        FindDeveloperCommand otherFindDeveloperCommand = (FindDeveloperCommand) other;
        return predicate.equals(otherFindDeveloperCommand.predicate);
    }

    /**
     * Generates a string representation of this FindDeveloperCommand.
     *
     * @return A string representation of this object.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
