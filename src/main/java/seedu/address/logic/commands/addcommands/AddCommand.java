package seedu.address.logic.commands.addcommands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHEDULED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.commandresults.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Add a person or a company into the address book.
 */
public abstract class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_SUCCESS = "Success message for the add command!";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person, "
            + "a company or an internship to the address book. \n"
            + "\n"
            + "Parameters for Person: "
            + "p - add a person's contact \n"
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " p "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney \n"
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
            + PREFIX_TAG + "interested \n"
            + "\n"
            + "Parameters for Internship: "
            + "i - add an internship to a company contact \n"
            + "INDEX (must be a positive integer) "
            + PREFIX_NAME + "NAME "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_SCHEDULED + "SCHEDULED_TIME (in dd-MM-yyyy HH:mm) \n"
            + "Example: " + COMMAND_WORD + " i " + "1 "
            + PREFIX_NAME + "Software Engineering Intern 2024 "
            + PREFIX_DESCRIPTION + "Develop new features/functionalities on existing software products "
            + PREFIX_SCHEDULED + "10-03-2024 11:15";


    @Override
    public abstract CommandResult execute(Model model) throws CommandException;
}
