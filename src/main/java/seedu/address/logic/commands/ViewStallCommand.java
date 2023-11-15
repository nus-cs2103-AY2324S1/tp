package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STALL;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.stall.Stall;

/**
 * View a stall's detail identified using it's displayed index from Food Notes.
 */
public class ViewStallCommand extends Command {

    public static final String COMMAND_WORD = "view-stall";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the details of the stall identified by the index number used in the displayed stall list.\n"
            + "Parameters: "
            + PREFIX_STALL + "STALL_INDEX \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_STALL + "1";

    public static final String MESSAGE_VIEW_STALL_SUCCESS = "Here are the details of this stall.";

    private final Index stallIndex;

    /**
     * Creates a ViewStallCommand to view the specified {@code Stall}
     */
    public ViewStallCommand(Index stallIndex) {
        this.stallIndex = stallIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Stall> lastShownList = model.getFilteredStallList();

        if (stallIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STALL_DISPLAYED_INDEX);
        }

        Stall stallToView = lastShownList.get(stallIndex.getZeroBased());
        model.showStall(stallToView);
        model.setFilteredStall(stallIndex);
        model.setFilteredItemList(stallIndex);
        return new CommandResult(String.format(MESSAGE_VIEW_STALL_SUCCESS, Messages.format(stallToView)),
                CommandResult.ViewType.STALL_DETAIL);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ViewStallCommand)) {
            return false;
        }

        ViewStallCommand otherViewStallCommand = (ViewStallCommand) other;
        return stallIndex.equals(otherViewStallCommand.stallIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("stallIndex", stallIndex)
                .toString();
    }
}
