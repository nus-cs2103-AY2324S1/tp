package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.group.Group;

/**
 * Lists all groups in the address book to the user.
 */
public class ListGroupCommand extends Command {

    public static final String COMMAND_WORD = "listgroup";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all groups in the contact list. "
            + "Should not contain any extra inputs.\n"
            + "Example: " + COMMAND_WORD;

    public static final StringBuilder MESSAGE_SUCCESS = new StringBuilder();


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        MESSAGE_SUCCESS.setLength(0);
        MESSAGE_SUCCESS.append("Groups in address book:\n");
        ObservableList<Group> groupList = model.getFilteredGroupList();
        groupList.stream().forEach(
                g -> MESSAGE_SUCCESS.append(g.getGroupName() + "\n")
        );
        return new CommandResult(MESSAGE_SUCCESS.toString());
    }
}
