package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "(Opened help window.)\n"
            + "Command summary: \n"
            + "  -> add + n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]\n"
            + "  -> delete + INDEX\n"
            + "  -> edit + " +
            "INDEX (must be a positive integer) [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [r/REMARK] [t/TAG]\n"
            + "  -> remark + INDEX (must be a positive integer) r/[REMARK]\n"
            + "  -> list\n"
            + "  -> newteam + tn/TeamName tl/TeamLeader Example: newteam tn/ABC tl/JOHN DOE\n"
            + "  -> deleteteam + tn/Team Name\n"
            + "  -> editTeamLeader + tn/Team Name tl/Team Leader Name\n"
            + "  -> editTeamName + tn/Original Team Name tn/New Team Name\n"
            + "  -> dev2team + tn/Team Name n/Developer Name\n"
            + "  -> deletedev + tn/Team Name n/Developer Name\n"
            + "  -> listt\n"
            + "  -> clear (CAUTION! using the clear command might result in unintended loss of data!)\n"
            + "  -> find + KEYWORD [MORE_KEYWORDS]\n";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE,
                true, false, false, false);
    }
}
