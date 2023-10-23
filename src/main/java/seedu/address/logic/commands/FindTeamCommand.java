package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.team.TeamContainsKeywordsPredicate;

public class FindTeamCommand extends Command {

    public static final String COMMAND_WORD = "findTeam";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all teams whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alpha beta gamma";

    private final TeamContainsKeywordsPredicate predicate;

    public FindTeamCommand(TeamContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTeamList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_TEAMS_LISTED_OVERVIEW, model.getFilteredTeamList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof FindTeamCommand)) {
            return false;
        }

        FindTeamCommand otherFindTeamCommand = (FindTeamCommand) other;
        return predicate.equals(otherFindTeamCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
