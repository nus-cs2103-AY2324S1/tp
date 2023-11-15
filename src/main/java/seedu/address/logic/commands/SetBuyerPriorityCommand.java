package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.CommandWarnings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.displayable.Priority;
import seedu.address.model.displayable.buyer.Buyer;

/**
 * Sets a buyer's priority based on displayed index in the address book.
 */
public class SetBuyerPriorityCommand extends Command {

    public static final String COMMAND_WORD = "bprio";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sets a priority level for the buyer, identified by index in the displayed buyer list. "
            + "INDEX must be a positive integer, while PRIORITY can be either 'high', 'medium', 'low', or 'nil'.\n"
            + "Parameters: "
            + "INDEX "
            + "PRIORITY\n"
            + "Example: " + COMMAND_WORD + " 1" + " high";

    public static final String MESSAGE_SUCCESS = "The buyer's priority level has been set:\n%1$s";
    private final Index targetIndex;
    private final Priority priority;

    private final CommandWarnings commandWarnings;

    /**
     * Constructs a SetBuyerPriorityCommand to set the priority level of a specified buyer.
     *
     * @param targetIndex the index of the buyer to set the priority of.
     * @param priority the priority that the buyer is to be set to.
     */
    public SetBuyerPriorityCommand(Index targetIndex, Priority priority) {
        this.targetIndex = targetIndex;
        this.priority = priority;
        this.commandWarnings = new CommandWarnings();
    }
    /**
     * Constructs a SetBuyerPriorityCommand to set the priority level of a specified buyer with
     * pre-existing warnings.
     *
     * @param targetIndex the index of the buyer to set the priority of.
     * @param priority the priority that the buyer is to be set to.
     * @param commandWarnings A container for warnings that may occur.
     */
    public SetBuyerPriorityCommand(Index targetIndex, Priority priority, CommandWarnings commandWarnings) {
        this.targetIndex = targetIndex;
        this.priority = priority;
        this.commandWarnings = commandWarnings;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Buyer> lastShownList = model.getFilteredBuyerList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BUYER_DISPLAYED_INDEX);
        }

        Buyer targetBuyer = lastShownList.get(targetIndex.getZeroBased());
        Buyer buyerWithPriority = getBuyerWithPriority(targetBuyer, this.priority);

        model.setBuyer(targetBuyer, buyerWithPriority);
        model.commitAddressBook();

        if (commandWarnings.containsWarnings()) {
            return new CommandResult(commandWarnings.getWarningMessage());
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(buyerWithPriority)));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Buyer getBuyerWithPriority(Buyer targetBuyer, Priority priority) {
        assert targetBuyer != null;

        return new Buyer(
                targetBuyer.getName(),
                targetBuyer.getPhone(),
                targetBuyer.getEmail(),
                targetBuyer.getAddress(),
                targetBuyer.getHouseInfo(),
                targetBuyer.getTags(),
                priority);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SetBuyerPriorityCommand)) {
            return false;
        }

        SetBuyerPriorityCommand otherPriorityCommand = (SetBuyerPriorityCommand) other;
        return targetIndex.equals(otherPriorityCommand.targetIndex)
                && priority.equals(otherPriorityCommand.priority)
                && commandWarnings.equals(otherPriorityCommand.commandWarnings);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .add("priority", priority)
                .add("warnings", commandWarnings)
                .toString();
    }
}
