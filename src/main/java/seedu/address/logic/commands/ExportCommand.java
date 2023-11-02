package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VISUAL_TYPE;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Visual;

/**
 * Exports a visual presentation.
 */
public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Export chart. "
            + "Parameters: "
            + PREFIX_VISUAL_TYPE + "TYPE OF VISUAL REPRESENTATION \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_VISUAL_TYPE + "BAR ";

    public static final String MESSAGE_SUCCESS = "Chart is exported";
    private final Visual visual;

    /**
     * Creates an ExportCommand to export the table {@code Visual}
     */
    public ExportCommand(Visual visual) {
        requireNonNull(visual);
        this.visual = visual;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            model.export(visual);
        } catch (Exception e) {
            throw new CommandException("Create a visual representation before export.");
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, model.getFilteredPersonList()));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("visual", visual)
                .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ExportCommand other = (ExportCommand) obj;
        return Objects.equals(this.visual, other.visual);
    }
}
