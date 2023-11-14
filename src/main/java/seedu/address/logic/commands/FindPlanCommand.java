package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.AddPlanCommand.MESSAGE_FRIEND_NOT_FOUND;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.plan.PlanContainsFriendPredicate;


/**
 * Finds and lists all plans in the FriendBook which are associated to the given unique friend's name.
 */
public class FindPlanCommand extends Command {

    public static final String COMMAND_WORD = "find-plan";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all plans which contains the specified "
            + "friend. \nParameters: FRIEND_NAME \n"
            + "Example: " + COMMAND_WORD + " Elijah Chia";

    private final Name friendName;

    public FindPlanCommand(Name friendName) {
        this.friendName = friendName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // Check if a friend with given name exists
        Person person;
        try {
            String name = this.friendName.toString().toLowerCase();
            person = model.getPersonByName(name);
        } catch (PersonNotFoundException e) {
            throw new CommandException(MESSAGE_FRIEND_NOT_FOUND);
        }

        PlanContainsFriendPredicate planContainsFriendPredicate = new PlanContainsFriendPredicate(person);
        model.updateFilteredPlanList(planContainsFriendPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PLANS_LISTED_OVERVIEW, model.getFilteredPlanList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindPlanCommand)) {
            return false;
        }

        FindPlanCommand findPlanCommand = (FindPlanCommand) other;
        return friendName.equals(findPlanCommand.friendName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("Friend", friendName)
                .toString();
    }
}
