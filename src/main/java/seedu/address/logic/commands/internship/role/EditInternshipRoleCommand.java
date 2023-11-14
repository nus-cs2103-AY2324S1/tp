package seedu.address.logic.commands.internship.role;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_EDITED_ROLE_IS_THE_SAME;
import static seedu.address.logic.Messages.MESSAGE_EDIT_LEADS_TO_DUPLICATE_ROLES;
import static seedu.address.logic.parser.assignment.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.assignment.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.internship.role.CliSyntax.PREFIX_CYCLE;
import static seedu.address.logic.parser.internship.role.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.internship.role.CliSyntax.PREFIX_OUTCOME;
import static seedu.address.logic.parser.internship.role.CliSyntax.PREFIX_PAY;

import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.internship.InternshipCommand;
import seedu.address.model.Model;
import seedu.address.model.fields.ApplicationOutcome;
import seedu.address.model.fields.Cycle;
import seedu.address.model.fields.Description;
import seedu.address.model.fields.Location;
import seedu.address.model.fields.Pay;
import seedu.address.model.internship.role.InternshipRole;
import seedu.address.model.internship.task.InternshipTask;

/**
 * A command that edits the application outcome of an InternshipRole when executed.
 */
public class EditInternshipRoleCommand extends InternshipCommand {
    public static final String COMMAND_WORD = "edit-i-role";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the application outcome of an InternshipRole. "
            + "Parameters: "
            + PREFIX_INDEX + "INDEX (must be a positive integer) and AT LEAST one of the following: "
            + "[" + PREFIX_CYCLE + "CYCLE " + "]"
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION " + "]"
            + "[" + PREFIX_PAY + "PAY " + "]"
            + "[" + PREFIX_OUTCOME + "OUTCOME " + "]"
            + "[" + PREFIX_LOCATION + "LOCATION " + "]\n"
            + "Example: " + COMMAND_WORD + " i/ 1" + " o/ accepted" + " p/3000";

    public static final String MESSAGE_SUCCESS = "Internship role outcome updated to: %1$s";

    public static final String MESSAGE_INVALID_ROLE = "This internship role does not exist in the address book";

    private final Index index;
    private final Cycle newCycle;
    private final Description newDescription;
    private final Pay newPay;
    private final ApplicationOutcome newOutcome;
    private final Location newLocation;

    /**
     * The constructor for an EditInternshipRoleCommand
     *
     * @param index      The index of the InternshipRole to be edited
     * @param newOutcome The new outcome for the target InternshipRole
     */
    public EditInternshipRoleCommand(Index index, Cycle newCycle, Description newDescription, Pay newPay,
                                     ApplicationOutcome newOutcome, Location newLocation) {
        this.index = index;
        this.newCycle = newCycle;
        this.newDescription = newDescription;
        this.newPay = newPay;
        this.newOutcome = newOutcome;
        this.newLocation = newLocation;

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<InternshipRole> lastShownList = model.getFilteredInternshipRoleList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERNSHIP_ROLE_DISPLAYED_INDEX);
        }

        InternshipRole roleToEdit = lastShownList.get(index.getZeroBased());
        if (!model.hasInternshipRole(roleToEdit)) {
            throw new CommandException(MESSAGE_INVALID_ROLE);
        }

        InternshipRole editedRole = roleToEdit;
        if (newCycle != null) {
            editedRole = editedRole.getNewInternshipRoleWithCycle(newCycle);
        }
        if (newDescription != null) {
            editedRole = editedRole.getNewInternshipRoleWithDescription(newDescription);
        }
        if (newPay != null) {
            editedRole = editedRole.getNewInternshipRoleWithPay(newPay);
        }
        if (newOutcome != null) {
            editedRole = editedRole.getNewInternshipRoleWithOutcome(newOutcome);
        }
        if (newLocation != null) {
            editedRole = editedRole.getNewInternshipRoleWithLocation(newLocation);
        }

        if (roleToEdit.equals(editedRole)) {
            throw new CommandException(MESSAGE_EDITED_ROLE_IS_THE_SAME);
        }

        if (!editedRole.isDuplicate(roleToEdit) && model.hasInternshipRole(editedRole)) {
            throw new CommandException(MESSAGE_EDIT_LEADS_TO_DUPLICATE_ROLES);
        }

        model.setInternshipRole(roleToEdit, editedRole);

        for (InternshipTask internshipTask : model.getUnfilteredInternshipTaskList()) {
            if (internshipTask.getInternshipRole().equals(roleToEdit)) {
                model.setInternshipTask(internshipTask, internshipTask.editInternshipRole(editedRole));
            }
        }

        // Force a Re render with updated values, by calling the set method

        model.setInternshipRole(editedRole, editedRole);

        for (InternshipTask internshipTask : model.getUnfilteredInternshipTaskList()) {
            model.setInternshipTask(internshipTask, internshipTask);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(editedRole)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditInternshipRoleCommand)) {
            return false;
        }

        // Using objects.equals() since fields can be null
        EditInternshipRoleCommand otherEditInternshipRoleCommand = (EditInternshipRoleCommand) other;
        return index.equals(otherEditInternshipRoleCommand.index)
                && Objects.equals(newCycle, otherEditInternshipRoleCommand.newCycle)
                && Objects.equals(newOutcome, otherEditInternshipRoleCommand.newOutcome)
                && Objects.equals(newPay, otherEditInternshipRoleCommand.newPay)
                && Objects.equals(newDescription, otherEditInternshipRoleCommand.newDescription)
                && Objects.equals(newLocation, otherEditInternshipRoleCommand.newLocation);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("edit", index)
                .add("cycle", newCycle)
                .add("outcome", newOutcome)
                .add("pay", newPay)
                .add("description", newDescription)
                .add("location", newLocation)
                .toString();
    }
}
