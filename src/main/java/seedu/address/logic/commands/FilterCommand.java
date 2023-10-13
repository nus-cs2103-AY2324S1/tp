package seedu.address.logic.commands;

import seedu.address.model.Model;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

public class FilterCommand extends Command {
    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose attributes contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Hugh "
            + PREFIX_PHONE + "90 "
            + PREFIX_EMAIL + "@u.nus.edu "
            + PREFIX_ADDRESS + "123, Tengah Ave 6, #69-420 "
            + PREFIX_TAG + "CS2103 "
            + PREFIX_TAG + "CSGod";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult("Filtering");
    }
}
