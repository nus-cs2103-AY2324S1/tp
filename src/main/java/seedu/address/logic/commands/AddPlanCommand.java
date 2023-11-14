package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FRIEND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PLANS;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.plan.Plan;
import seedu.address.model.plan.PlanDateTime;
import seedu.address.model.plan.PlanName;

/**
 * Adds a plan to the FriendBook.
 */
public class AddPlanCommand extends Command {

    public static final String COMMAND_WORD = "add-plan";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a plan to the FriendBook.\n"
            + "Parameters: "
            + PREFIX_NAME + "PLAN_NAME "
            + PREFIX_DATETIME + "DATE_TIME "
            + PREFIX_FRIEND + "FRIEND_NAME\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Project Meeting "
            + PREFIX_DATETIME + "2024-10-20-09:00 "
            + PREFIX_FRIEND + "John Doe";

    public static final String MESSAGE_SUCCESS = "New plan added: %1$s";
    public static final String MESSAGE_DUPLICATE_PLAN = "This plan already exists in the FriendBook";
    public static final String MESSAGE_FRIEND_NOT_FOUND = "The friend does not exist in the FriendBook.";

    private Plan toAdd;
    private final PlanName planName;
    private final PlanDateTime planDateTime;
    private final Name friendName;

    /**
     * Creates an AddPlanCommand to add the specified {@code Plan}
     */
    public AddPlanCommand(PlanName planName, PlanDateTime planDateTime, Name friendName) {
        requireAllNonNull(planName, planDateTime, friendName);
        this.planName = planName;
        this.planDateTime = planDateTime;
        this.friendName = friendName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPlanList(PREDICATE_SHOW_ALL_PLANS);

        Person person;
        try {
            person = model.getPersonByName(this.friendName);
        } catch (PersonNotFoundException e) {
            throw new CommandException(MESSAGE_FRIEND_NOT_FOUND);
        }

        toAdd = new Plan(this.planName, this.planDateTime, person);

        if (model.hasPlan(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PLAN);
        }
        model.addPlan(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        //instanceof handles nulls
        if (!(other instanceof AddPlanCommand)) {
            return false;
        }

        AddPlanCommand otherAddPlanCommand = (AddPlanCommand) other;
        return planName.equals(otherAddPlanCommand.planName)
                && planDateTime.equals(otherAddPlanCommand.planDateTime)
                && friendName.equals(otherAddPlanCommand.friendName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", "[" + planName + " with " + friendName + " at " + planDateTime + "]")
                .toString();
    }
}
