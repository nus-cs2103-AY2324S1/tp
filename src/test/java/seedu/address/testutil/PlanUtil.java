package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FRIEND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.AddPlanCommand;
import seedu.address.model.plan.Plan;

/**
 * A utility class for Plan.
 */
public class PlanUtil {

    /**
     * Returns an add plan command string for adding the {@code plan}
     */
    public static String getAddPlanCommand(Plan plan) {
        return AddPlanCommand.COMMAND_WORD + " " + getPlanDetails(plan);
    }

    /**
     * Returns the part of command string for the given {@code plan}'s details.
     */
    public static String getPlanDetails(Plan plan) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + plan.getPlanName().planName + " ");
        String originalDateTime = plan.getPlanDateTime().toString().replace(" ", "-");
        sb.append(PREFIX_DATETIME + originalDateTime + " ");
        sb.append(PREFIX_FRIEND + plan.getPlanFriend().getName().toString());
        return sb.toString();
    }
}
