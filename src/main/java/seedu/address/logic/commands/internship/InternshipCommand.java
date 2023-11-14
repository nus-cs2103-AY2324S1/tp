package seedu.address.logic.commands.internship;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.View;

/**
 * Command for internships CRUD operations
 */
public abstract class InternshipCommand extends Command {
    public final View correctViewNeeded = View.INTERNSHIPS;

    public abstract CommandResult execute(Model model) throws CommandException;
}
